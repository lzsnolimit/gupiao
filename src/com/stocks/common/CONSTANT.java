package com.stocks.common;

public class CONSTANT {
	// public static String DATA_SOURCE = "Yahoo";
	public static String DATA_SOURCE = "XueQiu";
	public static String DATA_MARKET = "CN";
	// public static String SYMBOL_LIST_SOURCE = "bianli";
	public static String SYMBOL_LIST_SOURCE = "list";

	public static class DB {

		// public static String CONNECTION_TYPE = "Oracle";
		public static String CONNECTION_TYPE = "Mysql";
		public static String DB_NAME = "stock";
		public static String RATE_TABLE_NAME = "rates";
		public static String History_Data_TABLE_NAME = "history";

		public static class ORACLE {
			public static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
			public static String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
			public static String DB_USERNAME = "nolimit";
			public static String DB_PASSWORD = "985211kj";
			public static String DB_IMPORT_FILE = "D:/stocks.sql";
			public static String DB_OUT_FILE = "D:/stocks/";
		}

		public static class MYSQL {
			public static String DB_DRIVER = "com.mysql.jdbc.Driver";
			public static String DB_URL = "jdbc:mysql://127.0.0.1:3306";
			public static String DB_USERNAME = "root";
			public static String DB_PASSWORD = "HAnolimit2010*";
		}

	}

	public static class HISTORY {
		public static String DATA_PATH = "files/history/" + DATA_MARKET + "/";
		public static String XUEQIU_ONEDAY_PATH = "files/history/xueqiu/dataByDay/" + DATA_MARKET + "/";
		public static String XUEQIU_TODAY="files/history/xueqiu/today/" + DATA_MARKET + "/";
		public static String SYMBOL_LIST = "files/history/xueqiu/dataByDay/" + DATA_MARKET + "/list.txt";
		
	}

	public static class HEADER {
		public static String XUEQIU = "files/httpHeaders/xueqiu";
	}

	// public static String MYSQL_DB_URL="";

}
