package co.edu.unicundi.proyectoSpringPrueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class HttpMediaTypeNotSupportedException extends Exception {

    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public HttpMediaTypeNotSupportedException(String message) {
        super(message);
    }
}
