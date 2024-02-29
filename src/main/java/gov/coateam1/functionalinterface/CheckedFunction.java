package gov.coateam1.functionalinterface;

@FunctionalInterface
public interface CheckedFunction<T,R,E extends Exception> {

    R apply(T t) throws E;
}
