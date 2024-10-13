package fsr.exame.library.mapper;

import fsr.exame.library.entity.BookEntity;
import fsr.exame.library.model.BookDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookMapper {

	public static BookDTO bookEntityToDTO(BookEntity bookEntity) {
		BookDTO bookDTO = new BookDTO(); 
		try {
			bookDTO.setIsbn(bookEntity.getISBN());
			bookDTO.setName(bookEntity.getName());
			bookDTO.setAuthor(bookEntity.getAuthor());
			bookDTO.setPublishedDate(bookEntity.getPubishedDate());
			
			return bookDTO;
		}catch (Exception e) {
			log.error("Ocurrio un problema al mapear entidad Libro a DTO: {}",e);
			return null;
		}
	}
	
	public static BookEntity bookDTOToEntity(BookDTO bookDTO) {
		BookEntity bookEntity = new BookEntity(); 
		try {
			bookEntity.setISBN(bookDTO.getIsbn());
			bookEntity.setName(bookDTO.getName());
			bookEntity.setAuthor(bookDTO.getAuthor());
			bookEntity.setPubishedDate(bookDTO.getPublishedDate());
			
			return bookEntity;
		}catch (Exception e) {
			log.error("Ocurrio un problema al mapear DTO Libro a Entidad: {}",e);
			return null;
		}
	}
}
