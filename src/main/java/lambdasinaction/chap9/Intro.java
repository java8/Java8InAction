package lambdasinaction.chap9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Intro{

    public static void main(String...args){

        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        // sort is a default method
        // naturalOrder is a static method
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
   }
}
