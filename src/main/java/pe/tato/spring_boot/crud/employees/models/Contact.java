package pe.tato.spring_boot.crud.employees.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pe.tato.spring_boot.crud.employees.constants.RegistrationStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "contact")
public class Contact extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "sequenceContact", sequenceName = "contact_seq", allocationSize = 1)
	@Column(name = "contact_id")
	private Long contactId;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@ManyToOne
	@JoinColumn(name = "marital_status_id")
	private MaritalStatus maritalStatus;

	@ManyToOne
	@JoinColumn(name = "gender_id")
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "document_id")
	private IdentificationDocument identificationDocument;

	@Column(name = "document")
	private String document;

	@OneToOne(mappedBy = "contact")
	private Employee employee;

	@PrePersist
	public void prePersistence() {
		this.createdAt = LocalDateTime.now();
		this.registrationStatus = RegistrationStatus.ACTIVE.getCode();
		this.idUser = 1L;
	}

	@PreUpdate
	public void preModify() {
		this.updatedAt = LocalDateTime.now();
	}
}
