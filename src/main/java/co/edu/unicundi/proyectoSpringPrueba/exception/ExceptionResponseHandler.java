package co.edu.unicundi.proyectoSpringPrueba.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.edu.unicundi.proyectoSpringPrueba.dto.ExceptionResponse;

@ControllerAdvice
@RestController
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ExceptionResponse> filtroModelNotFoundException(ModelNotFoundException ex, WebRequest  request){
        ExceptionResponse exp = new ExceptionResponse("404", "NOT_FOUND",ex.getMessage() , "URL");
        return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(NotSupportedException.class)
    public ResponseEntity<ExceptionResponse> filtroNotSupportedException(NotSupportedException ex, WebRequest  request){
        ExceptionResponse exp = new ExceptionResponse("415", "UNSUPPORTED_MEDIA_TYPE",ex.getMessage() , "URL");
        return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> filtroException(Exception ex, WebRequest  request){
        ExceptionResponse exp = new ExceptionResponse("415", "UNSUPPORTED_MEDIA_TYPE",ex.getMessage() , "URL");
        return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
