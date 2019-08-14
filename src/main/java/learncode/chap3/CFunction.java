package learncode.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/7 23:38
 * @Version 1.0
 */
public class CFunction {

    public static <T,R> List<R> map(List<T> list, Function<T,R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    public static <T, R> List<R> result(List<T> list, Function<T, R> f) {
        List<R> results = new ArrayList<>();
        for (T t : list) {
            results.add(f.apply(t));
        }
        return results;
    }

    public static void main(String[] args) {
        List<Integer> map = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        System.out.println(map);
        List<Boolean> result = result(Arrays.asList("lambdas", "in", "action"), (String s) -> s.contains("i"));
        System.out.println(result);
    }
}
