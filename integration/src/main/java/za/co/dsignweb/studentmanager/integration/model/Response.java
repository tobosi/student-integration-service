package za.co.dsignweb.studentmanager.integration.model;

import org.springframework.web.bind.annotation.ResponseStatus;

public class Response<T> {

    private final T data;
    private final ResponseType status;

    public Response(final T data, final ResponseType status) {
        this.data = data;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public ResponseType getStatus() {
        return status;
    }
}
