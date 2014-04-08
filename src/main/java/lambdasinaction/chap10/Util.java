package lambdasinaction.chap10;

import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Util {

    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public static void delay() {
        //int delay = 1000;
        int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
/*
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList())
        );
*/
        return CompletableFuture.supplyAsync(() -> futures.stream().
                map(future -> future.join()).
                collect(Collectors.<T>toList()));
    }
}
