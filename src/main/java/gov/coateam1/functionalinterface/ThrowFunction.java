package gov.coateam1.functionalinterface;

import java.util.function.Function;

public class ThrowFunction {

    public static  <T,R,E extends Exception> Function<T,R> throwingFunction(CheckedFunction<T,R,E> checkedFunction){

        return empDTO->{
            try {
                return checkedFunction.apply(empDTO);
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        };
    }
}
