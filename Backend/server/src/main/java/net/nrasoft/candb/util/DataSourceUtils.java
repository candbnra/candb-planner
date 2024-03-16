package net.nrasoft.candb.util;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

public class DataSourceUtils {
	public static DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/candb_5g?useUnicode=true");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("NoNo07,,");
		return dataSourceBuilder.build();
	}

}
