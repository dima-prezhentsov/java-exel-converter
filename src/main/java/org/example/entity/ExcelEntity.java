package org.example.entity;

import org.apache.poi.ss.usermodel.Row;

public abstract class ExcelEntity {
    public abstract void fromRow(Row row);
}
