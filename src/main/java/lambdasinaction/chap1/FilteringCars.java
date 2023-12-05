package lambdasinaction.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static lambdasinaction.chap1.CarSize.*;

public class FilteringCars {

    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
            new Car("Opel", "Corsa", "Blue", 1200, COMPACT),
            new Car("Toyota", "RAV4", "Black", 2000, LARGE),
            new Car("Toyota", "Yaris", "Red", 1000, COMPACT),
            new Car("Nissan", "Micra", "Yellow", 800, SUBCOMPACT),
            new Car("Mazda", "626", "Green", 1800, MEDIUM),
            new Car("Bentley", "Bollix", "Gold", 2800, LIKE_REALY_LARGE),
            new Car("Ford", "Mondeo", "White", 1600, MEDIUM),
            new Car("Fiat", "Tipo", "Silver", 1400, MEDIUM)
        );

        //        List<Car> midSizedCars = filterMediumCars(cars);
        //        System.out.println("midSizedCars - the initial filter");
        //        System.out.println(midSizedCars);
        //
        //        List<Car> smallCars = filterSmallCars(cars);
        //        System.out.println("smallCars - the initial filter");
        //        System.out.println(smallCars);

        //        List<Car> compactCars = filterCars(cars, (Car c) -> COMPACT.equals(c.getSize()));
        //        System.out.println("compactCars - the lambda filter");
        //        System.out.println(compactCars);

        List<Car> largeCars = filterCars(cars, (Car c) -> LARGE.equals(c.getSize()));
        System.out.println("largeCars - the lambda filter");
        System.out.println(largeCars);

        List<Car> aboveMediumCars = filterCars(cars, FilteringCars::isBigCar);
        System.out.println("aboveeMediumCars - the lambda filter");
        System.out.println(aboveMediumCars);

        //         (Apple a) -> "green".equals(a.getColor()))
    }

    private static List<Car> filterCars(List<Car> cars, Predicate<Car> predicate) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (predicate.test(car)) {
                result.add(car);
            }
        }
        return result;
    }

    private static List<Car> filterSmallCars(List<Car> cars) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (COMPACT.equals(car.getSize()) || SUBCOMPACT.equals(car.getSize())) {
                result.add(car);
            }
        }
        return result;
    }

    private static List<Car> filterMediumCars(List<Car> carz) {
        List<Car> result = new ArrayList<>();
        for (Car car : carz) {
            if (MEDIUM.equals(car.getSize())) {
                result.add(car);
            }
        }
        return result;
    }

    private static boolean isBigCar(Car car) {
        return (LARGE.equals(car.getSize()) || LIKE_REALY_LARGE.equals(car.getSize()));
    }
}


