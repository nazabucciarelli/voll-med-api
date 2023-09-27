package med.voll.api.infra.errors;

import org.springframework.validation.FieldError;

public record NotValidErrorDTO(String field, String error) {

    public NotValidErrorDTO(FieldError e){
        this(e.getField(),e.getDefaultMessage());
    }

}
