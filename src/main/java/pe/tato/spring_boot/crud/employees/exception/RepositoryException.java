package pe.tato.spring_boot.crud.employees.exception;

public class RepositoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7020731865315136036L;

	public RepositoryException(String message){
        super(message);
    }
    
}