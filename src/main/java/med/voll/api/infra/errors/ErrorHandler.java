package med.voll.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import med.voll.api.infra.exceptions.IntegrityException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity ErrorHandler404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ErrorHandler400(MethodArgumentNotValidException e){
        List<NotValidErrorDTO> error = e.getFieldErrors().stream().map(NotValidErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity ErrorHandlerDatatype(HttpMessageNotReadableException e){
        InvalidDatatypeErrorDTO error = new InvalidDatatypeErrorDTO(e);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IntegrityException.class)
    public ResponseEntity ErrorHandlerIntegrity(IntegrityException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity ErrorHandlerIntegrity(ValidationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

