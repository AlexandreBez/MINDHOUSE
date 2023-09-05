package com.businessServer.userReportFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.businessServer.model.entity.UsersData;

@Service
public class UserExcelReport {

	public Path createUserExcelWorkbook(List<UsersData> usersData) throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("User report");

		Row headerRow = sheet.createRow(0);
		Cell headerCell1 = headerRow.createCell(0);
		headerCell1.setCellValue("NAME");
		Cell headerCell2 = headerRow.createCell(1);
		headerCell2.setCellValue("EMAIL");
		Cell headerCell3 = headerRow.createCell(2);
		headerCell3.setCellValue("ROLE");
		Cell headerCell4 = headerRow.createCell(3);
		headerCell4.setCellValue("CREATED_ON");
		Cell headerCell5 = headerRow.createCell(4);
		headerCell5.setCellValue("STATUS");
		Cell headerCell6 = headerRow.createCell(5);
		headerCell6.setCellValue("UPDATED_ON");

		for (int i = 0; i < usersData.size(); i++) {
			Row dataRow = sheet.createRow(i + 1);
			UsersData userReportData = usersData.get(i);

			Cell dataCell1 = dataRow.createCell(0);
			dataCell1.setCellValue(userReportData.getName());

			Cell dataCell2 = dataRow.createCell(1);
			dataCell2.setCellValue(userReportData.getEmail());

			Cell dataCell3 = dataRow.createCell(2);
			dataCell3.setCellValue(userReportData.getRole());

			Cell dataCell4 = dataRow.createCell(3);
			dataCell4.setCellValue(userReportData.getCreated_on());

			Cell dataCell5 = dataRow.createCell(4);
			dataCell5.setCellValue(userReportData.getStatus());

			Cell dataCell6 = dataRow.createCell(5);
			dataCell6.setCellValue(userReportData.getUpdated_on());
		}

		Path tempFile = Files.createTempFile("user_report", ".xlsx");
		try (OutputStream fileOutputStream = Files.newOutputStream(tempFile)) {
			workbook.write(fileOutputStream);
		}

		return tempFile;
	}
	
	public Path createUserExcelWorkbookAndSave(List<UsersData> usersData) throws IOException {

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("User report");

		Row headerRow = sheet.createRow(0);
		Cell headerCell1 = headerRow.createCell(0);
		headerCell1.setCellValue("NAME");
		Cell headerCell2 = headerRow.createCell(1);
		headerCell2.setCellValue("EMAIL");
		Cell headerCell3 = headerRow.createCell(2);
		headerCell3.setCellValue("ROLE");
		Cell headerCell4 = headerRow.createCell(3);
		headerCell4.setCellValue("CREATED_ON");
		Cell headerCell5 = headerRow.createCell(4);
		headerCell5.setCellValue("STATUS");
		Cell headerCell6 = headerRow.createCell(5);
		headerCell6.setCellValue("UPDATED_ON");

		for (int i = 0; i < usersData.size(); i++) {
			Row dataRow = sheet.createRow(i + 1);
			UsersData userReportData = usersData.get(i);

			Cell dataCell1 = dataRow.createCell(0);
			dataCell1.setCellValue(userReportData.getName());

			Cell dataCell2 = dataRow.createCell(1);
			dataCell2.setCellValue(userReportData.getEmail());

			Cell dataCell3 = dataRow.createCell(2);
			dataCell3.setCellValue(userReportData.getRole());

			Cell dataCell4 = dataRow.createCell(3);
			dataCell4.setCellValue(userReportData.getCreated_on());

			Cell dataCell5 = dataRow.createCell(4);
			dataCell5.setCellValue(userReportData.getStatus());

			Cell dataCell6 = dataRow.createCell(5);
			dataCell6.setCellValue(userReportData.getUpdated_on());
		}

	    String reportDirectoryPath = "C:/Users/adm/Desktop/Repositorys/MINDHOUSE/v1/BackEnd/business-server/src/main/resources/ReportDataFiles";
	    Path reportDirectory = Paths.get(reportDirectoryPath);

	    if (!Files.exists(reportDirectory)) {
	        Files.createDirectories(reportDirectory);
	    }

	    String fileName = "user_report.xlsx";
	    Path reportFilePath = reportDirectory.resolve(fileName);

	    try (OutputStream fileOutputStream = Files.newOutputStream(reportFilePath)) {
	        workbook.write(fileOutputStream);
	    }

	    return reportFilePath;
	}
	
}
