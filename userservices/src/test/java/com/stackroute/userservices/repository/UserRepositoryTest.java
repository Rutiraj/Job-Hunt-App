package com.stackroute.userservices.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.stackroute.userservices.model.User;

import org.mockito.MockitoAnnotations;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserName("siva");
        user.setPassword("nagireddy");
        user.setEmail("siva@gmail.com");
    }

    @AfterEach
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getId()).get();
        assertThat(user.getId(), is(fetchUser.getId()));
    }

    @Test
    public void testLoginUserSuccess() {
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getId()).get();
        String name = fetchUser.getUserName();
        assertEquals("siva", name);

    }


    @Test
    public void testFindByUserNameAndPasswordMethod() {
        userRepository.save(user);
        User fetchUser = userRepository.findByuserNameAndPassword(user.getUserName(), user.getPassword());
        assertEquals(user.getId(), fetchUser.getId());
    }
}