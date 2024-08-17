package pe.tato.spring_boot.crud.employees.service;

import pe.tato.spring_boot.crud.employees.commons.IBaseInterfaceService;
import pe.tato.spring_boot.crud.employees.models.Employee;

public interface IEmployeeService extends IBaseInterfaceService<Employee, Long>{

	Employee readById(Long id);
	
	Employee update(Employee employee,Long id);
	
	void delete(Long id);
	
}
