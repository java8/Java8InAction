package lambdasinaction.chap1;

import java.util.Arrays;
import java.util.List;

public class PrettyApples {

    public static class SimplePretty implements IPrettyApples {

        @Override
        public String pretty(Apple apple) {
            return String.format("apple %s", apple.getColor());
        }
    }

    public static class DetailPretty implements IPrettyApples {
        @Override
        public String pretty(Apple apple) {
            return apple.toString();
        }
    }


    public static void doPretty(List<Apple> apples, IPrettyApples prettyApples) {
        apples.forEach(apple -> System.out.println(prettyApples.pretty(apple)));
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        );

        doPretty(apples, new DetailPretty());
        doPretty(apples, new SimplePretty());

    }

}
