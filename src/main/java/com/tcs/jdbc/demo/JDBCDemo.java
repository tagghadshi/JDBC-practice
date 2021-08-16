package com.tcs.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo {
	public static void main(String[] args) {
		String DB_URL = "jdbc:mysql://localhost/practice";
		String DB_USER = "root";
		String DB_PASSWORD = "Nuvelabs123$";

		try (Connection connnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement statement = connnection.createStatement();) {
//				insert(statement);
//				retrieve(statement);
//				update(statement);
//			    delete(statement);
			List<String> regions = retrieveWithCondition(statement, "A");// regions starting with A
			System.out.println(regions);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void delete(Statement statement) throws SQLException {
		boolean resultSet = statement.execute("Delete from Regions where Region_id = 5");
		
	}

	private static List<String> retrieveWithCondition(Statement statement, String s) throws SQLException {
		ResultSet resultSet = statement.executeQuery("Select * from regions where Region_name like '%"+s+"%'");
		List<String> regions = new ArrayList<String>();
		while (resultSet.next()) {
			regions.add(resultSet.getNString("REGION_NAME"));
		}
		return regions;
	}

	private static void update(Statement statement) throws SQLException {
		int resultSet = statement.executeUpdate("Update Regions Set Region_name ='Antartica' where Region_id = 5");

	}

	private static void retrieve(Statement statement) throws SQLException {
		ResultSet resultSet = statement.executeQuery("Select * from regions");
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
			System.out.println(resultSet.getNString("REGION_NAME"));
		}
	}

	private static void insert(Statement statement) throws SQLException {
		statement.execute("INSERT INTO REGIONS VALUES(5,'Australia')");
	}
}
