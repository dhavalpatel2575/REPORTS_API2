package com.jeku.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.jeku.request.SearchRequest;
import com.jeku.response.SearchResponse;

@Service
public interface ReportService {
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatuses();
	
	public List<SearchResponse> search(SearchRequest request);
	
	public void generateExcel(HttpServletResponse response);
	
	public void generatePdf(HttpServletResponse response)throws Exception;
	
	
}