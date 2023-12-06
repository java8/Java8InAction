package lambdasinaction.chap2;

import java.util.*;
import static lambdasinaction.chap2.Colour.*;

public class FilteringApples{

	public static void main(String ... args){

		List<Apple> inventory = Arrays.asList(new Apple(80,GREEN),
									new Apple(155, GREEN),
									new Apple(120, RED));

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples = filterApplesByColor(inventory, GREEN);
		System.out.println(greenApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples = filterApplesByColor(inventory, RED);
		System.out.println(redApples);

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
		System.out.println(greenApples2);

		// [Apple{color='green', weight=155}]
		List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
		System.out.println(heavyApples);

		// []
		List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redAndHeavyApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
			public boolean test(Apple a){
				return a.getColor().equals("red"); 
			}
		});
		System.out.println(redApples2);

	}

	public static List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(GREEN.equals(apple.getColor())){
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, Colour color){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getColor().equals(color)){
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getWeight() > weight){
				result.add(apple);
			}
		}
		return result;
	}


	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}       

	public static class Apple {
		private int weight = 0;
		private Colour color;

		public Apple(int weight, Colour color){
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public Colour getColor() {
			return color;
		}

		public void setColor(Colour color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
	}

	interface ApplePredicate{
		public boolean test(Apple a);
	}

	static class AppleWeightPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return apple.getWeight() > 150; 
		}
	}
	static class AppleColorPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return GREEN.equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return "red".equals(apple.getColor()) 
					&& apple.getWeight() > 150; 
		}
	}
}
