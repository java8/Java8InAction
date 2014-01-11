package lambdasinaction.chap7;

import java.util.function.Consumer;

public interface BasicList<T>{
    public void add(T t);
    public int size();
    //TODO: uncomment, read the README for instructions
    //public void forEach(Consumer<T> action);
}

