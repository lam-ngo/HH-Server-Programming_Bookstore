package fi.hh.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.Bookstore.domain.User;
import fi.hh.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
    private UserRepository repository;

    @Test
    public void findByUsernameShouldReturnUser() {
        User user = repository.findByUsername("user");
        
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("user");
    }
    
    @Test
    public void createNewUser() {
    	User user = new User("user", "password", "USER");
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    }    
}
