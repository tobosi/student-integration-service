package za.co.dsignweb.studentmanager.integration.converter;

public interface Converter<F, B, T, R> {

    public T toUI(R response);

    public B toBackend(F request);
}
