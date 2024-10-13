package fsr.exame.library.service;

import fsr.exame.library.model.AutenticacionRequest;
import fsr.exame.library.model.AuthResponse;
import fsr.exame.library.model.EmployeeDTO;

public interface AutenticacionService {

	AuthResponse registrar(EmployeeDTO registroRequest);
	AuthResponse autenticar(AutenticacionRequest autenticacionRequest);
}
