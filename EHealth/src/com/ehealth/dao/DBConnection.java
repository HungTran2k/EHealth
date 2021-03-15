package com.ehealth.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static Connection conn = null;
	static String username="root";
	static String pwd="tuananh123";
	static String connectionUrl = "jdbc:mysql://localhost:3306/loginDB";
	//static String JAWSDB_URL = "jdbc:mysql://k5im8ac4qc2cf8tl:fo61xy6kicdhslrq@td5l74lo6615qq42.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/d7gyazqpp9xujbrs";
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectionUrl,username,pwd);
			//conn = DriverManager.getConnection(JAWSDB_URL);
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}
}