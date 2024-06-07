package za.co.dsignweb.studentmanager.integration.helpers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import za.co.dsignweb.studentmanager.client.StudentClient;
import za.co.dsignweb.studentmanager.integration.converter.StudentConverter;
import za.co.dsignweb.studentmanager.integration.model.ResponseType;
import za.co.dsignweb.studentmanager.integration.model.StudentResponse;
import za.co.dsignweb.studentmanager.model.api.request.SearchCriteriaReq;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.model.api.response.Response;
import za.co.dsignweb.studentmanager.model.api.response.StudentRes;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentFacade {

    private final StudentClient studentClient;
    private final StudentConverter studentConverter;

    public StudentFacade(final StudentClient studentClient,
                         final StudentConverter studentConverter) {
        this.studentClient = studentClient;
        this.studentConverter = studentConverter;
    }

    public StudentResponse get(final String studentNo) {
        final ResponseEntity<StudentRes> response = studentClient.get(studentNo);
        return handleResponse(response.getBody(), response.getStatusCode());
    }

    public Response<List<StudentResponse>> getAll(final int page, final int size) {
        final ResponseEntity<Response<List<StudentRes>>> response = studentClient.getAll(page, size);
        return handleResponse(response.getBody(), response.getStatusCode());
    }

    public Response<List<StudentResponse>> search(final SearchCriteriaReq criteria, final int page, final int size) {
        final ResponseEntity<Response<List<StudentRes>>> response = studentClient.search(criteria, page, size);
        return handleResponse(response.getBody(), response.getStatusCode());
    }

    public StudentResponse create(final StudentReq request) {
        final ResponseEntity<StudentRes> response = studentClient.create(request);
        return handleResponse(response.getBody(), response.getStatusCode());
    }

    public StudentResponse update(final StudentReq request, final String studentNo) {
        final ResponseEntity<StudentRes> response = studentClient.update(studentNo, request);
        return handleResponse(response.getBody(), response.getStatusCode());
    }

    public void delete(final String studentNo) {
        studentClient.delete(studentNo);
    }

    private Response<List<StudentResponse>> handleResponse(final Response<List<StudentRes>> responses, final HttpStatusCode code) {
        if (responses == null) {
            return new Response<>(0, 0, new ArrayList<>());
        }

        return getResponse(responses);
    }

    private StudentResponse handleResponse(final StudentRes response, final HttpStatusCode code) {
        return getResponse(response, getResponseType(code));
    }

    private ResponseType getResponseType(final HttpStatusCode statusCode) {
        return (statusCode.is2xxSuccessful() ? ResponseType.SUCCESS:ResponseType.ERROR);
    }

    private StudentResponse getResponse(final StudentRes response, final  ResponseType responseType) {
        return studentConverter.toUI(response);
    }

    private Response<List<StudentResponse>> getResponse(final Response<List<StudentRes>> responses) {
        return new Response<>(responses.getLength(), responses.getTotalPages(), responses.getContent().stream().map(studentConverter::toUI).toList());
    }

}
