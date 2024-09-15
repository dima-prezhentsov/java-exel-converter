package org.example.reader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.entity.ExcelEntity;
import org.example.entity.ExelEntityFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ExcelReader {
    public static List<ExcelEntity> parse(File excelFile) {
        List<ExcelEntity> entities = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);
            Row row;
            String className;
            ExcelEntity entity;
            for (int i = 1; i < sheet.getLastRowNum(); ++i) {
                row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().isBlank()) {
                    continue;
                }
                className = row.getCell(0).getStringCellValue();
                entity = ExelEntityFactory.createEntity(className);
                entity.fromRow(row);
                entities.add(entity);
            }

            return entities;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
