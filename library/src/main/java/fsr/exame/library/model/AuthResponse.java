package fsr.exame.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse extends ResponseVO{

	private static final long serialVersionUID = -663488585796761387L;
	private String token;
}
