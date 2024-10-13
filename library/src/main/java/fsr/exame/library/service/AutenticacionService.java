package fsr.exame.library.service;

import org.springframework.http.ResponseEntity;

import fsr.exame.library.model.AutenticacionRequest;
import fsr.exame.library.model.AuthResponse;
import fsr.exame.library.model.EmployeeDTO;

public interface AutenticacionService {

	ResponseEntity<AuthResponse> registrar(EmployeeDTO registroRequest);
	AuthResponse autenticar(AutenticacionRequest autenticacionRequest);
}
