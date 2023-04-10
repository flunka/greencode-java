package org.example;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import java.util.Objects;

@CompiledJson
class Account {
    @JsonAttribute(name = "account")
    private final String account;
    @JsonAttribute(name = "debitCount")
    private int debitCount;
    @JsonAttribute(name = "creditCount")
    private int creditCount;
    @JsonAttribute(name = "balance")
    private Double balance;

    public Account(String account, int debitCount, int creditCount, Double balance) {
        this.account = account;
        this.debitCount = debitCount;
        this.creditCount = creditCount;
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public int getDebitCount() {
        return debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{account='" + account + '\'' + ", debitCount=" + debitCount + '\''
                + ", creditCount=" + creditCount + '\'' + ", balance=" + balance + '}';
    }

    public void withdraw(Double amount) {
        this.balance -= amount;
        this.debitCount += 1;
    }

    public void deposit(Double amount) {
        this.balance += amount;
        this.creditCount += 1;
    }

}
