package learncode.chap6;

import learncode.commons.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/22 23:56
 * @Version 1.0
 */
public class CParty {

    public static boolean isPrime(int candidate) {
        int sqrt = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, sqrt)
                .noneMatch(i -> sqrt % i == 0);

    }

    public Map<Boolean, List<Integer>> partitionPrimes(int n) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, n)
                .boxed()
                .collect(Collectors.partitioningBy(a -> isPrime(a)));
        return collect;
    }

    public static void main(String[] args) {
        Map<Boolean, Dish> collect = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect);
    }
}
