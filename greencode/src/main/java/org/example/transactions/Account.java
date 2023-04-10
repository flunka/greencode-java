package org.example.transactions;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
class Account {
    @JsonAttribute(name = "account", mandatory = true)
    private final String account;
    @JsonAttribute(name = "debitCount", mandatory = true)
    private int debitCount;
    @JsonAttribute(name = "creditCount", mandatory = true)
    private int creditCount;
    @JsonAttribute(name = "balance", mandatory = true)
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
