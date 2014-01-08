package lambdasinaction.chap7;
import java.util.function.Consumer;

public interface Foreachable<T> extends Iterable<T>{
    public default void forEach(Consumer<? super T> block){
        for(T e: this){
            block.accept(e);
        }
    }
}
