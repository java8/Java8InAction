package learncode.chap3;

import learncode.commons.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/8 0:30
 * @Version 1.0
 */
public class DSupplier {
    public static <T> T result(Supplier<T> s) {
        return s.get();
    }

    public static void main(String[] args) {
        Apple result = result(() -> new Apple(10));
        System.out.println(result);
    }
}
