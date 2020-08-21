package lambdasinaction.chap2;

import java.util.Arrays;
import java.util.List;

/** 测试2.1
 * @author dianedi
 * @date 2020/8/21 23:06
 * @Destription 根据苹果的大小，颜色输出不同的描述语句
 */
public class Test21 {
    static List<Apple> appleList = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));

    public static void main(String[] args) {
        OutPutApple(appleList,new AppleFormat());
    }
    public static void OutPutApple(List<Apple> appleList,ApplePredicate predicate){
        for (Apple apple: appleList){
          System.out.println("This is "+predicate.Format(apple)+".");
        }
    }
}
interface  ApplePredicate{
    public String Format(Apple apple);
}

class AppleFormatForColor implements ApplePredicate{

    @Override
    public String Format(Apple apple) {
        if (apple.getColor() == "green"){
            return "Green";
        }else {
            return "Red";
        }
    }
}
class AppleFormatForWeight implements ApplePredicate{
    @Override
    public String Format(Apple apple) {
        if (apple.getWeight() > 100){
            return "Big";
        }else{
            return "Small";
        }
    }
}

//看了答案，可以把ForColor与ForWeight整合起来
class AppleFormat implements ApplePredicate{

    @Override
    public String Format(Apple apple) {
        String isBig = apple.getWeight() > 120 ? "Big" : "Small";
        return "A "+ apple.getColor() +" and "+isBig+" apple";
    }
}
//但是不断地写这种内部类也挺烦的....

class Apple{
   private String color = "";
   private int weight = 0;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(int weight,String color) {
        this.color = color;
        this.weight = weight;
    }
}
