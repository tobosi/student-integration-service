package za.co.dsignweb.studentmanager.integration.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.dsignweb.studentmanager.api.IRestCreate;
import za.co.dsignweb.studentmanager.integration.converter.ScoreConverter;
import za.co.dsignweb.studentmanager.integration.helpers.ScoreFacade;
import za.co.dsignweb.studentmanager.integration.model.ScoreRequest;
import za.co.dsignweb.studentmanager.integration.model.ScoreResponse;

@CrossOrigin
@RestController
@RequestMapping("/manage/scores")
public class ScoreController implements IRestCreate<ScoreResponse, ScoreRequest> {

    private final ScoreFacade scoreFacade;
    private final ScoreConverter scoreConverter;

    public ScoreController(final ScoreFacade scoreFacade,
                           final ScoreConverter scoreConverter) {
        this.scoreFacade = scoreFacade;
        this.scoreConverter = scoreConverter;
    }


    @Override
    public ResponseEntity<ScoreResponse> create(@Valid final ScoreRequest request) {
        return ResponseEntity.ok(scoreFacade.create(scoreConverter.toBackend(request)));
    }
}
