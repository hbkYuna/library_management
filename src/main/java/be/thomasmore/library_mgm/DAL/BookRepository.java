package be.thomasmore.library_mgm.DAL;

import be.thomasmore.library_mgm.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {

}