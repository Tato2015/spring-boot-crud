package pe.tato.spring_boot.crud.employees.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class EmployeeResponseDTO extends AuditResponseDTO {

	@EqualsAndHashCode.Include
	@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	private Long employeeId;

	private ContactResponseDTO contact;

	@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	private Long managerId;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate hireDate;
	
	public EmployeeResponseDTO(Long employeeId,ContactResponseDTO contact,Long managerId,LocalDate hireDate,
			LocalDateTime createdAt,LocalDateTime updatedAt,String registrationStatus,
			Long idUser) {
		this.employeeId = employeeId;
		this.contact = contact;
		this.managerId = managerId;
		this.hireDate = hireDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.registrationStatus = registrationStatus;
		this.idUser = idUser;
	}

}
