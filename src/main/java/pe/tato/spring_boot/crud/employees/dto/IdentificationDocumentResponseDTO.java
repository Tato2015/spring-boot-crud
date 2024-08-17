package pe.tato.spring_boot.crud.employees.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.tato.spring_boot.crud.employees.constants.ValidationMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IdentificationDocumentResponseDTO {
	
	@EqualsAndHashCode.Include
    @NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	private Long documentId;
	
	private String code;
	
	private String description;
	
}
