package com.stocks.service.IO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.stocks.common.CONSTANT;

public class DBService {
	private static final Logger LOGGER = Logger.getLogger(DBService.class);
	private static Connection conn;
	private static Statement st;

	public static void initial() {
		switch (CONSTANT.DB.CONNECTION_TYPE) {
		case "Oracle":
			initialOracle();
			break;
		case "Mysql":
			initialMySql();
			break;
		default:
			conn=null;
			break;
		}
	}

	public static void initialMySql() {
		try {
			Class.forName(CONSTANT.DB.MYSQL.DB_DRIVER);
			LOGGER.info("Connecting " + CONSTANT.DB.MYSQL.DB_URL + "/" + CONSTANT.DB.DB_NAME
					+ "?useLegacyDatetimeCode=false&serverTimezone=America/New_York");
			conn = DriverManager.getConnection(
					CONSTANT.DB.MYSQL.DB_URL + "/" + CONSTANT.DB.DB_NAME
							+ "?useLegacyDatetimeCode=false&serverTimezone=America/New_York",
					CONSTANT.DB.MYSQL.DB_USERNAME, CONSTANT.DB.MYSQL.DB_PASSWORD);
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			LOGGER.info("Connection Success!");
		} catch (Exception e) {
			LOGGER.error("Error");
			e.printStackTrace();
		}
	}

	public static void initialOracle() {
		try {
			Class.forName(CONSTANT.DB.ORACLE.DB_DRIVER);
			LOGGER.info("Connecting " + CONSTANT.DB.ORACLE.DB_URL);
			conn = DriverManager.getConnection(CONSTANT.DB.ORACLE.DB_URL, CONSTANT.DB.ORACLE.DB_USERNAME,
					CONSTANT.DB.ORACLE.DB_PASSWORD);
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			LOGGER.info("Connection Success!");
		} catch (Exception e) {
			LOGGER.error("Error");
			e.printStackTrace();
		}
	}

	public static int executeInsert(String sql) {
		try {
			int count = st.executeUpdate(sql);
			return count;
		} catch (SQLException e) {
			LOGGER.error("SQL:" + sql);
			e.printStackTrace();
			return -1;
		}
	}

	public static int executeUpdate(String sql) {
		try {
			int count = st.executeUpdate(sql);
			return count;
		} catch (SQLException e) {
			System.out.println("SQL:" + sql);
			e.printStackTrace();
			return -1;
		}
	}

	public static int executePreparedUpdate(String sql, List<String> parameters) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < parameters.size(); i++) {
				pstmt.setString(i + 1, parameters.get(i));
			}
			int count = pstmt.executeUpdate();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ResultSet executeQuery(String sql) {
		try {
			Statement sta = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = sta.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			LOGGER.error("SQL:" + sql);
			e.printStackTrace();
			return null;
		}
	}

	public static void close() {
		try {
			st.close();
			conn.close();
			LOGGER.info("Connection closed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Close Error!");
		}
	}
}
