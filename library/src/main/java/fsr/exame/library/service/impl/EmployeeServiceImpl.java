package fsr.exame.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fsr.exame.library.entity.EmployeeEntity;
import fsr.exame.library.mapper.EmployeeMapper;
import fsr.exame.library.model.EmployeeDTO;
import fsr.exame.library.model.ResponseGeneric;
import fsr.exame.library.model.ResponseVO;
import fsr.exame.library.repository.EmployeeRepository;
import fsr.exame.library.service.EmployeeService;
import fsr.exame.library.service.LogAspect;
import fsr.exame.library.utils.ValidateRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository employeeRepository;
	
	@LogAspect
	@Override
	public ResponseEntity<ResponseGeneric<List<EmployeeDTO>>> getAllEmployees() {
		List<EmployeeDTO> listEmpleados = new ArrayList<EmployeeDTO>();
		ResponseGeneric<List<EmployeeDTO>> response = new ResponseGeneric<List<EmployeeDTO>>();
		try {
			listEmpleados = employeeRepository.findAll().stream().map(EmployeeMapper::employeeEntityToDTO).collect(Collectors.toList());
			
			if(listEmpleados.isEmpty()) {
				response.setMensaje("No se encontraron Libros");
				return new ResponseEntity<>(
						response,
						HttpStatus.NOT_FOUND
						);
			}
			
			response.setMensaje("Empleados consultados exitosamente");
			response.setData(listEmpleados);
			
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
					);
			
		}catch (Exception e) {
			log.error("Ocurrio un problema al obtener los Empleados");
			response.setMensaje("Ocurrio un problema al obtener los Empleados");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}

	@LogAspect
	@Override
	public ResponseEntity<ResponseGeneric<EmployeeDTO>> getEmployeeById(Long id) {
		log.info("Obtener Empleado: "+id);
		
		ResponseGeneric<EmployeeDTO> response = new ResponseGeneric<EmployeeDTO>();
		try {
			Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
			
			if(!employeeEntity.isPresent()) {
				response.setMensaje("No se encontro el Empleado: "+id);
				return new ResponseEntity<>(
						response,
						HttpStatus.NOT_FOUND
						);
			}
			
			EmployeeDTO empleado = EmployeeMapper.employeeEntityToDTO(employeeEntity.get());
			
			response.setMensaje("Empleado consultado exitosamente");
			response.setData(empleado);
			
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
					);
			
		}catch(Exception e) {
			log.error("Ocurrio un problema al consultar el Empleado");
			response.setMensaje("Ocurrio un problema al consultar el Empleado");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}

	@LogAspect
	@Override
	public ResponseEntity<ResponseVO> addEmployee(EmployeeDTO employee) {
		log.info("Guardando Empleado");
		
		ResponseVO response = new ResponseVO();
		try {
			List<String> dataRequired = ValidateRequest.validateEmployee(employee);
			
			if(!dataRequired.isEmpty()) {
				log.info("Bad Request: {}",dataRequired.toString());
				response.setMensaje(dataRequired.toString());
				return new ResponseEntity<>(
						response,
						HttpStatus.BAD_REQUEST
						);
				}
				
			Optional<EmployeeEntity> employeeEntityExist = employeeRepository.findByEmail(employee.getEmail());
			
			if(employeeEntityExist.isPresent()) {
				response.setMensaje("El Email ya existe: "+employee.getEmail());
				return new ResponseEntity<>(
						response,
						HttpStatus.CONFLICT
						);
			}
			
			EmployeeEntity employeeEntity = EmployeeMapper.employeeDTOToEntity(employee);
			
			employeeRepository.save(employeeEntity);
			
			response.setMensaje("Empleado creado exitosamente");
			
			return new ResponseEntity<>(
					response,
					HttpStatus.CREATED
					);
			
		}catch(Exception e) {
			log.error("Ocurrio un problema al crear el Empleado: {}",e);
			response.setMensaje("Ocurrio un problema al crear el Empleado");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}

	
}
