package fsr.exame.library.mapper;

import org.springframework.stereotype.Component;

import fsr.exame.library.constant.Role;
import fsr.exame.library.entity.EmployeeEntity;
import fsr.exame.library.model.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class EmployeeMapper {

	public static EmployeeDTO employeeEntityToDTO(EmployeeEntity employeeEntity) {
		EmployeeDTO employeeDTO = new EmployeeDTO(); 
		try {
			employeeDTO.setId(employeeEntity.getId());
			employeeDTO.setName(employeeEntity.getName());
			employeeDTO.setLastName(employeeEntity.getLastName());
			employeeDTO.setActive(employeeEntity.isActive());
			employeeDTO.setEmail(employeeEntity.getEmail());
			employeeDTO.setPassword(employeeEntity.getPassword());
			employeeDTO.setRole(employeeEntity.getRole().toString());
			return employeeDTO;
		}catch (Exception e) {
			log.error("Ocurrio un problema al mapear entidad Empleado a DTO: {}",e);
			return null;
		}
	}
	
	public static EmployeeEntity employeeDTOToEntity(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity = new EmployeeEntity(); 
		try {
			employeeEntity.setName(employeeDTO.getName());
			employeeEntity.setLastName(employeeDTO.getLastName());
			employeeEntity.setActive(employeeDTO.isActive());
			employeeEntity.setEmail(employeeDTO.getEmail());
			employeeEntity.setPassword(employeeDTO.getPassword());
			employeeEntity.setRole(Role.USER);
			return employeeEntity;
		}catch (Exception e) {
			log.error("Ocurrio un problema al mapear DTO Empleado a Entidad: {}",e);
			return null;
		}
	}
}
