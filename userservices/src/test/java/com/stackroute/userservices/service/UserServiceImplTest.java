package com.stackroute.userservices.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservices.exceptions.UserAlreadyExistException;
import com.stackroute.userservices.exceptions.UserNotFoundException;
import com.stackroute.userservices.model.User;
import com.stackroute.userservices.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private User user;

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    User optional;

    @BeforeEach
    public void setUp() throws Exception {
        user = new User();
        MockitoAnnotations.openMocks(this);
        user.setUserName("siva");
        user.setPassword("nagireddy");
        user.setEmail("siva@gmail.com");
        //optional = Optional.of(user);

    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistException {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User user1 = userServiceImpl.register(user);
        assertEquals(user.getId(), user1.getId());

    }

    @Test
    void testExistsUser() {
        Mockito.when(userRepository.findByuserName("siva")).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThrows(UserAlreadyExistException.class,
                () -> userServiceImpl.register(user));
    }

    @Test
    void testExistsLogin() throws UserNotFoundException {
        Mockito.when(userRepository.findByuserNameAndPassword("siva", "nagireddy")).thenReturn(user);
        User fetchedUser = userServiceImpl.login("siva", "nagireddy");
        assertEquals("siva", fetchedUser.getUserName());
    }

    @Test
    void testForLoginNotExistUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.login("siva", "nagireddy"));
    }


}