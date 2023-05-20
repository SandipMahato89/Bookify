package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static Connection conn; 
	public static Connection getConn() {
		try {
			//Loading the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ebook";
			conn = DriverManager.getConnection(url,"root","1234"); //url, username, pass
			System.out.println(conn);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
}
