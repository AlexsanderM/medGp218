package com.fileOffice;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileExcelXls implements FileEx{

    private FileInputStream input;
    private FileOutputStream outFile;
    private int sheet;

//    public FileExcelXls(String path) throws FileNotFoundException {
//        outFile = new FileOutputStream(path);
//    }

    public FileExcelXls(String path) {
        try {
            input = new FileInputStream(path);
            this.sheet = sheet;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HSSFSheet getSheet(int numberSheet) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        HSSFSheet sheet = workbook.getSheetAt(numberSheet);
        workbook.close();

        return sheet;
    }

    public Integer getSheetCount() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(input);
        workbook.close();

        return workbook.getNumberOfSheets();
    }
}
