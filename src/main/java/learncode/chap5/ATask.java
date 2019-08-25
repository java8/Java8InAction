package learncode.chap5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/16 0:31
 * @Version 1.0
 */
public class ATask {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> collect = transactions.stream()
                .filter(a -> a.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .collect(Collectors.toList());
        collect.stream().forEach(System.out::println);

        List<String> collect1 = transactions.stream()
                .map(a -> a.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        collect1.stream().forEach(System.out::println);

        List<Trader> collect2 = transactions.stream()
                .filter(a -> a.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(collect2);

        boolean milan = transactions.stream()
                .anyMatch(a -> a.getTrader().getCity().equals("Milan"));
        System.out.println(milan);

        transactions.stream()
                .filter(a->a.getTrader().getCity().equals("Cambridge"))
                .map(a->a.getValue())
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        transactions.stream()
                .max(Comparator.comparing(Transaction::getValue))
                .ifPresent(System.out::println);

        transactions.stream()
                .reduce((a,b)->a.getValue()>b.getValue()?a:b)
                .ifPresent(System.out::println);

        String reduce = transactions.stream()
                .map(a -> a.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", String::concat);
        System.out.println(reduce);
    }
}
