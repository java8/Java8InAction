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

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
