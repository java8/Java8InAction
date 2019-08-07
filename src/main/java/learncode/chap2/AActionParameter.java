package learncode.chap2;

import lambdasinaction.chap2.FilteringApples;
import lambdasinaction.chap3.Sorting;
import learncode.commons.Apple;
import learncode.commons.AppleFormatter;
import learncode.commons.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/3 22:52
 * @Version 1.0
 */
public class AActionParameter {

    public static List<Apple> fileterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple("green", 80), new Apple("green", 155), new Apple("red", 120)));

        List<Apple> apples = fileterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(apples);

         prettyPrintApple(inventory, new AppleFancyFormatter());

        List<Apple> redApples = fileterApples(inventory, new ApplePredicate() {
            @Override public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        System.out.println(redApples);

        List<Apple> greenApples = fileterApples(inventory, (Apple a) -> "green".equals(a.getColor()));

    }

    // 策略模式
    static class AppleWeightPredicate implements ApplePredicate {
        @Override public boolean test(Apple apple) {
            return apple.getWeight() > 80;
        }
    }

    static class AppleColorPredicate implements ApplePredicate {
        @Override public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override public boolean test(Apple apple) {
            return apple.getWeight() > 80 && "red".equals(apple.getColor());
        }
    }

    // 策略模式2
    public static class AppleFancyFormatter implements AppleFormatter {

        @Override public String accept(Apple apple) {
            String characteristic = apple.getWeight() > 80 ? "heavy" : "light";
            return "A " + characteristic + " " + apple.getColor() + " apple";
        }
    }

    public static class AppleSimpleFormatter implements AppleFormatter {
        @Override public String accept(Apple apple) {
            return "An apple of " + apple.getWeight() + "g";
        }
    }


}
