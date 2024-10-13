package fsr.exame.library.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fsr.exame.library.config.JwtService;
import fsr.exame.library.constant.Role;
import fsr.exame.library.entity.EmployeeEntity;
import fsr.exame.library.model.AutenticacionRequest;
import fsr.exame.library.model.AuthResponse;
import fsr.exame.library.model.EmployeeDTO;
import fsr.exame.library.repository.EmployeeRepository;
import fsr.exame.library.service.AutenticacionService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AutenticacionServiceImpl implements AutenticacionService{

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public ResponseEntity<AuthResponse> registrar(EmployeeDTO registroRequest) {
		Optional<EmployeeEntity> employeeEntityExist = employeeRepository.findByEmail(registroRequest.getEmail());
		
		AuthResponse response = new AuthResponse();
		
		if(employeeEntityExist.isPresent()) {
			response.setMensaje("El Email ya existe: "+registroRequest.getEmail());
			return new ResponseEntity<>(
					response,
					HttpStatus.CONFLICT
					);
		}
		
		EmployeeEntity user = EmployeeEntity.builder()
				.name(registroRequest.getName())
				.lastName(registroRequest.getLastName())
				.email(registroRequest.getEmail())
				.password(passwordEncoder.encode(registroRequest.getPassword()))
				.role(Role.ADMIN)
				.build();
		employeeRepository.save(user);
		String jwtToken = jwtService.generateToken(user);
		
		response.setToken(jwtToken);
		response.setMensaje("Admin registrado exitosamente");
		return new ResponseEntity<>(
				response,
				HttpStatus.CREATED
				);
	}

	@Override
	public AuthResponse autenticar(AutenticacionRequest autenticacionRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						autenticacionRequest.getEmail(), 
						autenticacionRequest.getPassword()));
		
		EmployeeEntity user = employeeRepository.findByEmail(autenticacionRequest.getEmail()).orElseThrow(null);
		String jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	
}
