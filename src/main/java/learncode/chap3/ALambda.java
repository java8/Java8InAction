package learncode.chap3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description TODO
 * @Author YC
 * @Date 2019/8/5 23:42
 * @Version 1.0
 */
public class ALambda {

    public static void main(String[] args) throws IOException {
        Runnable r1 = () -> System.out.println("Hello world");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world 2");
            }
        };

        process(r1);
        process(r2);
        process(()-> System.out.println("Hello world 3"));

        String s1 = processFile((BufferedReader br) -> br.readLine());

//        (List<String> list) -> list.isEmpty()
    }

    public static List<String> result(List<String> list, Predicate<String> p) {
        List<String> lists = new ArrayList<>();
        for (String s : list) {
            if (p.test(s)) {
                list.add(s);
            }
        }
        return lists;
    }

    public static void process(Runnable r) {
        r.run();
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

}
