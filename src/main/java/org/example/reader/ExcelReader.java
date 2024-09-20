package org.example.reader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class ExcelReader {
    private static Map<String, Integer> fieldsIndexMap;

    public static List<Object> parse(File excelFile) {
        List<Object> result = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheetAt(0);
            fieldsIndexMap = getFieldsIndexMap(sheet.getRow(0));

            for (int i = 2; i < sheet.getLastRowNum(); ++i) {
                Row row = sheet.getRow(i);
                String className = row.getCell(0).getStringCellValue();
                if (className.isBlank()) {
                    continue;
                }
                Class<?> clazz = Class.forName("org.example.entity." + className);
                result.add(getInstance(row, clazz));
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private static Map<String, Integer> getFieldsIndexMap(Row row) {
        Map<String, Integer> result = new HashMap<>();
        Iterator<Cell> cellIterator = row.cellIterator();

        Cell cell;
        while (cellIterator.hasNext()) {
            cell = cellIterator.next();
            if (cell == null || cell.getStringCellValue().isBlank()) {
                continue;
            }
            String key = cell.getStringCellValue().trim();
            int value = cell.getColumnIndex();
            result.put(key, value);
        }

        return result;
    }

    private static Object getInstance(Row row, Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Object instance;
        try {
            Constructor<?> constructor = clazz.getConstructor();
            instance = constructor.newInstance();
            Set<Field> classFields = getClassFields(clazz);
            for (Field field : classFields) {
                Integer cellIndex = fieldsIndexMap.get(field.getName());
                Object value;
                field.setAccessible(true);
                if (field.getType().isEnum()) {
                    String stringValue = (String) getCellValue(row.getCell(cellIndex), field.getType());
                    Method fromString = field.getType().getDeclaredMethod("fromString", String.class);
                    value = fromString.invoke(field.getType(), stringValue);
                } else if (!field.getType().isPrimitive() && !field.getType().equals(String.class)) {
                    value = getInstance(row, field.getType());
                } else {
                    value = getCellValue(row.getCell(cellIndex), field.getType());
                }
                field.set(instance, value);
            }

            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

    private static Object getCellValue(Cell cell, Class<?> fieldType) {
        DataFormatter formatter = new DataFormatter();
        String stringCellValue = formatter.formatCellValue(cell);

        if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
            return Integer.parseInt(stringCellValue);
        } else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
            return Double.parseDouble(stringCellValue);
        } else if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
            return Long.parseLong(stringCellValue);
        } else if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class)) {
            return Boolean.parseBoolean(stringCellValue);
        } else {
            return stringCellValue;
        }
    }

    private static Set<Field> getClassFields(Class<?> clazz) {
        Class<?> currentClass = clazz;
        Set<Field> fields = new HashSet<>();
        while (currentClass != Object.class) {
            List<Field> declaredFields = Arrays.stream(currentClass.getDeclaredFields()).toList();
            fields.addAll(declaredFields);
            currentClass = currentClass.getSuperclass();
        }

        return fields;
    }
}
