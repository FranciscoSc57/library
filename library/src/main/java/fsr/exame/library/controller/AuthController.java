package fsr.exame.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fsr.exame.library.model.AutenticacionRequest;
import fsr.exame.library.model.AuthResponse;
import fsr.exame.library.model.EmployeeDTO;
import fsr.exame.library.service.AutenticacionService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AutenticacionService authService;

	@PostMapping("/registrar")
	public ResponseEntity<AuthResponse> registrar(@RequestBody EmployeeDTO request){
		return authService.registrar(request);
	}
	
	@GetMapping("/autenticar")
	public ResponseEntity<AuthResponse> autenticar(@RequestBody AutenticacionRequest request){
		return ResponseEntity.ok(authService.autenticar(request));
	}
	
}
