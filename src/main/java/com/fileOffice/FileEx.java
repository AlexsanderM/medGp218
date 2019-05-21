package com.fileOffice;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.IOException;

public interface FileEx {
    public HSSFSheet getSheet(int numberSheet) throws IOException;
}
