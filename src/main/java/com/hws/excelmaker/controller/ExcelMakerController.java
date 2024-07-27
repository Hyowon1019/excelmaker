package com.hws.excelmaker.controller;

import com.hws.excelmaker.domain.ExcelData;
import com.hws.excelmaker.service.ExcelMakerService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/excel-maker")
@RequiredArgsConstructor
public class ExcelMakerController {

    private final ExcelMakerService excelMakerService;

    //생성자 하나이므로 @Autowired 생략

    @GetMapping
    public String addForm() {
        return "excel-maker/addForm";
    }

    @PostMapping("/check")
    public String check(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong, @RequestParam("carName") String carName,
                                   @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore, @RequestParam("charge") Integer charge, @RequestParam("progress") String progress,
                                   @RequestParam("cashBack") Integer cashBack, @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                                   @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {

        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice, releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);


        return "excel-maker/check";
    }

    @PostMapping("/make")
    public String make(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong,
                             @RequestParam("carName") String carName, @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore,
                             @RequestParam("charge") Integer charge, @RequestParam("progress") String progress, @RequestParam("cashBack") Integer cashBack,
                             @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                             @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {

        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice, releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);

        String filePath = "C:\\myDev\\mine.xlsx";
        File file = new File(filePath);

        if(file.exists()){
            excelMakerService.updateFile(inputData, filePath);
        } else {
            excelMakerService.createFile(inputData, filePath);
        }

        //새로고침으로 인한 POST 중복 요청을 방지하기 위한 PRG 처리
        return "redirect:/";
    }

}
