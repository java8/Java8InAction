package lambdasinaction.chap7;

import java.util.List;
import java.util.ArrayList;

public class PrintingList<T> implements BasicList<T> {
    private List<T> internalList = new ArrayList<>();

    public void add(T t){
        System.out.println("Adding an element");
        this.internalList.add(t);      
    }

    public int size(){
        System.out.println("Size of list");
        return internalList.size();
    }
}

