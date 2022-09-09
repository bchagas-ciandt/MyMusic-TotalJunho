package com.ciandt.summit.bootcamp2022.service;

import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.exception.EmptyListException;
import com.ciandt.summit.bootcamp2022.exception.InvalidIdException;
import com.ciandt.summit.bootcamp2022.exception.UserNotFoundException;
import com.ciandt.summit.bootcamp2022.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserById(String id){
        idValidation(id);
        userExiste(id);
        logger.info("Buscando usuário por id.");
        return userRepository.findById(id);
    }

    public List<User> findAllUsers(){
        logger.info("Buscando todos os usuários.");
        emptyUserListShouldTrowException(userRepository.findAll());
        return userRepository.findAll();
    }

    private void idValidation(String id) {
        if (id == " " || id == null) {
            logger.error("Id nulo ou em branco");
            throw new InvalidIdException("Id não não pode ser nulo ou branco");
        }
    }
    private void userExiste(String id){
        if(!userRepository.existsById(id)){
            logger.error("Id não consta na base de dados");
            throw new UserNotFoundException("Usuário não encontrado na base de dados.");
        }
    }
    private void emptyUserListShouldTrowException(List<User> useres){
        if(useres.isEmpty()){
            logger.error("Lista vazia para busca de usuários.");
            throw new EmptyListException("Não foi encontrado nenhum usuário para busca.");
        }
    }
}
