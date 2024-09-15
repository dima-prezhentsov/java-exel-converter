package org.example;

import org.example.entity.Company;
import org.example.entity.ExcelEntity;
import org.example.entity.Individual;
import org.example.reader.ExcelReader;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource("test_table.xlsx");
        if (resource == null) {
            System.out.println("File not found");
            return;
        }

        File file = new File(resource.getFile());
        List<ExcelEntity> entityList = ExcelReader.parse(file);
        long individualEmployeeCount = entityList.stream().filter(e -> e instanceof Individual).count();
        long companyEmployeeCount = entityList.stream().filter(e -> e instanceof Company).count();
        System.out.println("Individual employee count: " + individualEmployeeCount);
        System.out.println("Company employee count: " + companyEmployeeCount);
        entityList.forEach(excelEntity -> {
            if (excelEntity instanceof Individual individual) {
                if (individual.getAge() < 20) {
                    System.out.println(individual.getFirstName() + " " + individual.getLastName());
                }
            }
        });
    }
}