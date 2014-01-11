package lambdasinaction.chap7;

public interface Sized{
    public int size();
    public default boolean isEmpty() { 
        return size() == 0; 
    }
}

