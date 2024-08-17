package pe.tato.spring_boot.crud.employees.constants;

public enum RegistrationStatus {

	ACTIVE("A", "Active"),
	INACTIVE("I", "Inactive");

	private final String code;
	private final String description;

	RegistrationStatus(String code, String description) {
	        this.code = code;
	        this.description = description;
	    }

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
