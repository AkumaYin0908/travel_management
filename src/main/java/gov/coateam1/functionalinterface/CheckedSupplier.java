package gov.coateam1.functionalinterface;

public interface CheckedSupplier <T, E extends Exception>{

    T get() throws Exception;
}
