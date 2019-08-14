package learncode.chap3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/7 23:32
 * @Version 1.0
 */
public class BConsumer {

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }

    public static <T> void result(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static void main(String[] args) {
        forEach(Arrays.asList(1,2,3,4,5), (Integer i)-> System.out.println(i));
    }
}
