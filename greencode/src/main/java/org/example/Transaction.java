package org.example;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import java.util.Objects;

@CompiledJson
class Transaction {
    @JsonAttribute(name = "debitAccount")
    private final String debitAccount;
    @JsonAttribute(name = "creditAccount")
    private final String creditAccount;
    @JsonAttribute(name = "amount")
    private final Double amount;

    public Transaction(String debitAccount, String creditAccount, Double amount) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{debitAccount='" + debitAccount + '\'' + ", creditAccount=" + creditAccount + '\''
                + ", amount=" + amount + '}';
    }

}
