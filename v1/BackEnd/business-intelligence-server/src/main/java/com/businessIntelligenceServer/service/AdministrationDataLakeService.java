package com.businessIntelligenceServer.service;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.springframework.stereotype.Service;

import com.businessIntelligenceServer.feignClients.EmailServerClient;
import com.businessIntelligenceServer.model.Authentication_and_security_usersJPA;
import com.businessIntelligenceServer.model.entity.Authentication_and_security_user;
import com.businessIntelligenceServer.objects.CustomResponse;
import com.businessIntelligenceServer.objects.FileShareHelper;
import com.businessIntelligenceServer.objects.UserInfoData;
import com.businessIntelligenceServer.objects.UsersQtdRolesData;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The AdministrationDataLakeService class provides functionality for managing administration data in a data lake.
 */
@Service
public class AdministrationDataLakeService {

    @Autowired
    Authentication_and_security_usersJPA authentication_and_security_usersJPA;

    @Autowired
    EmailServerClient emailServerClient;

    /**
     * Retrieves user role quantity data.
     *
     * @return a ResponseEntity containing a list of UsersQtdRolesData objects, or an error response
     */
    @Transactional
    public ResponseEntity<List<UsersQtdRolesData>> getUserQtdRolesData() {
        try {
            List<Object[]> result = authentication_and_security_usersJPA.getUserQtdRolesData();

            if (result.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            List<UsersQtdRolesData> userQtdRoleDataList = new ArrayList<>();
            for (Object[] row : result) {
                String role = (String) row[0];
                BigInteger qtd = (BigInteger) row[1];
                UsersQtdRolesData userQtdRoleData = new UsersQtdRolesData(role, qtd);
                userQtdRoleDataList.add(userQtdRoleData);
            }

            return new ResponseEntity<>(userQtdRoleDataList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Generates an Excel file containing user information data grouped by role and sends it via email.
     *
     * @param id the user ID for sending the email
     * @return a ResponseEntity containing a CustomResponse, or an error response
     * @throws IOException if an I/O error occurs
     */
    public ResponseEntity<CustomResponse> generateRoleQtdUsersExcelFile(Integer id) throws IOException {

        CustomResponse response = new CustomResponse();
        String dateResp = new Date().toString();
        // Create a date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_HH-mm");
        // Format the current date
        String formattedDate = dateFormat.format(new Date());
        // Specify the file name with the current timestamp
        String fileName = formattedDate + "UserDataInfo.xlsx";
        // Specify the folder path where you want to save the file
        String fullPath = "C:/Users/Lucas/Desktop/Repositorys/Student-Management-System/v1/GeneratedDocuments/AdministrationWorkspace/UsersInfo/" + fileName;

        try {
            List<Object[]> result = authentication_and_security_usersJPA.getUserInfoData();

            if (result.isEmpty()) {
                response.setMessage("There is no data to generate the file...");
                response.setStatusCode(404);
                response.setTimestamp(dateResp);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            List<UserInfoData> userInfoDataList = new ArrayList<>();

            for (Object[] row : result) {
                String name = (String) row[0];
                String email = (String) row[1];
                String role = (String) row[2];
                String creation_date = (String) row[3];

                UserInfoData newUserInfoData = new UserInfoData(name, email, role, creation_date);

                userInfoDataList.add(newUserInfoData);
            }

            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet
            Sheet sheet = workbook.createSheet("User_Role List");

            // Create header row
            Row headerRow = sheet.createRow(0);

            // Create cells and set values for the headers
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("NAME");

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("EMAIL");

            Cell headerCell3 = headerRow.createCell(2);
            headerCell3.setCellValue("ROLE");

            Cell headerCell4 = headerRow.createCell(3);
            headerCell4.setCellValue("CREATED IN");

            // Create rows and set values for the data
            for (int i = 0; i < userInfoDataList.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                UserInfoData userInfoData = userInfoDataList.get(i);

                Cell dataCell1 = dataRow.createCell(0);
                dataCell1.setCellValue(userInfoData.getName().toString());

                Cell dataCell2 = dataRow.createCell(1);
                dataCell2.setCellValue(userInfoData.getEmail().toString());

                Cell dataCell3 = dataRow.createCell(2);
                dataCell3.setCellValue(userInfoData.getRole().toString());

                Cell dataCell4 = dataRow.createCell(3);
                dataCell4.setCellValue(userInfoData.getCreation_date().toString());
            }

            // Create the complete file path
            Path filePath = Paths.get(fullPath);

            // Write the workbook to the specified file path
            try (OutputStream fileOut = Files.newOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            // Close the workbook
            workbook.close();

            Authentication_and_security_user emailForSend = authentication_and_security_usersJPA.getReferenceById(id);

            FileShareHelper fileShareHelper = new FileShareHelper(emailForSend.getEmail(), fileName, filePath.toString());

            response = emailServerClient.sendUserInfoExcelData(fileShareHelper).getBody();

            if (response.getStatusCode() == 200) {

                Files.delete(filePath);
                System.out.println("File deleted successfully...");

                response.setMessage("Email sent with success...");
                response.setStatusCode(200);
                response.setTimestamp(dateResp);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Files.delete(filePath);
                System.out.println("File deleted successfully...");

                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {

            // Create the complete file path
            Path filePath = Paths.get(fullPath);

            Files.delete(filePath);
            System.out.println("File deleted successfully...");

            response.setMessage(e.getMessage());
            response.setStatusCode(500);
            response.setTimestamp(dateResp);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
