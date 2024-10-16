package com.hws.excelmaker.service;

import com.hws.excelmaker.domain.ExcelData;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ExcelMakerService {

    public void createFile(ExcelData excelData, String fileInfo) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        CellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setBorderLeft(BorderStyle.DOUBLE);
        // 셀 아래 테두리
        headCellStyle.setBorderBottom(BorderStyle.DOUBLE);
        // 셀 위 테두리
        headCellStyle.setBorderTop(BorderStyle.DOUBLE);
        // 셀 오른쪽 테두리
        headCellStyle.setBorderRight(BorderStyle.DOUBLE);

        // 셀 배경
        headCellStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
        headCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Sheet sheet0 = workbook.createSheet("고객정보");

        Row title = sheet0.createRow(0);
        Row row1 = sheet0.createRow(1);

        title.createCell(0).setCellValue("계약일");
        title.getCell(0).setCellStyle(headCellStyle);

        title.createCell(1).setCellValue("고객명");
        title.getCell(1).setCellStyle(headCellStyle);

        title.createCell(2).setCellValue("소속");
        title.getCell(2).setCellStyle(headCellStyle);

        title.createCell(3).setCellValue("차종");
        title.getCell(3).setCellStyle(headCellStyle);

        title.createCell(4).setCellValue("가격");
        title.getCell(4).setCellStyle(headCellStyle);

        title.createCell(5).setCellValue("출고점");
        title.getCell(5).setCellStyle(headCellStyle);

        title.createCell(6).setCellValue("수수료");
        title.getCell(6).setCellStyle(headCellStyle);

        title.createCell(7).setCellValue("결과진행");
        title.getCell(7).setCellStyle(headCellStyle);

        title.createCell(8).setCellValue("고객캐쉬백");
        title.getCell(8).setCellStyle(headCellStyle);

        title.createCell(9).setCellValue("출고장");
        title.getCell(9).setCellStyle(headCellStyle);

        title.createCell(10).setCellValue("지원내용");
        title.getCell(10).setCellStyle(headCellStyle);

        title.createCell(11).setCellValue("기타내용");
        title.getCell(11).setCellStyle(headCellStyle);

        title.createCell(12).setCellValue("등록일");
        title.getCell(12).setCellStyle(headCellStyle);

        title.createCell(13).setCellValue("차량번호");
        title.getCell(13).setCellStyle(headCellStyle);


        row1.createCell(0).setCellValue(excelData.getContractDate());
        row1.createCell(1).setCellValue(excelData.getCustomerName());
        row1.createCell(2).setCellValue(excelData.getBelong());
        row1.createCell(3).setCellValue(excelData.getCarName());
        row1.createCell(4).setCellValue(excelData.getCarPrice());
        row1.createCell(5).setCellValue(excelData.getReleaseStore());
        row1.createCell(6).setCellValue(excelData.getCharge());
        row1.createCell(7).setCellValue(excelData.getProgress());
        row1.createCell(8).setCellValue(excelData.getCashBack());
        row1.createCell(9).setCellValue(excelData.getReleasePlace());
        row1.createCell(10).setCellValue(excelData.getSupportContents());
        row1.createCell(11).setCellValue(excelData.getEtcContents());
        row1.createCell(12).setCellValue(excelData.getEnrollDate());
        row1.createCell(13).setCellValue(excelData.getCarNumber());

        FileOutputStream fos = new FileOutputStream(fileInfo);
        workbook.write(fos);
        fos.close();
        workbook.close();
    }

    public void updateFile(ExcelData excelData, String fileInfo) throws IOException {

        FileInputStream fis = new FileInputStream(fileInfo);

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

        FileOutputStream fos = new FileOutputStream(fileInfo);
        workbook.write(fos);
        fos.close();
        workbook.close();
        fis.close();
    }
}
