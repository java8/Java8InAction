package lambdasinaction.chap8;

public interface Sized{
    public int size();
    public default boolean isEmpty() {
        return size() == 0;
    }
}

