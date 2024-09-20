package org.example;

import org.example.entity.*;
import org.example.reader.ExcelReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource("test_table.xlsx");
        if (resource == null) {
            System.out.println("File not found");
            return;
        }
        File file = new File(resource.getFile());
        List<Object> employees = ExcelReader.parse(file);

        long individualEmployeeCount = employees.stream().filter(e -> e instanceof Individual).count();
        long companyEmployeeCount = employees.stream().filter(e -> e instanceof Company).count();
        System.out.println("Individual employee count: " + individualEmployeeCount);
        System.out.println("Company employee count: " + companyEmployeeCount);
        employees.forEach(excelEntity -> {
            if (excelEntity instanceof Individual individual) {
                if (individual.getAge() < 20) {
                    System.out.println(individual.getFirstName() + " " + individual.getLastName());
                }
            }
        });
    }
}