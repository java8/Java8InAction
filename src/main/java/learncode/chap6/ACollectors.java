package learncode.chap6;

import learncode.commons.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/21 22:39
 * @Version 1.0
 */
public class ACollectors {
    public static void main(String[] args) {
        /*Dish.menu.stream()
                .collect(Collectors.counting());

        Comparator<Dish> comparing = Comparator.comparing(Dish::getCalories);
        Optional<Dish> collect = Dish.menu.stream()
                .collect(Collectors.maxBy(comparing));

        Integer collect1 = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(collect1);
        Integer reduce = Dish.menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> (a + b));
        System.out.println(reduce);

        Dish collect2 = Dish.menu.stream().collect(Collectors.minBy(Comparator.comparing(Dish::getCalories))).get();
        System.out.println(collect2);
        Integer reduce2 = Dish.menu.stream().map(Dish::getCalories).reduce(0, (a, b) -> a<b?a:b);
        System.out.println(reduce2);*/

        /*IntSummaryStatistics collect = Dish.menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(collect);

        String collect1 = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(";"));
        System.out.println(collect1);*/

        Optional<Dish> collect = Dish.menu.stream().collect(Collectors.reducing(
                (Dish a, Dish b) -> a.getCalories() > b.getCalories() ? a : b
        ));
        System.out.println(collect);

        int sum = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }
}
