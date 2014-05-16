package lambdasinaction.chap13;


import java.util.function.Function;
import java.util.function.Supplier;

public class PatternMatching {


    static class Expr {  }
    static class Number extends Expr { int val;  }
    static class BinOp extends Expr { String opname; Expr left, right; }



    static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

    static interface TriFunction<S, T, U, R>{
        R apply(S s, T t, U u);
    }
    static <T> T PatternMatchExpr( Expr e,
                                   TriFunction<String,Expr,Expr,T> binopcase, Function<Integer,T> numcase,
                                   Supplier<T> defaultcase) {

        if(e instanceof BinOp){
                return binopcase.apply(((BinOp)e).opname, ((BinOp)e).left, ((BinOp)e).right);
        }
        else if(e instanceof Number){
                return numcase.apply(((Number)e).val);
        }
        else{
            return defaultcase.get();
        }
    }

}
