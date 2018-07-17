////////////////////////////////////////////////////////////////////////////////
//	Title		:	Defaults.java
//
//	Description	:	Class which holds IDM Repository default values.
////////////////////////////////////////////////////////////////////////////////
//	Tab setting	:	4
////////////////////////////////////////////////////////////////////////////////

package  com.vignette.idm.common;

public final class Defaults
{
	public final static String	ConfigBasePath = "/etc/tower";

	public final static String	HostConfigsPath = "/config/idm";

	public final static String	DefaultHostId = "default";

	public final static String	HostPropsFileName =  "host.props";
	public final static String	OrbPropsFileName = "orb.props";
	
	// Default settings for JDBC datasource connections
	public final static String	InitialContextFACTORY =
		"weblogic.jndi.WLInitialContextFactory";
	public final static String	ProviderURL = "t3://localhost:7001";
	public final static String	DataSourceREF = "txdataSource-towerPool";
	public final static String	TableSchema = "TOWER";
	public final static boolean	FieldUpdateable = true;
	public final static boolean	FieldIndexed = false;
	public final static boolean	FieldSearchable = true;
	public final static int		FieldPrecision = 0;
	
	public final static int		CorbaRetrys = 30;
	public final static int		CheckConfigInterval = 1 * 60 * 1000; // msec
}

////////////////////////////////////////////////////////////////////////////////
//	End of Code
////////////////////////////////////////////////////////////////////////////////
