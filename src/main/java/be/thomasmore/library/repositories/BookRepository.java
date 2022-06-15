package be.thomasmore.library.repositories;

import be.thomasmore.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.author LIKE CONCAT('%',?1,'%')")
    List<Book> findAllByAuthor(String author);
    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%',?1,'%')")
    List<Book> findAllByTitle(String title);
    List<Book> findAllByYear(int year);
}
