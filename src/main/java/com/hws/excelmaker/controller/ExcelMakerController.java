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
    public String addForm() {
        return "excelmaker/addForm";
    }

    @PostMapping("/check")
    public String check(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong, @RequestParam("carName") String carName,
                                   @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore, @RequestParam("charge") Integer charge, @RequestParam("progress") String progress,
                                   @RequestParam("cashBack") Integer cashBack, @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                                   @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {

        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice,
                releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);


        return "excelmaker/check";
    }

    @PostMapping("/make")
    public String make(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong,
                             @RequestParam("carName") String carName, @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore,
                             @RequestParam("charge") Integer charge, @RequestParam("progress") String progress, @RequestParam("cashBack") Integer cashBack,
                             @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                             @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {
        ExcelData inputData = new ExcelData(contractDate, customerName, belong, carName, carPrice,
                releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

        model.addAttribute("inputData", inputData);

        // if 문 활용하여 만약 기존 파일이 있으면 이어서 작성, 없으면 새로 생성하여 작성(새로하는건 이미 아래와 같이 만들어져 있음) Integer myRow = sheet0.getLastRowNum(); -> @@@@@@@@@@@@@@@@@@@@@@@@@이런식으로 마지막 행 얻기 (마지막 열 얻는 방법도 같음.)
        //validation 구현
        // PRG 처리하기.
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // 파일 덮어써지지 않도록 보완하기(새로 생성하는 것이 아닌 이미 해당 이름의 파일이 있다면, 불러와서 아래 행부터 추가하는 것으로 수정)
        // 같은 POST로 요청시에 PRG관련하여 오류 막을 것.
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        String filePath = "C:\\myDev\\mine.xlsx";
        File file = new File(filePath);

        if(file.exists()){
            excelMakerService.updateFile(inputData, filePath);
        } else {
            excelMakerService.createFile(inputData, filePath);
        }

        return "excelmaker/result";
    }

}
