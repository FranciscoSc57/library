package fsr.exame.library.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable{

	private static final long serialVersionUID = 4283774835496656913L;

	private Long id;
	
	private String name;
	
	private String lastName;
	
	private boolean active;

	private String password;
	
	private String email;
}
