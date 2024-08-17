package pe.tato.spring_boot.crud.employees.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import pe.tato.spring_boot.crud.employees.dto.EmployeeRequestDTO;
import pe.tato.spring_boot.crud.employees.dto.EmployeeResponseDTO;
import pe.tato.spring_boot.crud.employees.models.Employee;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IEmployeeMapper {
	
	EmployeeResponseDTO toDTO(Employee employee);
	
	@InheritInverseConfiguration
	@Mapping(source = "contact.maritalStatusId", target = "contact.maritalStatus.maritalStatusId")
	@Mapping(source = "contact.genderId", target = "contact.gender.genderId")
	@Mapping(source = "contact.identificationDocumentId", target = "contact.identificationDocument.documentId")	
	@Mapping(source = "registrationStatus", target = "registrationStatus")	
	@Mapping(target  = "contact.employee", ignore = true)	
//	@Mapping(target  = "contact.createdAt", ignore = true)
//	@Mapping(target  = "createdAt", ignore = true)
	Employee toEntity(EmployeeRequestDTO employeeRequestDTO);
	
	List<EmployeeResponseDTO> listEntityToDTO(List<Employee> employees);

}
