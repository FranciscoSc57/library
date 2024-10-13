package fsr.exame.library.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import fsr.exame.library.model.BookDTO;
import fsr.exame.library.model.ResponseGeneric;
import fsr.exame.library.model.ResponseVO;

public interface BookService {

	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getAllBooks();
	
	public ResponseEntity<ResponseGeneric<BookDTO>> getBookByISBN(String isbn);
	
	public ResponseEntity<ResponseVO> createBook(BookDTO bookDTO);
	
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getBookByAuthor(String author);
	
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getBookByCertainDate(LocalDate fecha);
	
}
