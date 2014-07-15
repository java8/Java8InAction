package lambdasinaction.chap5;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by raoul-gabrielurma on 14/01/2014.
 */
public class Laziness {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                       .filter(n -> {
                           System.out.println("filtering " + n); return n % 2 == 0;
                       })
                       .map(n -> {
                           System.out.println("mapping " + n);
                           return n * n;
                       })
                       .limit(2)
                       .collect(toList());

    }


}
