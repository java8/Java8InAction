package learncode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/2 0:39
 * @Version 1.0
 */
public class SelectApple {

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
        List<Apple> greens = filterApples(apples, SelectApple::isGreenApple);

        // 3. 
    }

}

class Apple {
    private String color;
    private int weight;

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}


