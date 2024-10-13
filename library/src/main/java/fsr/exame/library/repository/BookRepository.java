package fsr.exame.library.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fsr.exame.library.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String>{
	
	public Optional<BookEntity> findById(String isbn);
	
	public Optional<List<BookEntity>> findByAuthor(String author);
	
	@Query("SELECT b FROM BookEntity b WHERE pubishedDate > :pubishedDate")
	public Optional<List<BookEntity>> findByPublishedAfterDate(@Param("pubishedDate") LocalDate pubishedDate);

}
