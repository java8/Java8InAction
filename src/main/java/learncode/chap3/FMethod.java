package learncode.chap3;

import learncode.commons.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/11 23:05
 * @Version 1.0
 */
public class FMethod {

    public static void main(String[] args) {
        /*List<String> strs = Arrays.asList("a", "b", "A", "B");
        System.out.println(strs);
        strs.sort((s1,s2)->s1.compareToIgnoreCase(s2));
        System.out.println(strs);

        Supplier<Apple> c1 = Apple::new;
        System.out.println(c1.get());

        Function<Integer,Apple> c2 = Apple::new;
        System.out.println(c2.apply(110));*/

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println(apples);
    }

    public static List<Apple> map(List<Integer> list,Function<Integer,Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e));
        }
        return result;
    }
}
