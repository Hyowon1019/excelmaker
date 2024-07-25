package com.hws.excelmaker.service;

import com.hws.excelmaker.domain.ExcelData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ExcelMakerService {

    public void createFile(ExcelData excelData, String filePath) throws IOException {
        //try-catch문으로 감싸기
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet0 = workbook.createSheet("고객정보");

        Row row0 = sheet0.createRow(0);


        row0.createCell(0).setCellValue(excelData.getContractDate());
        row0.createCell(1).setCellValue(excelData.getCustomerName());
        row0.createCell(2).setCellValue(excelData.getBelong());
        row0.createCell(3).setCellValue(excelData.getCarName());
        row0.createCell(4).setCellValue(excelData.getCarPrice());
        row0.createCell(5).setCellValue(excelData.getReleaseStore());
        row0.createCell(6).setCellValue(excelData.getCharge());
        row0.createCell(7).setCellValue(excelData.getProgress());
        row0.createCell(8).setCellValue(excelData.getCashBack());
        row0.createCell(9).setCellValue(excelData.getReleasePlace());
        row0.createCell(10).setCellValue(excelData.getSupportContents());
        row0.createCell(11).setCellValue(excelData.getEtcContents());
        row0.createCell(12).setCellValue(excelData.getEnrollDate());
        row0.createCell(13).setCellValue(excelData.getCarNumber());

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    public void updateFile(ExcelData excelData, String filePath) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();

        Row row0 = sheet.createRow(lastRowNum+1);

        row0.createCell(0).setCellValue(excelData.getContractDate());
        row0.createCell(1).setCellValue(excelData.getCustomerName());
        row0.createCell(2).setCellValue(excelData.getBelong());
        row0.createCell(3).setCellValue(excelData.getCarName());
        row0.createCell(4).setCellValue(excelData.getCarPrice());
        row0.createCell(5).setCellValue(excelData.getReleaseStore());
        row0.createCell(6).setCellValue(excelData.getCharge());
        row0.createCell(7).setCellValue(excelData.getProgress());
        row0.createCell(8).setCellValue(excelData.getCashBack());
        row0.createCell(9).setCellValue(excelData.getReleasePlace());
        row0.createCell(10).setCellValue(excelData.getSupportContents());
        row0.createCell(11).setCellValue(excelData.getEtcContents());
        row0.createCell(12).setCellValue(excelData.getEnrollDate());
        row0.createCell(13).setCellValue(excelData.getCarNumber());

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();
        fis.close();
    }
}
