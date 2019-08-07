package learncode.chap3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
