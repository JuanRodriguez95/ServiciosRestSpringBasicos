package co.edu.unicundi.proyectoSpringPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoHandlerFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoHandlerFoundException(String message){
        super(message);
    }

    
}
