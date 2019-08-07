package learncode.commons;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/3 17:53
 * @Version 1.0
 */
public class Apple {
    private Integer weight;
    private String color;

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
