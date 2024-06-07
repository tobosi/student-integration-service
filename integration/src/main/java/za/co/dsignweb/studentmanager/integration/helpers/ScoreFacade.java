package za.co.dsignweb.studentmanager.integration.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import za.co.dsignweb.studentmanager.client.ScoreClient;
import za.co.dsignweb.studentmanager.integration.converter.ScoreConverter;
import za.co.dsignweb.studentmanager.integration.model.ScoreResponse;
import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;
import za.co.dsignweb.studentmanager.model.api.response.ScoreRes;

import java.util.Objects;

@Component
public class ScoreFacade {

    private final ScoreClient scoreClient;
    private final ScoreConverter scoreConverter;

    public ScoreFacade(final ScoreClient scoreClient,
                       final ScoreConverter scoreConverter) {
        this.scoreClient = scoreClient;
        this.scoreConverter = scoreConverter;
    }

    public ScoreResponse create(final ScoreReq request) {
        final ResponseEntity<ScoreRes> response = scoreClient.create(request);
        return scoreConverter.toUI(Objects.requireNonNull(response.getBody()));
    }
}
