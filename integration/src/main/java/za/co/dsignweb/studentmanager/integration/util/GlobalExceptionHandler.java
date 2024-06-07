package za.co.dsignweb.studentmanager.integration.util;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import za.co.dsignweb.studentmanager.api.ApiError;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(final FeignException e, final WebRequest webRequest) throws URISyntaxException {
        return getApiError(e, HttpStatus.BAD_REQUEST, webRequest);
    }

    private ResponseEntity<ApiError> getApiError(final Exception e, final HttpStatus httpStatus, final WebRequest webRequest) throws URISyntaxException {
        e.printStackTrace();
        final String repsonse = (e instanceof FeignException) ?
                ((((FeignException) e).responseBody().isPresent()) ? new String(((FeignException) e).responseBody().get().array(), StandardCharsets.UTF_8) : null) : e.getMessage();

        return new ResponseEntity<>(new ApiError(httpStatus, e, repsonse,  webRequest), httpStatus);
    }
}
