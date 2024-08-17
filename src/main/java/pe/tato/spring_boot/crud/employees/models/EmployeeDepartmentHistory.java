package pe.tato.spring_boot.crud.employees.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "employee_department_history")
public class EmployeeDepartmentHistory extends Audit{
	
	@EmbeddedId
	private EmployeeDepartmentHistoryId employeeDepartmentHistoryId; 
	
	private LocalDate startDate;
	
	private LocalDate endDate;

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
