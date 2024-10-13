package fsr.exame.library.utils;

import java.util.ArrayList;
import java.util.List;

import fsr.exame.library.model.BookDTO;
import fsr.exame.library.model.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateRequest {

	public static List<String> validateBook(BookDTO bookDTO) {
		log.info("Validando datos Request Book");
		List<String> required = new ArrayList<String>();
		
		if(bookDTO.getIsbn() == null || bookDTO.getIsbn().trim().isEmpty()) {
			required.add("ISBN no debe ser nulo ni vacio");
		}
		if(bookDTO.getName() == null || bookDTO.getName().trim().isEmpty()) {
			required.add("Nombre del libro no debe ser nulo ni vacio");
		}
		if(bookDTO.getAuthor() == null || bookDTO.getAuthor().trim().isEmpty()) {
			required.add("author no debe ser nulo ni vacio");
		}
		if(bookDTO.getPublishedDate() == null || bookDTO.getPublishedDate().equals("")) {
			required.add("Fecha de publicacion no debe ser nulo ni vacio");
		}
		
		return required;
	}
	
	public static List<String> validateEmployee(EmployeeDTO employeeDTO) {
		log.info("Validando datos Request Empleados");
		List<String> required = new ArrayList<String>();
		
		if(employeeDTO.getId() == null || employeeDTO.getId() == 0) {
			required.add("ID del empleado no debe ser nulo y mayor a 0");
		}
		if(employeeDTO.getName() == null || employeeDTO.getName().trim().isEmpty()) {
			required.add("Nombre del empleado no debe ser nulo ni vacio");
		}
		if(employeeDTO.getLastName() == null || employeeDTO.getLastName().trim().isEmpty()) {
			required.add("Apellido del Empleado no debe ser nulo ni vacio");
		}
		if(employeeDTO.getPassword() == null || employeeDTO.getPassword().trim().isEmpty()) {
			required.add("Password del Empleado no debe ser nulo ni vacio");
		}
		if(employeeDTO.getEmail() == null || employeeDTO.getEmail().trim().isEmpty()) {
			required.add("Email del Empleado no debe ser nulo ni vacio");
		}
		
		return required;
	}
}
