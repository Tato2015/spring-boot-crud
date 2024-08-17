package pe.tato.spring_boot.crud.employees.exception;

public class ModelNotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4600585579637986546L;

	public  ModelNotFoundException(String message) {
        super(message);
    }
    
}
