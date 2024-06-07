package za.co.dsignweb.studentmanager.integration.converter;

import org.springframework.stereotype.Component;
import za.co.dsignweb.studentmanager.integration.model.ScoreRequest;
import za.co.dsignweb.studentmanager.integration.model.ScoreResponse;
import za.co.dsignweb.studentmanager.model.api.request.ScoreReq;
import za.co.dsignweb.studentmanager.model.api.response.ScoreRes;

@Component
public class ScoreConverter implements Converter<ScoreRequest, ScoreReq, ScoreResponse, ScoreRes> {

    @Override
    public ScoreResponse toUI(final ScoreRes response) {
        return new ScoreResponse(
                response.refNo(),
                response.score(),
                response.studentNo()
        );
    }

    @Override
    public ScoreReq toBackend(final ScoreRequest request) {
        return new ScoreReq(
                request.score(),
                request.studentNo()
        );
    }
}
