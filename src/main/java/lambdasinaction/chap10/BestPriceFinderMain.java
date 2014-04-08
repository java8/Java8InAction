package lambdasinaction.chap10;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static java.util.stream.Collectors.toList;

public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        //execute("sequential", () -> bestPriceFinder.findPriceSequential("myPhone"));
        //execute("parallel", () -> bestPriceFinder.findPriceParallel("myPhone"));
        //execute("composed CompletableFuture", () -> bestPriceFinder.findPrice("myPhone"));
        printPricesStream();
    }

    private static void printPricesStream() {
        long start = System.nanoTime();
        List<CompletableFuture<Void>> futures =
        bestPriceFinder.findPriceStream("myPhone")
                       .map(f -> f.thenAccept(s -> System.out.println(s + " (find in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                       .collect(toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
