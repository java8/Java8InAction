package learncode.chap1;

import learncode.commons.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Description
 * @Author YC
 * @Date 2019/8/2 0:39
 * @Version 1.0
 */
public class ASelectApple {

    // 选出所有的绿色苹果
    public static List<Apple> filterGreenApples(List<Apple> inventry) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventry) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 示例集合
        List<Apple> apples = Arrays.asList(new Apple("green",80), new Apple("red",90), new Apple("blue",100));
        // 1. 常规方法
        List<Apple> greenApples = filterGreenApples(apples);
        System.out.println(greenApples);

        // 2. 函数引用
        List<Apple> greens = filterApples(apples, ASelectApple::isGreenApple);

        // 3. 顺序处理
        List<Apple> collect = apples.stream().filter((Apple a) -> a.getWeight() > 80).collect(toList());
        System.out.println(collect);

        // 4. 并行处理
        List<Apple> collect1 = apples.parallelStream().filter((Apple a) -> a.getWeight() > 80).collect(toList());
        System.out.println(collect1);
    }

}


