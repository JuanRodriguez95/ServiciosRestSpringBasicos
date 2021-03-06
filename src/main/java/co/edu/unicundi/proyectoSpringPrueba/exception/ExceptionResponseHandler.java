package co.edu.unicundi.proyectoSpringPrueba.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.edu.unicundi.proyectoSpringPrueba.dto.ExceptionResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler{
    

    //@Autowired
    //private DefaultErrorAttributes defaultErrorAttributes;

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ExceptionResponse> filtroModelNotFoundException(ModelNotFoundException ex, WebRequest  request){        
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),ex.getMessage() ,((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse> filtroModelNotFoundException(DataNotFoundException ex, WebRequest  request){        
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),ex.getMessage() ,((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionResponse> filtroConflictException(ConflictException ex, WebRequest  request){   
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(),ex.getMessage(),((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.CONFLICT);
    }

    
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> filtroException(Exception ex, WebRequest  request){
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),ex.getMessage(),((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @Override
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            org.springframework.web.HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {                       
        ExceptionResponse exp = new ExceptionResponse(status.value(),status.name(),"Formato no soportado, no permitido",((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }



    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
           HttpHeaders headers, HttpStatus status, WebRequest request) {
            ExceptionResponse exp = new ExceptionResponse(status.value(),status.name(),"Metodo no permitido, el recurso solicitado no corresponde a la peticion realizada",((ServletWebRequest)request).getRequest()
            .getRequestURI().toString());
            return new ResponseEntity<>(exp,HttpStatus.METHOD_NOT_ALLOWED);
   }

   @Override
   protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
           HttpStatus status, WebRequest request) {
            ExceptionResponse exp = new ExceptionResponse(status.value(),status.name(),"Recurso no encontrado",((ServletWebRequest)request).getRequest().getRequestURI().toString());
            return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
   }

   @Override
   protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
           HttpHeaders headers, HttpStatus status, WebRequest request) {
            ExceptionResponse exp = new ExceptionResponse(status.value(),status.name(),"Error en la solicitud, JSON mal formado",((ServletWebRequest)request).getRequest().getRequestURI().toString());
            return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
   }


   @Override
   protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
           HttpStatus status, WebRequest request) {
       ExceptionResponse exp = new ExceptionResponse(status.value(),status.name(),"Error interno en el servidor",((ServletWebRequest)request).getRequest().getRequestURI().toString());
       return new ResponseEntity<>(exp,HttpStatus.INTERNAL_SERVER_ERROR);
   }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
                Map<String, String> errors = new HashMap<>();              
                for (ObjectError error : ex.getBindingResult().getAllErrors()) {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }         
                
                ExceptionResponse exp = new ExceptionResponse(status.value(),status.name(),errors,((ServletWebRequest)request).getRequest().getRequestURI().toString()); 
                return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException e,WebRequest  request) {
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(),NestedExceptionUtils.getMostSpecificCause(e).getMessage(),((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ExceptionResponse> filtroEmptyListException(EmptyListException ex, WebRequest  request){   
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name(),ex.getMessage(),((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(PageException.class)
    public ResponseEntity<ExceptionResponse> filtroPageException(PageException ex, WebRequest  request){   
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),ex.getMessage(),((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
    }
/*
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse> filtroDataNotFound(DataNotFoundException ex, WebRequest  request){   
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),ex.getMessage(),((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
    }
    */

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> filtroOrderNotFound(OrderNotFoundException ex, WebRequest  request){   
        ExceptionResponse exp = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),ex.getMessage(),((ServletWebRequest)request).getRequest().getRequestURI().toString());
        return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
    }
}
