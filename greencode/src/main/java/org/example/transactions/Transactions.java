package org.example.transactions;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.Arrays;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;

import io.activej.http.AsyncServlet;
import io.activej.http.HttpResponse;
import io.activej.http.HttpRequest;
import io.activej.bytebuf.ByteBuf;

import org.example.transactions.Transaction;
import org.example.transactions.Account;

public class Transactions {
    public static AsyncServlet execute(DslJson<?> dslJson) {
        return request -> request.loadBody()
                .map($ -> {
                    ByteBuf body = request.getBody();
                    try {
                        byte[] bodyBytes = body.getArray();
                        Transaction[] transactions = dslJson.deserialize(Transaction[].class, bodyBytes,
                                bodyBytes.length);
                        Map<String, Account> accounts = new HashMap<String, Account>();
                        for (Transaction transaction : transactions) {
                            Account debitAccount = accounts.get(transaction.getDebitAccount());
                            if (debitAccount == null) {
                                debitAccount = new Account(transaction.getDebitAccount(), 0, 0, 0.0);
                                accounts.put(transaction.getDebitAccount(), debitAccount);
                            }
                            Account creditAccount = accounts.get(transaction.getCreditAccount());
                            if (creditAccount == null) {
                                creditAccount = new Account(transaction.getCreditAccount(), 0, 0, 0.0);
                                accounts.put(transaction.getCreditAccount(), creditAccount);
                            }
                            doTransaction(debitAccount, creditAccount, transaction.getAmount());
                        }
                        Account[] accounts_array = accounts.values().toArray(new Account[0]);
                        Arrays.sort(accounts_array, new SortbyAccount());
                        JsonWriter writer = dslJson.newWriter();
                        try {
                            dslJson.serialize(writer, accounts_array);
                        } catch (IOException e) {
                            return HttpResponse.ofCode(400).withHtml("Invalid payload");
                        }
                        return HttpResponse.ok200().withJson(writer.toString());
                    } catch (IOException e) {
                        return HttpResponse.ofCode(400).withHtml("Invalid payload");
                    }
                });

    }

    static void doTransaction(Account debitAccount, Account creditAccount, double amount) {
        debitAccount.withdraw(amount);
        creditAccount.deposit(amount);
    }

}

class SortbyAccount implements Comparator<Account> {

    public int compare(Account a, Account b) {
        return a.getAccount().compareTo(b.getAccount());
    }
}
