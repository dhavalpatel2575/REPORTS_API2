package com.jeku.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeku.request.SearchRequest;
import com.jeku.response.SearchResponse;
import com.jeku.service.ReportService;

@RestController
public class ReportRestController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames(){
		
		List<String> planNames= service.getUniquePlanNames();
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/statuses")
	public ResponseEntity<List<String>> getPlanStatuses(){
		List <String>statuses=service.getUniquePlanStatuses();
		return new ResponseEntity<>(statuses,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request){	
		List<SearchResponse> response=service.search(request);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	  
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/octet-stream");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=data.xlsx";
		response.setHeader(headerKey,headerValue);
		
		service.generateExcel(response);
	}
}

