////////////////////////////////////////////////////////////////////////////////
//	Title		:	RecordCollection.java
//
//	Description	:	Class which handles access to the database.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ddtek.jdbc.extensions.ExtEmbeddedConnection;
import com.towertechnology.common.environment.EnvironmentClassServer;
import com.towertechnology.common.errorhandling.TTCommonErrors;
import com.towertechnology.common.errorhandling.TTException;
import com.towertechnology.common.errorhandling.TTRepositoryErrors;
import com.vignette.idm.common.ConfigKeys;
import com.vignette.idm.common.Defaults;
import com.vignette.idm.common.IDMConfiguration;
import com.vignette.idm.common.NameValue;
import com.vignette.idm.common.StringBufferS;

import TTEXCEPTIONS.AuthenticationException;
import TTEXCEPTIONS.GenericException;
import TTEXCEPTIONS.TTlibException;
import TTGENERAL.OBJECT_ID;
import TTGENERAL.POA_NAME;

public class RecordCollection {
	private static final String START = "Start: ";
	private static final String COMPLETE = "Complete: ";

	private static final String JBOSS_CONNECTION = "org.jboss.resource.adapter.jdbc.WrappedConnection";
	private static final String JBOSS = "org.jboss.jca.adapters.jdbc.jdk6.WrappedConnectionJDK6";

	private static java.util.logging.Logger mLogger = java.util.logging.Logger
			.getLogger("com.vignette.idm.services.RecordCollection");

	private static Hashtable mIsAppCache = new Hashtable();
	private static Hashtable mFieldsCache = new Hashtable();

	private String mTableSchemaPattern;
	private DataSource mDataSrc;
	private IdmRepImpl mRepository;

