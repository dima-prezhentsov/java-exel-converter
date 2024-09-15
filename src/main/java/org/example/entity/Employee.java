package org.example.entity;

import org.apache.poi.ss.usermodel.Row;

public class Employee extends ExcelEntity {
    private long id;
    private String email;
    private String phone;
    private String address;
    private BankAccount bankAccount;

    @Override
    public void fromRow(Row row) {
        long id = Long.parseLong(row.getCell(1).getStringCellValue());
        String email = row.getCell(2).getStringCellValue();
        String phone = row.getCell(3).getStringCellValue();
        String address = row.getCell(4).getStringCellValue();
        BankAccount bankAccount = new BankAccount();
        bankAccount.fromRow(row);

        setId(id);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        setBankAccount(bankAccount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
