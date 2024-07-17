package com.hws.excelmaker.controller;

import com.hws.excelmaker.domain.ExcelData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/excelmaker")
public class ExcelMakerController {

    @GetMapping
    public String addForm() {
        return "excelmaker/addForm";
    }

    @PostMapping("/check")
    public String inputDataChecker(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong, @RequestParam("carName") String carName,
                                   @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore, @RequestParam("charge") Integer charge, @RequestParam("progress") String progress,
                                   @RequestParam("cashBack") Integer cashBack, @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                                   @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {

        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice,
                releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);


        return "excelmaker/check";
    }

    @PostMapping("/make")
    public String excelMaker(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong,
                             @RequestParam("carName") String carName, @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore,
                             @RequestParam("charge") Integer charge, @RequestParam("progress") String progress, @RequestParam("cashBack") Integer cashBack,
                             @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                             @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {
        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice,
                releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);

        Workbook workBook = new XSSFWorkbook();

        Sheet sheet0 = workBook.createSheet("고객정보");

        Row row0 = sheet0.createRow(0);
        // Integer myRow = sheet0.getLastRowNum(); -> @@@@@@@@@@@@@@@@@@@@@@@@@이런식으로 마지막 행 얻기 (마지막 열 얻는 방법도 같음.)
        // PRG 처리하기.

        row0.createCell(0).setCellValue(contractDate);
        row0.createCell(1).setCellValue(customerName);
        row0.createCell(2).setCellValue(belong);
        row0.createCell(3).setCellValue(carName);
        row0.createCell(4).setCellValue(carPrice);
        row0.createCell(5).setCellValue(releaseStore);
        row0.createCell(6).setCellValue(charge);
        row0.createCell(7).setCellValue(progress);
        row0.createCell(8).setCellValue(cashBack);
        row0.createCell(9).setCellValue(releasePlace);
        row0.createCell(10).setCellValue(supportContents);
        row0.createCell(11).setCellValue(etcContents);
        row0.createCell(12).setCellValue(enrollDate);
        row0.createCell(13).setCellValue(carNumber);

        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // 파일 덮어써지지 않도록 보완하기(새로 생성하는 것이 아닌 이미 해당 이름의 파일이 있다면, 불러와서 아래 행부터 추가하는 것으로 수정)
        // 같은 POST로 요청시에 PRG관련하여 오류 막을 것.
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        FileOutputStream fos = new FileOutputStream("C:\\myDev\\mine.xlsx");
        workBook.write(fos);
        fos.close();
        workBook.close();

        return "excelmaker/result";
    }

}
