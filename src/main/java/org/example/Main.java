package org.example;

import org.example.entity.ExcelEntity;
import org.example.reader.ExcelReader;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("/Users/dima/IdeaProjects/java-exel-converter/src/main/resources/test_table.xlsx");
        List<ExcelEntity> entityList = ExcelReader.parse(file);
    }
}