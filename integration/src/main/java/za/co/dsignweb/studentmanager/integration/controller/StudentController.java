package za.co.dsignweb.studentmanager.integration.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.dsignweb.studentmanager.api.RestCrud;
import za.co.dsignweb.studentmanager.integration.helpers.StudentFacade;
import za.co.dsignweb.studentmanager.integration.converter.StudentConverter;
import za.co.dsignweb.studentmanager.integration.model.StudentRequest;
import za.co.dsignweb.studentmanager.integration.model.StudentResponse;
import za.co.dsignweb.studentmanager.model.api.request.SearchCriteriaReq;
import za.co.dsignweb.studentmanager.model.api.response.Response;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/manage/students")
public class StudentController implements RestCrud<StudentResponse, StudentRequest> {

    private final StudentFacade studentFacade;
    private final StudentConverter studentConverter;

    public StudentController(final StudentFacade studentFacade,
                             final StudentConverter studentConverter) {
        this.studentFacade = studentFacade;
        this.studentConverter = studentConverter;
    }

    @Override
    public ResponseEntity<StudentResponse> get(final String refNo) {
        return ResponseEntity.ok(studentFacade.get(refNo));
    }

    @Override
    public ResponseEntity<Response<List<StudentResponse>>> getAll(final int page, final int size) {
        return ResponseEntity.ok(studentFacade.getAll(page, size));
    }

    @Override
    public ResponseEntity<Response<List<StudentResponse>>> search(final SearchCriteriaReq searchCriteriaReq, final int page, final int size) {
        return ResponseEntity.ok(studentFacade.search(searchCriteriaReq, page, size));
    }

    @Override
    public ResponseEntity<StudentResponse> create(@Valid final StudentRequest request) {
        return ResponseEntity.ok(studentFacade.create(studentConverter.toBackend(request)));
    }

    @Override
    public ResponseEntity<StudentResponse> update(final String refNo, @Valid final StudentRequest request) {
        return ResponseEntity.ok(studentFacade.update(studentConverter.toBackend(request), refNo));
    }

    @Override
    public ResponseEntity<Void> delete(final String refNo) {
        studentFacade.delete(refNo);
        return ResponseEntity.noContent().build();
    }
}
