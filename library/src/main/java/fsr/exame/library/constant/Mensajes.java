package fsr.exame.library.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Mensajes {

	SUCCESS("Success"),
	OK("OK");
	
	private final String mensaje;
}