	/**
	 * Constructor takes an IdmRepImpl reference to identify the user making
	 * database changes
	 */
	public RecordCollection(IdmRepImpl aRepository) throws TTException {

		System.out.println("Called Record COllection Constructor");

		try {
			Class.forName("com.vignette.jdbc.db2.DB2Driver");

			IDMConfiguration config = (IDMConfiguration) EnvironmentClassServer.getClass(IDMConfiguration.class);
			mTableSchemaPattern = config.getConfigurationValue(ConfigKeys.TABLE_SCHEMA, Defaults.TableSchema);
			String initContextFactory = config.getConfigurationValue(ConfigKeys.CONTEXT_FACTORY,
					Defaults.InitialContextFACTORY);
			String providerURL = config.getConfigurationValue(ConfigKeys.PROVIDER_URL, Defaults.ProviderURL);
			Properties environ = new Properties();
			environ.put(Context.INITIAL_CONTEXT_FACTORY, initContextFactory);
			environ.put(Context.PROVIDER_URL, providerURL);
			InitialContext context = new InitialContext(environ);
			mDataSrc = (DataSource) context.doLookup(aRepository.getDataSrcName());
			mRepository = aRepository;
		} catch (Exception e) {
			String msg = "Unable to retrieve DataSource object (" + e.toString() + ")";
			mLogger.severe(msg);
			throw new TTException(msg, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 * Determine whether a particular tablename is also an IDM Application
	 * 
	 * @param aTableName
	 *            a table name
	 * @return true if the table name is an IDM Application, false otherwise
	 * @exception TTException
	 */
	public boolean isApplication(String aTableName) throws TTException {
		try {
			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(START);
				msg.append("aTableName", aTableName, "");
				mLogger.info(msg.toString());
			}

			// Generate the key to be used on the application cache.
			String key = aTableName + ";" + mRepository.getUserId();

			Boolean ret = (Boolean) mIsAppCache.get(key);
			if (ret == null) {
				// Define formal arguments.
				Class[] formalArgs = new Class[] { java.lang.String.class, java.lang.String.class };

				// Define actual arguments.
				java.lang.Object[] args = new java.lang.Object[] { mRepository.getIdmTicket(), aTableName };

				// Call remote method.
				ret = (Boolean) mRepository.doRemoteMethod("TTGENERAL.GenFunctionsHelper", POA_NAME.value,
						OBJECT_ID.value, null, null, "isApplication", formalArgs, args);

				// Save application indicator in cache.
				mIsAppCache.put(key, ret);
			}

			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("ret", ret.booleanValue(), "");
				mLogger.info(msg.toString());
			}

			return ret.booleanValue();
		} catch (AuthenticationException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LICENSE_INVALID);
		} catch (TTlibException e) {
			mLogger.severe(e.errorDescription);
			String[] ctlerrno = new String[] { Integer.toString(e.errorCode), e.errorCodeStr };
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION, ctlerrno);
		} catch (GenericException e) {
			mLogger.severe(e.errorDescription);
			throw new TTException(e.errorDescription, TTCommonErrors.LOW_LEVEL_EXCEPTION);
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTCommonErrors.LOW_LEVEL_EXCEPTION);
		}
	}

	/**
	 * Execute an SQL statement. SQL statements are usually inserts, updates and
	 * deletes.
	 * 
	 * @param aStatement
	 *            The SQL statement to be executed
	 * @return the number of rows affected
	 * @exception TTException
	 */
	public int executeStatement(SQLStatement aStatement) throws TTException {
		Connection connection = null;
		PreparedStatement prepStatement = null;

		try {
			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(START);
				msg.append("aStatement", aStatement.getSQLStatement(), "");
				mLogger.info(msg.toString());
			}

			mRepository.refresh();
			connection = DriverManager.getConnection(ConfigKeys.DATABASEID, ConfigKeys.DATABASEUSERID,
					ConfigKeys.DATABASEPASSWORD);
			if (connection instanceof ExtEmbeddedConnection) {
				ExtEmbeddedConnection embConnect = (ExtEmbeddedConnection) connection;
				try {
					embConnect.unlock("IDM-Repository");
				} catch (Exception e) {
					System.err.println("ddad");
				}
			}
			// connect = mDataSrc.getConnection();
			// unlockJDBC(connect);
			prepStatement = connection.prepareStatement(aStatement.getSQLStatement());
			setStatement(aStatement, prepStatement);
			int rows = prepStatement.executeUpdate();

			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("rows", rows, "");
				mLogger.info(msg.toString());
			}

			return rows;
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTRepositoryErrors.DATABASE_ERROR);
		} finally {
			try {
				if (prepStatement != null) {
					prepStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				mLogger.severe(e.toString());
				throw new TTException(e.toString(), TTRepositoryErrors.DATABASE_ERROR);
			}
		}
	}

	/**
	 * Execute an SQL select statement
	 * 
	 * @param aStatement
	 *            The SQL select statement
	 * @param anOffset
	 *            Results are returned from this position
	 * @param aNumRows
	 *            The number of rows to return
	 * @return a set of rows
	 * @exception TTException
	 */
	public NameValue[][] executeQuery(SQLStatement aStatement, int anOffset, int aNumRows) throws TTException {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet results = null;

		try {
			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(START);
				msg.append("aStatement", aStatement.getSQLStatement(), ", ");
				msg.append("anOffset", anOffset, ", ");
				msg.append("aNumRows", aNumRows, "");
				mLogger.info(msg.toString());
			}

			mRepository.refresh();
			connection = DriverManager.getConnection(ConfigKeys.DATABASEID, ConfigKeys.DATABASEUSERID,
					ConfigKeys.DATABASEPASSWORD);
			if (connection instanceof ExtEmbeddedConnection) {
				ExtEmbeddedConnection embConnect = (ExtEmbeddedConnection) connection;
				try {
					embConnect.unlock("IDM-Repository");
				} catch (Exception e) {
					System.err.println("ddad");
				}
			}
			prepStatement = connection.prepareStatement(aStatement.getSQLStatement());
			prepStatement.setMaxRows(aNumRows + anOffset);
			setStatement(aStatement, prepStatement);
			results = prepStatement.executeQuery();

			ResultSetMetaData metaData = results.getMetaData();
			int columnCount = metaData.getColumnCount();

			NameValue[][] rows = null;
			if (columnCount != 0) {
				if (anOffset > 0) {
					for (int row = 0; row < anOffset; row++) {
						results.next();
					}
				}
				ArrayList tempRows = new ArrayList(aNumRows);
				NameValue[] currentRow = null;
				while (results.next()) {
					currentRow = new NameValue[columnCount];
					for (int col = 1; col <= columnCount; col++) {
						String value = results.getString(col);
						currentRow[col - 1] = new NameValue(metaData.getColumnName(col), (value == null) ? "" : value);
					}
					tempRows.add(currentRow);
				}
				if (!tempRows.isEmpty()) {
					rows = new NameValue[tempRows.size()][columnCount];
					for (int row = 0; row < tempRows.size(); row++) {
						rows[row] = (NameValue[]) tempRows.get(row);
					}
				}
			}

			if (rows == null) {
				rows = new NameValue[0][];
			}

			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(COMPLETE);
				msg.append("rows", rows, "");
				mLogger.info(msg.toString());
			}

			return rows;
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTRepositoryErrors.DATABASE_ERROR);
		} finally {
			try {
				if (results != null) {
					results.close();
				}
				if (prepStatement != null) {
					prepStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				mLogger.severe(e.toString());
				throw new TTException(e.toString(), TTRepositoryErrors.DATABASE_ERROR);
			}
		}
	}

	/**
	 * Query for the list of columns and their attributes of a particular table
	 *
	 * @return a list of columns
	 * @exception TTException
	 */
	public ColumnDef getFields(String aTableName) throws TTException {

		System.out.println("Called Get Fields Method");
		Connection connection = null;
		ResultSet fieldData = null;

		try {
			if (mLogger.isLoggable(Level.INFO)) {
				StringBufferS msg = new StringBufferS(START);
				msg.append("aTableName", aTableName, "");
				mLogger.info(msg.toString());
			}

			ColumnDef columns = (ColumnDef) mFieldsCache.get(aTableName);
			if (columns == null) {
				System.out.println("No columns");
				// Connect to the database.
				mRepository.refresh();
				connection = DriverManager.getConnection(ConfigKeys.DATABASEID, ConfigKeys.DATABASEUSERID,
						ConfigKeys.DATABASEPASSWORD);
				if (connection instanceof ExtEmbeddedConnection) {
					ExtEmbeddedConnection embConnect = (ExtEmbeddedConnection) connection;
					try {
						embConnect.unlock("IDM-Repository");
					} catch (Exception e) {
						System.err.println("ddad");
					}
				}

				// Obtain table metadata.
				DatabaseMetaData dbMetaData = connection.getMetaData();

				System.out.println("DB Data");
				System.out.println(dbMetaData.getDatabaseProductName());
				System.out.println(dbMetaData.getURL());
				System.out.println(dbMetaData.getUserName());
				System.out.println(dbMetaData.getDriverName());
				System.out.println(dbMetaData.getDatabaseProductVersion());
				System.out.println(dbMetaData.getSchemas().toString());
				System.out.println(dbMetaData.getJDBCMajorVersion());
				System.out.println("DB Data Out");
				String tableNamePattern = (dbMetaData.storesUpperCaseIdentifiers()) ? aTableName.toUpperCase()
						: aTableName;

				fieldData = dbMetaData.getColumns(null, mTableSchemaPattern, tableNamePattern, null);

				// Copy table metadata.
				columns = new ColumnDef();
				while (fieldData.next()) {
					columns.addColAttr(fieldData.getString("COLUMN_NAME"), fieldData.getString("REMARKS"),
							fieldData.getInt("DATA_TYPE"), fieldData.getInt("COLUMN_SIZE"), Defaults.FieldPrecision,
							fieldData.getString("COLUMN_DEF"), fieldData.getString("IS_NULLABLE").equals("NO"),
							Defaults.FieldUpdateable, Defaults.FieldIndexed, Defaults.FieldSearchable);
				}

				// Save table metadata in cache.
				mFieldsCache.put(aTableName, columns);
			}

			mLogger.info(COMPLETE);

			return columns;
		} catch (TTException e) {
			throw e;
		} catch (Exception e) {
			mLogger.severe(e.toString());
			throw new TTException(e.toString(), TTRepositoryErrors.DATABASE_ERROR);
		} finally {
			try {
				if (fieldData != null) {
					fieldData.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				mLogger.severe(e.toString());
				throw new TTException(e.toString(), TTRepositoryErrors.DATABASE_ERROR);
			}
		}
	}

	/**
	 * Set up the SQL Statements within the PreparedStatement class for database
	 * query or statement executions.
	 *
	 */
	private void setStatement(SQLStatement aStatement, PreparedStatement aPrepStatement) throws Exception {
		if (!aStatement.isEmptyParameter()) {
			ArrayList paramList = aStatement.getParameters();
			for (int idx = 0; idx < paramList.size(); idx++) {
				ParameterItem paramItem = (ParameterItem) paramList.get(idx);
				switch (paramItem.getParameterType()) {
				case ColAttr.SQL_DATE:
				case ColAttr.SQL_TYPE_DATE:
					aPrepStatement.setDate(idx + 1, (Date) paramItem.getParameterValue());
					break;

				case ColAttr.SQL_TIME:
				case ColAttr.SQL_TYPE_TIME:
					aPrepStatement.setTime(idx + 1, (Time) paramItem.getParameterValue());
					break;

				case ColAttr.SQL_TIMESTAMP:
				case ColAttr.SQL_TYPE_TIMESTAMP:
					aPrepStatement.setTimestamp(idx + 1, (Timestamp) paramItem.getParameterValue());
					break;
				}
			}
		}
	}

	/**
	 * Unlocks branded DataDirect Connect for JDBC
	 */
	private void unlockJDBC(Connection connect) {
		/*
		 * This bit is JBoss specific but we are trying to avoid importing JBoss JAR
		 * files so we use reflection instead.
		 */

		if (connect.getClass().getName().equals(JBOSS_CONNECTION)
				|| connect.getClass().getSuperclass().getName().equals(JBOSS_CONNECTION)) {
			Method method = null;
			try {
				method = connect.getClass().getMethod("getUnderlyingConnection", null);
				Connection conn = (Connection) method.invoke(connect, null);
				if (conn != null) {
					connect = conn;
				}
			} catch (InvocationTargetException e) {
				mLogger.severe(e.getTargetException().getMessage());
			} catch (Exception e) {
				mLogger.severe(e.toString());
			}
		}

		/*
		 * At this point, we are supposed to have the real database connection.
		 */
		if (connect instanceof ExtEmbeddedConnection) {
			ExtEmbeddedConnection embConnect = (ExtEmbeddedConnection) connect;
			try {
				embConnect.unlock("IDM-Repository");
			} catch (Exception e) {
				mLogger.severe(e.toString());
			}
		}
	}
}

////////////////////////////////////////////////////////////////////////////////
// End of Code
////////////////////////////////////////////////////////////////////////////////
