package med.voll.api.infra.exceptions;

public class IntegrityException extends RuntimeException {

    public IntegrityException(String msg){
        super(msg);
    }
}
