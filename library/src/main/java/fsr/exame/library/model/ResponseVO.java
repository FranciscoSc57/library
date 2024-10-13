package fsr.exame.library.model;

import java.io.Serializable;

import fsr.exame.library.constant.Mensajes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO implements Serializable{

	private static final long serialVersionUID = 7252616391867106021L;
	
	private String mensaje = Mensajes.SUCCESS.getMensaje();

}
