package lambdasinaction.chap10;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                                                   new Shop("LetsSaveBig"),
                                                   new Shop("MyFavoriteShop"),
                                                   new Shop("BuyItAll"),
                                                   new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

/*
    public List<String> findPriceSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getName() + " price is " + shop.calculatePrice(product))
                .collect(Collectors.<String>toList());
    }

    public List<String> findPriceParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getName() + " price is " + shop.calculatePrice(product))
                .collect(Collectors.<String>toList());
    }

    public List<String> findPrice(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.calculatePrice(product)))
                .collect(Collectors.<CompletableFuture<String>>toList());

        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.<String>toList());
        return prices;
        //return sequence(priceFutures).join();
    }
/*/
    public List<String> findPriceSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.<String>toList());
    }

    public List<String> findPriceParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.<String>toList());
    }

    public List<String> findPrice(String product) {
        List<CompletableFuture<String>> priceFutures = findPriceStream(product)
                .collect(Collectors.<CompletableFuture<String>>toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.<String>toList());
    }

    public Stream<CompletableFuture<String>> findPriceStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }
}
