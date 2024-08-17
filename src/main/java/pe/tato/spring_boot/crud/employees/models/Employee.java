package pe.tato.spring_boot.crud.employees.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table( name = "employee")
public class Employee extends Audit{

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	@SequenceGenerator(name = "sequenceEmployee", sequenceName = "employee_seq", allocationSize = 1)
    @Column( name = "employee_id" )
    private Long employeeId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
	private Contact contact;
	
	@Column(name = "manager_id")
	private Long managerId;
	
	@Column( name = "hire_date" )
	private LocalDate hireDate;
	
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
