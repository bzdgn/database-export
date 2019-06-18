package com.bzdgn.dbexport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bzdgn.dbexport.export.DataExport;

@RestController
@RequestMapping("/dataexport")
public class DataExportController {
	
	@Autowired
	private DataExport dataExport;
	
	@GetMapping
	public List<String> list() {
//		return dataExport.getTableList();
		return null;
}

}
