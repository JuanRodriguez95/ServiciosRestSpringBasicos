package co.edu.unicundi.proyectoSpringPrueba.exception;

public class HttpRequestMethodNotSupportedException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    

    public HttpRequestMethodNotSupportedException(String message){
        super(message);
    }

}
