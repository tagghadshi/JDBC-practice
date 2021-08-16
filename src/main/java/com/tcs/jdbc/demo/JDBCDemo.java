package com.tcs.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCDemo {
	private static final Logger logger = LogManager.getLogger(JDBCDemo.class);
	public static void main(String[] args) {
		String DB_URL = "jdbc:mysql://localhost/practice";
		String DB_USER = "root";
		String DB_PASSWORD = "Nuvelabs123$";
		
		logger.debug("Connection to DB successful");
		
		try (Connection connnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement statement = connnection.createStatement();) {
//				insert(statement);
//				retrieve(statement);
//				update(statement);
//			    delete(statement);
			List<String> regions = retrieveWithCondition(statement, "A");// regions starting with A
			logger.debug(regions);
//			System.out.println(regions);
			orderBy(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void orderBy(Statement statement) throws SQLException {
		ResultSet rs = statement.executeQuery("Select * from regions Order by Region_name");
		while (rs.next()) {
			logger.debug(rs.getNString("Region_name"));
		}

	}

	private static void delete(Statement statement) throws SQLException {
		boolean resultSet = statement.execute("Delete from Regions where Region_id = 5");

	}

	private static List<String> retrieveWithCondition(Statement statement, String s) throws SQLException {
		ResultSet resultSet = statement.executeQuery("Select * from regions where Region_name like '%" + s + "%'");
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
			logger.debug(resultSet.getNString("REGION_NAME"));
//			System.out.println(resultSet.getInt(1));
//			System.out.println(resultSet.getNString("REGION_NAME"));
		}
	}

	private static void insert(Statement statement) throws SQLException {
		statement.execute("INSERT INTO REGIONS VALUES(5,'Australia')");
	}
}
