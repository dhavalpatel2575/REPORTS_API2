package com.jeku.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Example;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.wp.usermodel.Paragraph;
import org.hibernate.cfg.annotations.QueryBinder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.jeku.entity.EligibilityDetails;
import com.jeku.repo.EligibilityDetailsRepo;
import com.jeku.request.SearchRequest;
import com.jeku.response.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

import springfox.documentation.spi.DocumentationType;


@Service
public class ReportsServiceImpl implements ReportService {

	
	@Autowired
	private EligibilityDetailsRepo eligRepo;
	
	public List<String> getUniquePlanNames() {
		
		return eligRepo.findPlanNames();
	}

	public List<String> getUniquePlanStatuses() {
		return eligRepo.findPlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		
		List<SearchResponse> response=new ArrayList<>();
		
		EligibilityDetails queryBuilder=new EligibilityDetails();
		
		String planName=request.getPlanName();
		if(planName!=null && planName.equals(""))
		{
			queryBuilder.setPlanName(planName);
		}
	
		String planStatus=request.getPlanStatus();
		if(planStatus!=null && planStatus.equals(""))
		{
			queryBuilder.setPlanStatus(planStatus);
		}
		
		LocalDate planStartDate=request.getPlanStartDate();
		if(planStartDate!=null)
		{
			queryBuilder.setPlanStartDate(planStartDate);
		}
	
		LocalDate planEndDate=request.getPlanEndDate();
		if(planEndDate!=null)
		{
			queryBuilder.setPlanEndDate(planEndDate);
		}
		
	
		//org.springframework.data.domain.Example<EligibilityDetails>=Example.of(queryBuilder);
		Example<EligibilityDetails> example=Example.of(queryBuilder);
		List<EligibilityDetails> entities=eligRepo.findAll(example);
		
		for(EligibilityDetails entity: entities)
		{
			SearchResponse sr=new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
			
		}
		
		return null;
	}

	@Override
	public void generateExcel(HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<EligibilityDetails> entities =eligRepo.findAll();
	 	
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet=workBook.createSheet();
		HSSFRow headerRow=sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("S. no.");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Mobile");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("Email");
		headerRow.createCell(5).setCellValue("SSN");

		
		int i=1;
		
		
		//entities.forEach(entity->{
		for(EligibilityDetails entity :entities) {
			
			HSSFRow dataRow=sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getEligId());
			dataRow.createCell(1).setCellValue(entity.getName());
			dataRow.createCell(2).setCellValue(entity.getMobile());
			dataRow.createCell(3).setCellValue(entity.getGender().toString());
			dataRow.createCell(4).setCellValue(entity.getEmail());
			dataRow.createCell(5).setCellValue(entity.getSsn());
			i++;
		}
		
		ServletOutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			workBook.write(outputStream);
			workBook.close();
			outputStream.close();
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	@Override
	public void generatePdf(HttpServletResponse response)throws Exception {
		List<EligibilityDetails> entities=eligRepo.findAll();
		
		Document document =new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		font.setSize(18);
		font.setColor(Color.blue);
		
	}

}
