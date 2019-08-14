package learncode.chap3;

import java.util.function.DoubleFunction;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/14 0:09
 * @Version 1.0
 */
public class HLambda {

    public double integreate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }
}
