package learncode.chap4;

import learncode.commons.Dish;

import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/15 0:00
 * @Version 1.0
 */
public class AStream {
    public static void main(String[] args) {
        /*        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        
        List<String> names = new ArrayList<>();
        for (Dish d : Dish.menu) {
            names.add(d.getName());
        }
        names = Dish.menu.stream().map(Dish::getName).collect(Collectors.toList());
        names.forEach(System.out::println);*/

        /*List<String> names = Dish.menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).limit(3)
            .collect(Collectors.toList());
        names.forEach(System.out::println);*/

        /*List<String> names = Dish.menu.stream()
                .filter(d -> {
                    System.out.println("filtering"+d.getName());return d.getCalories() > 300;})
                .map(d->{
                    System.out.println("mapping"+d.getName());return d.getName();})
                .limit(3)
                .collect(Collectors.toList());

        List<Dish> collect = Dish.menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());*/

        /*List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i->i%2==0)
                .distinct()
                .forEach(System.out::println);

        List<Dish> collect = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());*/

        /*List<Dish> collect = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());*/

        /*List<Dish> collect = Dish.menu.stream()
                .filter(d->d.getType()==Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());*/

        /*List<String> collect = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(collect);*/

        /*List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> collect = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(collect);*/

        /*List<Integer> collect = Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        String[] arrayOfWords = {"Goodbye", "World"};
        List<String> words = Arrays.asList(arrayOfWords);
        List<Stream<String>> collect1 = words.stream()
                .map(w -> w.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        List<String> collect2 = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect2);*/

        /*List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(collect);*/

        /*List<Integer> num1 = Arrays.asList(1,2,3);
        List<Integer> num2 = Arrays.asList(3,4);
        System.out.println(num1);
        System.out.println(num2);

        List<int[]> collect = num1.stream()
                .flatMap(i -> num2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        List<int[]> collect1 = num1.stream()
                .flatMap(i ->
                        num2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        collect.stream()
                .map(Arrays::toString)
                .forEach(System.out::println);*/

        /*if (Dish.menu.stream()
                .anyMatch(Dish::isVegetarian)) {
            System.out.println("hahah");
        }
        if (Dish.menu.stream().anyMatch(d -> d.getCalories() > 500)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));*/

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream()
                .map(x->x*x)
                .filter(i->i%3==0)
                .findFirst()
                .ifPresent(System.out::println);

        Integer sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
        Integer sum1 = someNumbers.stream().reduce(0, Integer::sum);
        System.out.println(sum1);

        List<Integer> nums = Arrays.asList(4, 5, 3, 9);
        nums.stream()
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        Integer count = Dish.menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);
    }
}
