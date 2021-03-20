package co.edu.unicundi.proyectoSpringPrueba.exception;

public class WebApplicationException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    

    public WebApplicationException(String message){
        super(message);
        
    }
}
