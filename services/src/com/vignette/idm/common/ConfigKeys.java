////////////////////////////////////////////////////////////////////////////////
//	Title		:	ConfigKeys.java
//
//	Description	:	Class which holds IDM Repository configuration keys.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package com.vignette.idm.common;

public final class ConfigKeys {
	public final static String IDM_HOME = "tower.idm.home";
	public final static String TOWER_HOME = "tower.home";

	public final static String HOST_ID = "SystemID";
	public final static String SESSION_ID = "SESSION_ID";
	public final static String CLIENT_IP = "CLIENT_IP";
	public final static String LICENCE_TYPE = "LICENCE_TYPE";
	public final static String APPLICATION_REF = "APPLICATION_REF";
	public final static String IDM_HOSTNAME = "IDM_HOSTNAME";
	public final static String NEW_PASSWORD = "NewPassword";

	// Config keys to connect to the JNDI context factory
	public final static String CONTEXT_FACTORY = "jndi.initial.context.factory";
	public final static String PROVIDER_URL = "provider.url";
	public final static String DATASOURCE_REF = "datasource.ref";
	public final static String TABLE_SCHEMA = "jdbc.table.schema";

	// Config keys for DB2 Database
	public final static String DATABASEID = "jdbc:vignette:db2://10.96.84.141:50000;databaseName=IDMDB2;";
	public final static String DATABASEUSERID = "DB2ADMIN";
	public final static String DATABASEPASSWORD = "Peace123";

}

////////////////////////////////////////////////////////////////////////////////
// End of Code
////////////////////////////////////////////////////////////////////////////////
