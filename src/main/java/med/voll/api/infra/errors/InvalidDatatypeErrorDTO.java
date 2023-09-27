package med.voll.api.infra.errors;

import org.springframework.http.converter.HttpMessageNotReadableException;

public record InvalidDatatypeErrorDTO(String message) {
    public InvalidDatatypeErrorDTO(HttpMessageNotReadableException e) {
        this(e.getMessage());
    }
}
