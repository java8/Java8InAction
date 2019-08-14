package learncode.chap3;

import java.util.List;
import java.util.function.IntBinaryOperator;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/8 23:09
 * @Version 1.0
 */
public class EIntBinaryOperator {
    public static int result(int a, int b, IntBinaryOperator i) {
        return i.applyAsInt(a, b);
    }

    public static void main(String[] args) {
        int result = result(2, 3, (int a, int b) -> a * b);
        System.out.println(result);
    }
}
