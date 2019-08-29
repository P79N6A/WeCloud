package com.bat.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseTools {
	static Logger logger = LoggerFactory.getLogger(DataBaseTools.class);
	
	Connection conn;
	/**
	 * @param conn
	 */
	public DataBaseTools(Connection conn) {
		this.conn = conn;
	}

	/**
	 * @param driverClass
	 * @param connectionURL
	 * @param userId
	 * @param password
	 */
	public static DataBaseTools getInstanc(String driverClass, String connectionURL, String userId, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionURL, userId, password);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new DataBaseTools(conn);
	}
}

class DbConfig {
	DbConfig(String url, String uid, String pwd) {
		this.url = url;
		this.uid = uid;
		this.pwd = pwd;
	}
	public String url = "jdbc:sqlserver://localhost:3306;SelectMethod=cursor;DatabaseName=koo_dolphin";
	public String uid = "root";
	public String pwd = "root";
}
