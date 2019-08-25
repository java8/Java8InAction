package learncode.chap5;

import lambdasinaction.chap6.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/16 23:56
 * @Version 1.0
 */
public class BValueStream {

    public static void main(String[] args) throws IOException {
        /*Integer reduce = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        int sum = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        IntStream intStream = Dish.menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

        int i = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max()
                .orElse(1);

        int reduce1 = IntStream
                .rangeClosed(1, 100)
                .filter(n -> n % 2 == 0)
                .map(d->1)
                .reduce(0, (a, b) -> a + b);
            System.out.println(reduce1);

        IntStream.rangeClosed(1,100)
                .boxed()
                .flatMap(a->
                        IntStream.rangeClosed(a,100)
                                .filter(b->Math.sqrt(a*a+b*b)%1==0)
                                .mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)}));

        Stream<String> stringStream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        int[] nums = {2, 3, 4, 5, 6};
        int sum1 = Arrays.stream(nums).sum();*/

        /*Stream<String> lines = Files.lines(Paths.get("lambdasinaction/chap5/data.txt"), Charset.defaultCharset());
        long count = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct()
                .count();*/

        /*Stream.iterate(0, n->n+2)
                .limit(10)
                .forEach(System.out::println);*/

        /*Stream.iterate(new int[]{0,1}, t->new int[]{t[1],t[0]+t[1]})
                .limit(20)
                .map(t->t[0])
                .forEach(System.out::println);*/

        /*Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);*/

        /*int[] nums = {2, 3, 4, 5, 6};
        List<Integer> integers = Arrays.asList(2, 3, 5, 6);
        Integer integer = integers.stream()
                .filter(a -> a == 3)
                .findFirst()
                .orElse(null);
        System.out.println(integer);

        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });*/

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
}
