package pe.tato.spring_boot.crud.employees.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pe.tato.spring_boot.crud.employees.constants.ValidationMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeRequestDTO extends AuditRequestDTO {

	private Long employeeId;

	private ContactRequestDTO contact;

	@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	private Long managerId;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate hireDate;
}
