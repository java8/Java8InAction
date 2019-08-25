package learncode.chap6;

import learncode.commons.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/26 0:25
 * @Version 1.0
 */
public class DCollector {

    public static void main(String[] args) {
        List<Dish> collect = Dish.menu.stream().collect(Collectors.toList());

        List<Dish> collect1 = Dish.menu.stream().collect(new ToListCollector<Dish>());

        ArrayList<Object> collect2 = Dish.menu.stream().collect(ArrayList::new, List::add, List::addAll);

        System.out.println(collect);
        System.out.println("========");
        System.out.println(collect1);
    }
}
