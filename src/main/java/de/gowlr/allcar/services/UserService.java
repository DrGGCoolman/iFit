package de.gowlr.allcar.services;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.gowlr.allcar.repositories.UserRepository;
import de.gowlr.allcar.entities.*;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.io.Console;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static String USER_ROLE = "USER";
    public static String ADMIN_ROLE = "ADMIN";

    @PostConstruct
    void init() {
        createUser("user@gmail.com", "1234", USER_ROLE);
        createUser("admin@gmail.com", "1234", ADMIN_ROLE);
        // createUser("Benedikt", "987654", User.USER_ROLE);
    }

    private void createUser(String username, String password, String role) {
        try {
            userRepository.save(new EcUserEntity(username, passwordEncoder.encode(password), role));

        } catch (Exception e) {
            
        }
    }

    public List<EcUserEntity> findAll() {
        return userRepository.findAll();
    }

}