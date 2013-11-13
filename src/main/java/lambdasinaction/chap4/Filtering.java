package lambdasinaction.chap4;
import java.util.stream.*;
import java.util.*;

import static lambdasinaction.chap4.Dish.menu;

public class Filtering{

    public static void main(String...args){

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
               .filter(i -> i % 2 == 0)
               .distinct()
               .forEach(System.out::println);

        numbers.stream().limit(20).forEach(System.out::println);
        
    }
    
    
    
}