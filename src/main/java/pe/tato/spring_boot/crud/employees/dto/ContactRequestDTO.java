package pe.tato.spring_boot.crud.employees.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.tato.spring_boot.crud.employees.constants.ValidationMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper =  false)
public class ContactRequestDTO extends AuditRequestDTO{
	
	private Long contactId;
	
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
	private String name;
	
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
	private String lastName;
	
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
	private String phone;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDate;
	
	@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	private Long maritalStatusId;
	
	@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	private Long genderId;
	
	@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	private Long identificationDocumentId;
	
	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
    @NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
	private String document;
	
}
