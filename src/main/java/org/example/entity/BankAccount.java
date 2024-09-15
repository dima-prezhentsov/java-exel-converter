package org.example.entity;

import org.apache.poi.ss.usermodel.Row;
public class BankAccount extends ExcelEntity {
    public String iban;
    public String bic;
    public String accountHolder;

    @Override
    public void fromRow(Row row) {
        String iban = row.getCell(11).getStringCellValue();
        String bic = row.getCell(12).getStringCellValue();
        String accountHolder = row.getCell(13).getStringCellValue();

        setIban(iban);
        setBic(bic);
        setAccountHolder(accountHolder);
    }


    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
