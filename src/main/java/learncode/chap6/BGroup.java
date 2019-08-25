package learncode.chap6;

import lambdasinaction.chap6.Grouping;
import learncode.commons.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/22 23:25
 * @Version 1.0
 */
public class BGroup {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String[] args) {
        /*Map<Dish.Type, List<Dish>> collect = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect);

        Map<CaloricLevel, List<Dish>> collect1 = Dish.menu.stream()
                .collect(Collectors.groupingBy(
                        d -> {
                            if (d.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (d.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }
                ));

        Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(
                        d -> {
                            if (d.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (d.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));

        Map<Dish.Type, Long> collect2 = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<Dish.Type, Dish> collect3 = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));
        Map<Dish.Type, Set<CaloricLevel>> collect = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                        d -> {
                            if (d.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (d.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, Collectors.toCollection(HashSet::new)
                )));*/

    }
}
