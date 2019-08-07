package learncode.chap1;

import lambdasinaction.chap6.GroupingTransactions;
import learncode.commons.Currency;
import learncode.commons.Transaction;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/3 17:57
 * @Version 1.0
 */
public class BFilterCurrency {
    public static List<Transaction> transactions = Arrays
        .asList(new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0));

    // 筛选出金额大于5000，并按货币分类的集合
    private static Map<Currency, List<Transaction>> filterGroupCurrency(List<Transaction> transactionList) {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        for (Transaction transaction : transactionList) {
            if (transaction.getValue() > 5000) {
                Currency currency = transaction.getCurrency();
                List<Transaction> transactions = transactionsByCurrencies.get(currency);
                if (transactions == null) {
                    transactions = new ArrayList<>();
                    transactionsByCurrencies.put(currency, transactions);
                }
                transactions.add(transaction);
            }
        }
        System.out.println(transactionsByCurrencies);
        return transactionsByCurrencies;
    }

    private static Map<Currency, List<Transaction>> filterGroupCurrencyByStream(List<Transaction> transactionList) {
        Map<Currency, List<Transaction>> collect =
            transactionList.stream().filter((Transaction t) -> t.getValue() > 5000)
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println(collect);
        return collect;
    }

    public static void main(String[] args) {
        // 1 常规遍历
        filterGroupCurrency(transactions);
        // 2 stream流
        filterGroupCurrencyByStream(transactions);
    }
}
