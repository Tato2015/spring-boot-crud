package pe.tato.spring_boot.crud.employees.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.tato.spring_boot.crud.employees.commons.PaginationModel;
import pe.tato.spring_boot.crud.employees.dto.EmployeeRequestDTO;
import pe.tato.spring_boot.crud.employees.dto.EmployeeResponseDTO;
import pe.tato.spring_boot.crud.employees.mapper.IEmployeeMapper;
import pe.tato.spring_boot.crud.employees.models.Employee;
import pe.tato.spring_boot.crud.employees.service.IEmployeeService;
import pe.tato.spring_boot.crud.employees.service.impl.EmployeePaginationServiceImpl;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

	private final IEmployeeService service;

	private final EmployeePaginationServiceImpl servicePagination;

	private final IEmployeeMapper mapper;

	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination) {
		log.info("PAGINATION ....." + pagination);
		Page<EmployeeResponseDTO> lst = servicePagination.pagination(pagination);
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<EmployeeResponseDTO> save(@RequestBody EmployeeRequestDTO dto) {
		Employee entity = this.service.create(this.mapper.toEntity(dto));
		EmployeeResponseDTO employeeResponseDTO = this.mapper.toDTO(entity);
		return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeResponseDTO>> findAll() {
		List<Employee> employees = this.service.getAll();
		List<EmployeeResponseDTO> employeesDTO = this.mapper.listEntityToDTO(employees);
		return new ResponseEntity<>(employeesDTO, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable("id") Long id) {
		Employee employee = this.service.readById(id);
		EmployeeResponseDTO employeeDTO = this.mapper.toDTO(employee);
		return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> update(@PathVariable("id") Long id,@RequestBody @Valid EmployeeRequestDTO dto) {
		dto.setEmployeeId(id);
		Employee employee = this.mapper.toEntity(dto);
		Employee employeeUpdated = this.service.update(employee, id);
		return new ResponseEntity<>(this.mapper.toDTO(employeeUpdated), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
