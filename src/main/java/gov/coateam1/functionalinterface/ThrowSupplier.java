package gov.coateam1.functionalinterface;

import java.util.function.Supplier;

public class ThrowSupplier{

    private ThrowSupplier(){}

    public static <T,E extends Exception> Supplier<T> throwingSupplier(CheckedSupplier<T,E> checkedSupplier){
        return () -> {
            try {
                return checkedSupplier.get();
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        };
    }
}
