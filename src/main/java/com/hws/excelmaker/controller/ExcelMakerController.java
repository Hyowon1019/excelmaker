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
@RequestMapping("/excelmaker")
@RequiredArgsConstructor
public class ExcelMakerController {

    private final ExcelMakerService excelMakerService;

    //생성자 하나이므로 @Autowired 생략

    @GetMapping
    public String excelForm() {
        return "excelmaker/form/excelForm";
    }

    @PostMapping("/info")
    public String check(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong, @RequestParam("carName") String carName,
                                   @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore, @RequestParam("charge") Double charge, @RequestParam("progress") String progress,
                                   @RequestParam("cashBack") Integer cashBack, @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                                   @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {

        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice, releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);


        return "excelmaker/form/checkForm";
    }

    @PostMapping("/file")
    public String make(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong,
                             @RequestParam("carName") String carName, @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore,
                             @RequestParam("charge") Double charge, @RequestParam("progress") String progress, @RequestParam("cashBack") Integer cashBack,
                             @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                             @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {

        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice, releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);

        String folderName = "C:\\CarMasterFolder\\";
        String excelName = "CustomerData.xlsx";
        File file = new File(folderName);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!file.exists()) {
            file.mkdir(); //폴더 생성합니다.
            excelMakerService.createFile(inputData, folderName + excelName);
        } else {
            File excel = new File(folderName + excelName);
            if (!excel.exists()) {
                excelMakerService.createFile(inputData, folderName + excelName);
            } else {
                excelMakerService.updateFile(inputData, folderName + excelName);
            }
        }

        //새로고침으로 인한 POST 중복 요청을 방지하기 위한 PRG 처리
        return "redirect:/";
    }

}
