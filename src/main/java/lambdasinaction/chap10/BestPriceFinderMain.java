package lambdasinaction.chap10;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static java.util.stream.Collectors.toList;

public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPriceSequential("myPhone"));
        execute("parallel", () -> bestPriceFinder.findPriceParallel("myPhone"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPrice("myPhone"));
        bestPriceFinder.printPricesStream();
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
