package com.bbsw.bitboxer2.practica.service;

import com.bbsw.bitboxer2.practica.model.User;
import com.bbsw.bitboxer2.practica.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    public boolean loginUser(String username, String password) {
        User loggedUser = userRepository.loginUser(username, password);
        if (loggedUser == null) {
            logger.info("User not found");
            return false;
        }
        return true;
    }

}
