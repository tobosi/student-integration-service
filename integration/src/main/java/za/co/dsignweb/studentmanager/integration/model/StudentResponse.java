package za.co.dsignweb.studentmanager.integration.model;

public record StudentResponse(
        String studentNo,
        String name,
        String surname,
        Integer countryCode,
        String mobile,
        String email,
        String dateOfBirth,
        int currentScore,
        double average) {
}
