package lambdasinaction.chap9;

import java.util.function.Function;

public class Letter{
    public static String addHeader(String text){
        return "From Raoul, Mario and Alan:" + text;
    }

    public static String addFooter(String text){
        return text + "Kind regards";
    }

    public static String checkSpelling(String text){
        return text.replaceAll("C\\+\\+", "**Censored**");
    }


    public static void main(String...args){
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
          = addHeader.andThen(Letter::checkSpelling)
                     .andThen(Letter::addFooter);

        System.out.println(transformationPipeline.apply("C++ stay away from me!"));
    }

}
