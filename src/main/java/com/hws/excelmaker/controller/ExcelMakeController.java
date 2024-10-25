package com.hws.excelmaker.controller;

import com.hws.excelmaker.form.ExcelMakeForm;
import com.hws.excelmaker.service.ExcelMakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/excelmaker")
@RequiredArgsConstructor
public class ExcelMakeController {

    private final ExcelMakerService excelMakerService;

    //생성자 하나이므로 @Autowired 생략

    @GetMapping("/form")
    public String excelMakeForm(@ModelAttribute(name = "excelMakeForm") ExcelMakeForm excelMakeForm) {
        return "excelmaker/form/excelForm";
    }


//    @PostMapping("/form")
    public String make(Model model, @RequestParam("contractDate") String contractDate, @RequestParam("customerName") String customerName, @RequestParam("belong") String belong,
                             @RequestParam("carName") String carName, @RequestParam("carPrice") Integer carPrice, @RequestParam("releaseStore") String releaseStore,
                             @RequestParam("charge") Double charge, @RequestParam("progress") String progress, @RequestParam("cashBack") Integer cashBack,
                             @RequestParam("releasePlace") String releasePlace, @RequestParam("supportContents") String supportContents, @RequestParam("etcContents") String etcContents,
                             @RequestParam("enrollDate") String enrollDate, @RequestParam("carNumber") String carNumber) throws IOException {

        ExcelMakeForm inputData = new ExcelMakeForm(contractDate, customerName, belong, carName, carPrice, releaseStore, charge, progress, cashBack, releasePlace, supportContents, etcContents, enrollDate,carNumber);

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

    @PostMapping("/form")
    public String formToExcel(@Validated @ModelAttribute(name = "excelMakeForm") ExcelMakeForm excelMakeForm, BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()) {
            return "excelmaker/form/excelForm";
        }

        ExcelMakeForm inputData = new ExcelMakeForm(excelMakeForm.getContractDate(), excelMakeForm.getCustomerName(), excelMakeForm.getBelong(), excelMakeForm.getCarName(), excelMakeForm.getCarPrice(), excelMakeForm.getReleaseStore(), excelMakeForm.getCarPrice()* excelMakeForm.getCharge()/100, excelMakeForm.getProgress(), excelMakeForm.getCashBack(), excelMakeForm.getReleasePlace(), excelMakeForm.getSupportContents(), excelMakeForm.getEtcContents(), excelMakeForm.getEnrollDate(), excelMakeForm.getCarNumber());

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
