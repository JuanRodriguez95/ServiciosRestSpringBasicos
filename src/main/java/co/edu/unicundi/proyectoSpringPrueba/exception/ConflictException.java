package co.edu.unicundi.proyectoSpringPrueba.exception;

import org.springframework.web.context.request.ServletWebRequest;

public class ConflictException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
     

    public ConflictException(String message){
        super(message);
        
    }

  

}
