package com.tcs.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
	public static void main(String[] args) {
		String DB_URL ="jdbc:mysql://localhost/practice";
		String DB_USER="root";
		String DB_PASSWORD = "Nuvelabs123$";
		
		try(Connection connnection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
				Statement statement = connnection.createStatement();){
//				insert(statement);
				retrieve(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}

	private static void retrieve(Statement statement) throws SQLException {
		ResultSet resultSet = statement.executeQuery("Select * from regions");
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1));
			System.out.println(resultSet.getNString("REGION_NAME"));
		}
	}

	private static void insert(Statement statement) throws SQLException {
		statement.execute("INSERT INTO REGIONS VALUES(5,'Australia')");
	}
}
