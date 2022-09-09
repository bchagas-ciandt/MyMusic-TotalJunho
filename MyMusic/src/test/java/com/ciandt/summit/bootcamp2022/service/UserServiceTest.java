package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.entity.UserType;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    void findAllUsers_shouldReturnListOfMusic(){
        List<User> userList = new ArrayList<>(Arrays.asList(user1,user2));
        BDDMockito.when(userRepository.findAll()).thenReturn(userList);

        Assertions.assertFalse(userList.isEmpty());
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(2, userList.size());
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
    @DisplayName("return a list of all users")
    void findUserById_shouldReturnUser_WhenIdisOk() {

    BDDMockito.when(userRepository.findById("user1Id")).thenReturn(Optional.of(user1));
    Assertions.assertNotNull(user1);
    Assertions.assertEquals("user1Name", user1.getName());
    }

    @Test
    @DisplayName("Throws InvalidIdException When id is not ok")
    void findUserById_shouldThrowsInvalidIdException_WhenIdIsNoTOk() {
        Assertions.assertThrows(InvalidIdException.class, ()-> userService.findUserById(null));
    }

}
