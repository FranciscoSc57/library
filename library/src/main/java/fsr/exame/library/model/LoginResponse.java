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
public class LoginResponse implements Serializable{

	private static final long serialVersionUID = 7431131431506495746L;

	private String token;

    private long expiresIn;

}
