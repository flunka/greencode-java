package org.example.transactions;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
class Transaction {
    @JsonAttribute(name = "debitAccount", mandatory = true)
    private final String debitAccount;
    @JsonAttribute(name = "creditAccount", mandatory = true)
    private final String creditAccount;
    @JsonAttribute(name = "amount", mandatory = true)
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
