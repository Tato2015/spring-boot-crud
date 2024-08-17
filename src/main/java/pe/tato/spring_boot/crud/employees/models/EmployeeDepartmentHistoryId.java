package pe.tato.spring_boot.crud.employees.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDepartmentHistoryId {
	
	private Long employeeId;
	
	private Long departmentId;

}
