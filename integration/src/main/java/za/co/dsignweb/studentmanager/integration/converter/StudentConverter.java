package za.co.dsignweb.studentmanager.integration.converter;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.dsignweb.studentmanager.integration.model.StudentRequest;
import za.co.dsignweb.studentmanager.integration.model.StudentResponse;
import za.co.dsignweb.studentmanager.model.api.request.StudentReq;
import za.co.dsignweb.studentmanager.model.api.response.StudentRes;

@Component
public class StudentConverter implements Converter<StudentRequest, StudentReq, StudentResponse, StudentRes> {

    private static final Logger LOG = LoggerFactory.getLogger(StudentConverter.class);

    @Override
    public StudentResponse toUI(final StudentRes response) {
        Integer countryCode = null;
        String nationalNo = null;
        try {
            final Phonenumber.PhoneNumber number = PhoneNumberUtil.getInstance().parse(response.cellphoneNo(), Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
            countryCode = number.getCountryCode();
            nationalNo = String.valueOf(number.getNationalNumber());
        } catch (final Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }

        return new StudentResponse(
                response.studentNo(),
                response.firstName(),
                response.lastName(),
                countryCode,
                (nationalNo) != null ? nationalNo : response.cellphoneNo(),
                response.email(),
                response.dob(),
                response.score(),
                response.average()
        );
    }

    @Override
    public StudentReq toBackend(final StudentRequest request) {
        return new StudentReq(
                request.name(),
                request.surname(),
                request.email(),
                request.mobile(),
                request.dateOfBirth(),
                request.currentScore()
        );
    }
}
