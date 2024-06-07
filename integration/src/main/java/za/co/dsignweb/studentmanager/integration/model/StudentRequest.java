package za.co.dsignweb.studentmanager.integration.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record StudentRequest(String name, String surname, String mobile, String email, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy") LocalDate dateOfBirth, int currentScore) {
}
