package com.aloys.orm.datasource;

import java.sql.Connection;

public interface DataSource {

	Connection getDataSource();
}
