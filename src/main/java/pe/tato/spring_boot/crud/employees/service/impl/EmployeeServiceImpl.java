package pe.tato.spring_boot.crud.employees.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pe.tato.spring_boot.crud.employees.constants.RegistrationStatus;
import pe.tato.spring_boot.crud.employees.exception.ModelNotFoundException;
import pe.tato.spring_boot.crud.employees.models.Employee;
import pe.tato.spring_boot.crud.employees.repository.IGenericRepository;
import pe.tato.spring_boot.crud.employees.service.IEmployeeService;
import pe.tato.spring_boot.crud.employees.service.validation.EmployeeValidation;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl  extends CRUDImpl<Employee, Long> implements IEmployeeService{

	private final IGenericRepository<Employee, Long> repository;
	
	private final EmployeeValidation employeeValidation;
	
	@Override
	protected IGenericRepository<Employee, Long> getRepo() {
		return repository;
	}
	
	@Override
	public Employee create(Employee employee) {
		employeeValidation.validateCreate(employee);		
		return super.create(employee);
	}

	@Override
	public Employee readById(Long id) {
		return repository.findById(id).orElseThrow( () -> new ModelNotFoundException( "ID does not exist : " + id ));
	}
	
	@Transactional
	@Override
	public Employee update(Employee employee, Long id) {
		return Optional.ofNullable(this.readById(id))
				.map( original-> {
					employeeValidation.validateUpdate(employee);
					employee.setCreatedAt( original.getCreatedAt() );
					employee.setRegistrationStatus( employee.getRegistrationStatus().isEmpty() ? RegistrationStatus.ACTIVE.getCode() : employee.getRegistrationStatus() );
					employee.setIdUser( employee.getIdUser() == null ? 1L : employee.getIdUser() );
					
					employee.getContact().setCreatedAt(original.getContact().getCreatedAt());
					employee.getContact().setRegistrationStatus(employee.getContact().getRegistrationStatus().isEmpty() ? RegistrationStatus.ACTIVE.getCode() : employee.getContact().getRegistrationStatus());
					employee.getContact().setIdUser( employee.getContact().getIdUser() == null ? 1L : employee.getContact().getIdUser());
					
					BeanUtils.copyProperties(employee, original);
					return super.update(original, id);
				}).get();
	}
	
	@Override
	public void delete(Long id) {
		 Optional.ofNullable(this.readById(id))
	        .ifPresentOrElse(
	            employee -> super.delete(employee),
	            () -> { throw new ModelNotFoundException("ID does not exist : " + id); }
	        );
	}
	

}
