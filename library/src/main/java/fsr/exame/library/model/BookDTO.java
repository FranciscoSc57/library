package fsr.exame.library.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String isbn;
	
	private String name;
	
	private String author;
	
	private LocalDate publishedDate;
}
