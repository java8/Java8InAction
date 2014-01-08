package lambdasinaction.chap7;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Removeable<T> extends Iterable<T>{
    public default boolean removeIf(Predicate<? super T> filter){
        boolean removed = false; 
        Iterator<T> each = iterator(); 
        while(each.hasNext()) { 
            if(filter.test(each.next())) { 
                each.remove(); 
                removed = true; 
            } 
        } 
        return removed; 
    }
}
