package fsr.exame.library.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fsr.exame.library.model.BookDTO;
import fsr.exame.library.model.ResponseGeneric;
import fsr.exame.library.model.ResponseVO;
import fsr.exame.library.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

	private final BookService bookService;
	
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getAllBooks(){
		log.info("Obtener All Books");
		
		return bookService.getAllBooks();
	}
	
	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<ResponseGeneric<BookDTO>> getAllBooks(@PathVariable("isbn") String isbn){
		log.info("Obtener Libro by ISBN");
		
		return bookService.getBookByISBN(isbn);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<ResponseVO> createBook(@RequestBody BookDTO bookDTO){
		log.info("Crear Libro");
		
		return bookService.createBook(bookDTO);
	}
	
	@GetMapping(path = "/author")
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getBookByAuthor(@RequestParam("author") String author){
		log.info("Obtener Book by Fecha Determinada");
		
		return bookService.getBookByAuthor(author);
	}
	
	@GetMapping(path = "/fecha")
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getBookByCertainDate(@RequestParam("fecha") LocalDate fecha){
		log.info("Obtener Book by Fecha Determinada");
		
		return bookService.getBookByCertainDate(fecha);
	}
}
