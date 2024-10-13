package fsr.exame.library.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fsr.exame.library.model.EmployeeDTO;
import fsr.exame.library.model.ResponseGeneric;
import fsr.exame.library.model.ResponseVO;

public interface EmployeeService {

	public ResponseEntity<ResponseGeneric<List<EmployeeDTO>>> getAllEmployees();
	public ResponseEntity<ResponseGeneric<EmployeeDTO>> getEmployeeById(Long id);
	public ResponseEntity<ResponseVO> addEmployee(EmployeeDTO employee);
}
