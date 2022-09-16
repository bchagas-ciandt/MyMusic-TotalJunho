package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.UserNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import com.ciandt.summit.bootcamp2022.utils.ErrorMassage;
import com.ciandt.summit.bootcamp2022.utils.LogMassage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Cacheable("userId")
    public Optional<User> findUserById(String id) {
        idValidation(id);
        userExiste(id);
        logger.info(LogMassage.FIND_USER_BY_ID);
        return userRepository.findById(id);
    }

    @Cacheable("allUsers")
    public List<User> findAllUsers() {
        logger.info(LogMassage.FIND_ALL_USERS);
        emptyUserListShouldTrowException(userRepository.findAll());
        return userRepository.findAll();
    }

    private void idValidation(String id) {
        if (Objects.equals(id, " ") || id == null) {
            logger.error(LogMassage.LOG_ERROR_INVALID_ID);
            throw new InvalidIdException(ErrorMassage.INVALID_ID_EXCEPTION);
        }
    }

    private void userExiste(String id) {
        if (!userRepository.existsById(id)) {
            logger.error(LogMassage.LOG_ERROR_ID_NOT_FOUND);
            throw new UserNotFoundException(ErrorMassage.USER_NOT_FOUND_EXCEPTION);
        }
    }

    private void emptyUserListShouldTrowException(List<User> useres) {
        if (useres.isEmpty()) {
            logger.error(LogMassage.LOG_ERROR_EMPTY_LIST);
            throw new EmptyListException(ErrorMassage.EMPTY_LIST_EXCEPTION);
        }
    }
}
