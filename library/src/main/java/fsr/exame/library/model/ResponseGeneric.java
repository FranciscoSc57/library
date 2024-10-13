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
public class ResponseGeneric<T> extends ResponseVO implements Serializable{

	private static final long serialVersionUID = 5405571813272825904L;

	private T data;
}
