package pe.tato.spring_boot.crud.employees.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.tato.spring_boot.crud.employees.constants.ValidationMessage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class Audit {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	protected LocalDateTime createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = true)
	protected LocalDateTime updatedAt;

	@Column(name = "registration_status ", nullable = false, length = 1)
	protected String registrationStatus;

	//@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
	@Min(value = 1, message = ValidationMessage.GREATER_THAN_ONE)
	@Column(name = "id_user", nullable = false)
	protected Long idUser;
	
}
