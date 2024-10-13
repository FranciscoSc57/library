package fsr.exame.library.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fsr.exame.library.entity.BookEntity;
import fsr.exame.library.mapper.BookMapper;
import fsr.exame.library.model.BookDTO;
import fsr.exame.library.model.ResponseGeneric;
import fsr.exame.library.model.ResponseVO;
import fsr.exame.library.repository.BookRepository;
import fsr.exame.library.service.BookService;
import fsr.exame.library.utils.ValidateRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{

	private final BookRepository bookRepository;
	
	@Override
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getAllBooks() {
		log.info("Obteniendo Todos los Libros");
		List<BookDTO> allBooks = new ArrayList<BookDTO>();
		ResponseGeneric<List<BookDTO>> response = new ResponseGeneric<List<BookDTO>>();
		try {
			
			allBooks = bookRepository.findAll().stream()
										.map(BookMapper::bookEntityToDTO)
										.collect(Collectors.toList());
			
			if(allBooks == null || allBooks.isEmpty()) {
				
				response.setMensaje("No se encontraron Libros");
				return new ResponseEntity<>(
						response,
						HttpStatus.NOT_FOUND
						);
			}
			
			response.setMensaje("Libros consultados exitosamente");
			response.setData(allBooks);
			
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
					);
			
		}catch(Exception e) {
			log.error("Ocurrio un problema al obtener los libros");
			response.setMensaje("Ocurrio un problema al obtener los libros");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
		
	}

	@Override
	public ResponseEntity<ResponseGeneric<BookDTO>> getBookByISBN(String isbn) {
		log.info("Obtener Libro: "+isbn);
		
		ResponseGeneric<BookDTO> response = new ResponseGeneric<BookDTO>();
		try {
			Optional<BookEntity> bookEntity = bookRepository.findById(isbn);
			
			if(!bookEntity.isPresent()) {
				response.setMensaje("No se encontro el Libro: "+isbn);
				return new ResponseEntity<>(
						response,
						HttpStatus.NOT_FOUND
						);
			}
			
			BookDTO book = BookMapper.bookEntityToDTO(bookEntity.get()); 
			
			response.setMensaje("Libro consultado exitosamente");
			response.setData(book);
			
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
					);
			
		}catch(Exception e) {
			log.error("Ocurrio un problema al consultar el libro");
			response.setMensaje("Ocurrio un problema al consultar el libro");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseVO> createBook(BookDTO bookDTO) {
		log.info("Guardando Libro");
		
		ResponseVO response = new ResponseVO();
		try {
			
			List<String> dataRequired = ValidateRequest.validateBook(bookDTO);
					
			if(!dataRequired.isEmpty()) {
				log.info("Bad Request: {}",dataRequired.toString());
				response.setMensaje(dataRequired.toString());
				return new ResponseEntity<>(
						response,
						HttpStatus.BAD_REQUEST
						);
				}
			
			if(bookDTO.getPublishedDate().isAfter(LocalDate.now())) {
				response.setMensaje("Libro aun no publicado, fecha de publicacion no debe estar en el futuro");
				return new ResponseEntity<>(
						response,
						HttpStatus.CONFLICT
						);
			}
			
			Optional<BookEntity> bookEntityExist = bookRepository.findById(bookDTO.getIsbn());
			
			if(bookEntityExist.isPresent()) {
				response.setMensaje("El Libro ya existe: "+bookDTO.getIsbn());
				return new ResponseEntity<>(
						response,
						HttpStatus.CONFLICT
						);
			}
			
			BookEntity bookEntity = BookMapper.bookDTOToEntity(bookDTO);
			
			bookRepository.save(bookEntity);
			
			response.setMensaje("Libro creado exitosamente");
			
			return new ResponseEntity<>(
					response,
					HttpStatus.CREATED
					);
			
		}catch(Exception e) {
			log.error("Ocurrio un problema al crear el libro");
			response.setMensaje("Ocurrio un problema al crear el libro");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}

	@Override
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getBookByAuthor(String author) {
		log.info("Obtener Libro by Autor: "+author);
		
		ResponseGeneric<List<BookDTO>> response = new ResponseGeneric<List<BookDTO>>();
		try {
			Optional<List<BookEntity>> bookEntity = bookRepository.findByAuthor(author);
			
			if(bookEntity.get().isEmpty()) {
				response.setMensaje("No se encontraron libros del autor: "+author);
				return new ResponseEntity<>(
						response,
						HttpStatus.NOT_FOUND
						);
			}
			
			List<BookDTO> librosDTO = bookEntity.get().stream().map(BookMapper::bookEntityToDTO)
														.collect(Collectors.toList());
			
			response.setMensaje("Libros encontrados exitosamente");
			response.setData(librosDTO);
			
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
					);
			
		}catch(Exception e) {
			log.error("Ocurrio un problema al consultar libros by author");
			response.setMensaje("Ocurrio un problema al consultar libros by author");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
	
	@Override
	public ResponseEntity<ResponseGeneric<List<BookDTO>>> getBookByCertainDate(LocalDate fecha) {
		log.info("Obtener Libro by Fecha Determinada: "+fecha);
		
		ResponseGeneric<List<BookDTO>> response = new ResponseGeneric<List<BookDTO>>();
		try {
			Optional<List<BookEntity>> bookEntity = bookRepository.findByPublishedAfterDate(fecha);
			
			if(bookEntity.get().isEmpty()) {
				response.setMensaje("No se encontraron libros publicados despues de la fecha: "+fecha);
				return new ResponseEntity<>(
						response,
						HttpStatus.NOT_FOUND
						);
			}
			
			List<BookDTO> librosDTO = bookEntity.get().stream().map(BookMapper::bookEntityToDTO)
														.collect(Collectors.toList());
			
			response.setMensaje("Libros encontrados exitosamente");
			response.setData(librosDTO);
			
			return new ResponseEntity<>(
					response,
					HttpStatus.OK
					);
			
		}catch(Exception e) {
			log.error("Ocurrio un problema al consultar libros by fecha determinada");
			response.setMensaje("Ocurrio un problema al consultar libros by fecha determinada");
			return new ResponseEntity<>(
					response,
					HttpStatus.INTERNAL_SERVER_ERROR
					);
		}
	}
}
