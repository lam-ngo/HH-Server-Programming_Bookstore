package fi.hh.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

import fi.hh.Bookstore.domain.Book;
import fi.hh.Bookstore.domain.BookRepository;
import fi.hh.Bookstore.domain.Category;
import fi.hh.Bookstore.domain.CategoryRepository;
import fi.hh.Bookstore.domain.User;
import fi.hh.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder
	application) {
		return application.sources(BookstoreApplication.class);
	}
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Category 1"));
			crepository.save(new Category("Category 2"));
			
			brepository.save(new Book("Title1", "Author1", 2014, "ABC123", 10.5,crepository.findByName("Category 1").get(0)));
			brepository.save(new Book("Title2", "Author2", 2015, "DEF456", 20.0,crepository.findByName("Category 2").get(0)));
			brepository.save(new Book("Title3", "Author3", 2016, "GHI789", 5.0,crepository.findByName("Category 1").get(0)));
			brepository.save(new Book("Title4", "Author4", 2017, "KLM101", 15.3,crepository.findByName("Category 2").get(0)));
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "password", "USER");
			User user2 = new User("admin", "password", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
						
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
