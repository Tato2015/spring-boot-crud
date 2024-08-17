package pe.tato.spring_boot.crud.employees.service.validation;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.tato.spring_boot.crud.employees.exception.ModelNotFoundException;
import pe.tato.spring_boot.crud.employees.models.Contact;
import pe.tato.spring_boot.crud.employees.models.Employee;
import pe.tato.spring_boot.crud.employees.repository.IContactRepository;
import pe.tato.spring_boot.crud.employees.repository.IEmployeeRepository;
import pe.tato.spring_boot.crud.employees.repository.IGenderRepository;
import pe.tato.spring_boot.crud.employees.repository.IIdentificationDocumentRepository;
import pe.tato.spring_boot.crud.employees.repository.IMaritalStatusRepository;

@Service
@RequiredArgsConstructor
public class EmployeeValidation {
	
	private final IMaritalStatusRepository maritalStatusRepository;
	
	private final IGenderRepository genderRepository;
	
	private final IIdentificationDocumentRepository identificationDocumentRepository;
	
	private final IEmployeeRepository employeeRepository;
	
	private final IContactRepository contactRepository;
	
	public void checkMaritalStatusValid(Long maritalStatusId) {
		 maritalStatusRepository.findById(maritalStatusId).ifPresentOrElse(
	            maritalStatus -> {},
	            () -> { throw new ModelNotFoundException(" Marital Status ID does not exist: " + maritalStatusId);}
	        );
	}
	
	public void checkGenderValid(Long genderId) {
		genderRepository.findById(genderId).ifPresentOrElse( 
				gender -> {},
	            () -> { throw new ModelNotFoundException(" Gender ID does not exist: " + genderId);}
	        );
	}

	
	public void checkIdentificationDocumentValid(Long identificationDocumentId) {
		identificationDocumentRepository.findById(identificationDocumentId).ifPresentOrElse(
				document -> {},
	            () -> { throw new ModelNotFoundException(" Document ID does not exist: " + identificationDocumentId);}
				);
	}
	
	public void checkManagerValid(Long managerId) {
		employeeRepository.findById(managerId).ifPresentOrElse(
				employee -> {}, 
				() -> { throw new ModelNotFoundException(" Manager ID does not exist : " + managerId); } 
				);
	}
	
	public void checkContactValid(Contact contact) {
		Optional.ofNullable(contact).orElseThrow(() -> new ModelNotFoundException("Contact object is null"));
		 Optional.ofNullable(contact.getContactId()).orElseThrow(() -> new ModelNotFoundException("Contact ID does not exist"));
		
		contactRepository.findById(contact.getContactId()).ifPresentOrElse(
				contactDB -> {}, 
				() -> { throw new ModelNotFoundException(" Contact ID does not exist : " + contact.getContactId()); }
				);
	}
	
	public void validateCreate(Employee employee) {
		checkMaritalStatusValid(employee.getContact().getMaritalStatus().getMaritalStatusId());
		checkGenderValid(employee.getContact().getGender().getGenderId());
		checkIdentificationDocumentValid(employee.getContact().getIdentificationDocument().getDocumentId());		
		checkManagerValid(employee.getManagerId());	
	}
	
	public void validateUpdate(Employee employee) {
		checkMaritalStatusValid(employee.getContact().getMaritalStatus().getMaritalStatusId());
		checkGenderValid(employee.getContact().getGender().getGenderId());
		checkIdentificationDocumentValid(employee.getContact().getIdentificationDocument().getDocumentId());		
		checkManagerValid(employee.getManagerId());
		checkContactValid(employee.getContact());
	}
	
}
