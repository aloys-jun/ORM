package com.aloys.orm.datasource.impl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.aloys.orm.datasource.DataSource;
import com.aloys.orm.exception.ORMException;

public class DataSourceImpl implements DataSource {

	private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	
	
	static{
		try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			
		} catch (Exception e) {
			throw new ORMException("驱动加载异常：" + e.getMessage());
		}
	}

	@Override
	public Connection getDataSource() {
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connect;
	}

}
