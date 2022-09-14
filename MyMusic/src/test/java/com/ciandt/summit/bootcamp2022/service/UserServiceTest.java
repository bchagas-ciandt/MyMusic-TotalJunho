package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.entity.UserType;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.UserNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    User user1, user2;
    @BeforeEach
    public void setup(){
        user1 = new User("user1Id", "user1Name", new Playlist(), new UserType());
        user2 = new User("user2Id", "user2Name", new Playlist(), new UserType());

    }

    @Test
    @DisplayName("return a list of all users")
    void findAllUsers_shouldReturnListOfUsers(){
        List<User> userList = new ArrayList<>(Arrays.asList(user1,user2));
        BDDMockito.when(userRepository.findAll()).thenReturn(userList);

        Assertions.assertFalse(userService.findAllUsers().isEmpty());
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(2, userService.findAllUsers().size());
        Assertions.assertEquals("user1Id", userList.get(0).getId());
        Assertions.assertEquals("user1Name", userList.get(0).getName());
        Assertions.assertEquals("user2Id", userList.get(1).getId());
        Assertions.assertEquals("user2Name", userList.get(1).getName());
    }

    @Test
    @DisplayName("Throws EmptyListException When list is empty")
    void findAllUsers_shouldThrowsEmptyListException_WhenReturnEmptyList() {
        Assertions.assertThrows(EmptyListException.class, ()-> userService.findAllUsers());
    }

    @Test
    @DisplayName("return a user by id ")
    void findUserById_shouldReturnUser_WhenIdisOk() {

    BDDMockito.when(userRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(user1));
    BDDMockito.when(userRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);
    Assertions.assertNotNull(user1);
    Assertions.assertEquals(Optional.of(user1), userService.findUserById("asdasda"));
    }

    @Test
    @DisplayName("Throws InvalidIdException When id is null")
    void findUserById_shouldThrowsInvalidIdException_WhenIdIsNull() {
        Assertions.assertThrows(InvalidIdException.class, ()-> userService.findUserById(null));
    }

    @Test
    @DisplayName("Throws InvalidIdException When id is Empty")
    void findUserById_shouldThrowsInvalidIdException_WhenIdIsEmpty() {
        Assertions.assertThrows(InvalidIdException.class, ()-> userService.findUserById(" "));
    }

    @Test
    @DisplayName("Throws UserNotFoundException when user doesnot exist")
    void findUserById_shouldThrowsUserNotFoundException_WhenUserDoesnotExist() {
        Assertions.assertThrows(UserNotFoundException.class, ()-> userService.findUserById("50"));
    }

}
