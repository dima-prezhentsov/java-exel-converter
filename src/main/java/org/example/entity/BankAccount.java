package org.example.entity;

import org.apache.poi.ss.usermodel.Row;
public class BankAccount {
    public String iban;
    public String bic;
    public String accountHolder;

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
