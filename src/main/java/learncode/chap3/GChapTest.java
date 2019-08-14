package learncode.chap3;

import lambdasinaction.chap3.Sorting;
import learncode.commons.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/12 0:47
 * @Version 1.0
 */
public class GChapTest {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple("green",80), new Apple( "green",155), new Apple("red",120)));
        
        // f1
        inventory.sort(new AppleComparator());
        // f2
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        //f3
        inventory.sort((Apple a1,Apple a2)->a1.getWeight().compareTo(a2.getWeight()));
        //f4
        inventory.sort((a1, a2)->a1.getWeight().compareTo(a2.getWeight()));
        //f5
        inventory.sort(Comparator.comparing(a->a.getWeight()));
        //f6
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }

    static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }
}
