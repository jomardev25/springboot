package com.jomardev25.springdatajpa.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.jomardev25.springdatajpa.service.UserService;

import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@AllArgsConstructor
public class ReportController {

	private UserService userService;

	@GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> downloadPdf() throws JRException, IOException {


		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(userService.findAllUsers());
		JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/reports/students_report.jrxml"));
		Map<String, Object> parameters = new HashMap<>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

		byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

	}

}
