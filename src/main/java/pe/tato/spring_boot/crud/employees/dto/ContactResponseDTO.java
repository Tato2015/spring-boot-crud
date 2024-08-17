package pe.tato.spring_boot.crud.employees.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@EqualsAndHashCode(callSuper = false)
public class ContactResponseDTO extends AuditResponseDTO {

	@EqualsAndHashCode.Include
	@NotNull(message = ValidationMessage.CAN_T_BE_NULL)
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

	private MaritalStatusResponseDTO maritalStatus;

	private GenderResponseDTO gender;

	private IdentificationDocumentResponseDTO identificationDocument;

	@NotEmpty(message = ValidationMessage.NONEMPTY_STRING)
	@NotBlank(message = ValidationMessage.NOWHITESPACES_STRING)
	private String document;

	public ContactResponseDTO(Long contactId, String name, String lastName, String phone, LocalDate birthDate,
			MaritalStatusResponseDTO maritalStatus, GenderResponseDTO gender,
			IdentificationDocumentResponseDTO identificationDocument, String document, LocalDateTime createdAt,
			LocalDateTime updatedAt, String registrationStatus, Long idUser) {
		this.contactId = contactId;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.birthDate = birthDate;
		this.maritalStatus = maritalStatus;
		this.gender = gender;
		this.identificationDocument = identificationDocument;
		this.document = document;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.registrationStatus = registrationStatus;
		this.idUser = idUser;
	}

}
