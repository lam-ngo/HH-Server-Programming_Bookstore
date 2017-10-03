package fi.hh.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.Bookstore.domain.Category;
import fi.hh.Bookstore.domain.Book;
import fi.hh.Bookstore.domain.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	@Autowired
    private BookRepository repository;

    @Test
    public void findByLastnameShouldReturnStudent() {
        List<Book> books = repository.findByTitle("Title1");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Title1");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("Title1", "Author1", 2014, "ABC123", 10.5, new Category("Category10"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
}
