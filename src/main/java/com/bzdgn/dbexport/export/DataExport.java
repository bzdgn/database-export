package com.bzdgn.dbexport.export;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataExport {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${appconfig.excludeTableList}")
	private String excludeTableList;
	
	List<String> getTableList() {
		String tableQuery = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'PUBLIC'";
		
		List<String> tableNames = jdbcTemplate.queryForList(tableQuery, String.class);
		
		tableNames.removeAll(getExludeTableList());
		
		return tableNames;
	}
	
	List<String> getTableColumns(String tableName) {	
		String tableColumnQuery = "SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName + "'";
		
		List<String> tableColumnNames = jdbcTemplate.queryForList(tableColumnQuery, String.class);
		
		return tableColumnNames;
	}
	
	List<Map<String, Object>> getTableData(String tableName) {	
		String tableDataQuery = "SELECT * FROM " + tableName;
		
		List<Map<String, Object>> tableData = jdbcTemplate.queryForList(tableDataQuery);
		
		return tableData;
	}
	
	List<String> getExludeTableList() {
		return Arrays.asList(excludeTableList.split(","));
	}

}
