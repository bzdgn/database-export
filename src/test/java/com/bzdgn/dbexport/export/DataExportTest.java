package com.bzdgn.dbexport.export;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@JdbcTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { DataExport.class })
//@ActiveProfiles("test")
public class DataExportTest {

	@Autowired
	private DataExport dataExport;

	@Test
	public void testTableList() {
		List<String> expectedResult = Arrays.asList("AUTHORS");

		assertEquals(expectedResult, dataExport.getTableList());
	}

	@Test
	public void testColumnList() {
		List<String> expectedResult = Arrays.asList("ID", "TITLE", "AUTHOR");

		assertEquals(expectedResult, dataExport.getTableColumns("BOOKS"));
	}

	@Test
	public void testDataList() {		
		List<Map<String, Object>> expectedResult;
		
		Map<String, Object> table1 = new HashMap<String, Object>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			put("ID", "1");
			put("TITLE", "Learn Java by Practice");
			put("AUTHOR", "Levent Divilioglu");
		}};
		
		Map<String, Object> table2 = new HashMap<String, Object>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			put("ID", "2");
			put("TITLE", "How To Stay Anonymous");
			put("AUTHOR", "John Doe");
		}};
		
		expectedResult = Arrays.asList(table1, table2);
		
		List<Map<String, Object>> result = dataExport.getTableData("BOOKS");
		
		for(int i = 0; i < result.size(); i++) {
			Map<String,Object> r = result.get(i);
			Map<String,Object> e = expectedResult.get(i);
			
			for(String key : r.keySet()) {
				assertEquals(e.get(key), r.get(key).toString());
			}
		}
		
//		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testExcludeTableList() {
		List<String> expectedResult = Arrays.asList("table1","BOOKS");
		
		assertEquals(expectedResult, dataExport.getExludeTableList());
	}
	
}
