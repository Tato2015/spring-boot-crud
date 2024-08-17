package pe.tato.spring_boot.crud.employees.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditResponseDTO {

	@JsonFormat(pattern = "dd-MM-yyyy")
	protected LocalDateTime createdAt;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	protected LocalDateTime updatedAt;
	
	protected String registrationStatus;
	
	protected Long idUser;
	
}
