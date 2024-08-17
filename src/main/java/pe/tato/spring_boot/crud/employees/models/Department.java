package pe.tato.spring_boot.crud.employees.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "department")
public class Department extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "sequenceDepartment", sequenceName = "department_seq", allocationSize = 1)
	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;

	@PrePersist
	public void prePersistence() {
		this.createdAt = LocalDateTime.now();
		this.registrationStatus = RegistrationStatus.ACTIVE.getCode();
	}

	@PreUpdate
	public void preModify() {
		this.updatedAt = LocalDateTime.now();
	}

}
