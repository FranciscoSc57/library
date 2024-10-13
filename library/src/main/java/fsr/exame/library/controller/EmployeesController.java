package fsr.exame.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fsr.exame.library.model.EmployeeDTO;
import fsr.exame.library.model.ResponseGeneric;
import fsr.exame.library.model.ResponseVO;
import fsr.exame.library.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeesController {

	private final EmployeeService employeeService;
	
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseGeneric<List<EmployeeDTO>>> getAllEmployees(){
		log.info("Obteniendo todos los empleados");
		
		return employeeService.getAllEmployees();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseGeneric<EmployeeDTO>> getEmployeeById(@PathVariable("id") Long id){
		log.info("Obteniendo empleado by ID");
		
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping
	public ResponseEntity<ResponseVO> addEmployee(@RequestBody EmployeeDTO employee){
		log.info("Guardando empleado");
		
		return employeeService.addEmployee(employee);
	}
}
