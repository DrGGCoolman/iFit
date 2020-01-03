package de.hsba.ifit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        if (userRepository.count() == 0) {
            createUser("Anne", "Baum", "anne.baum", "password", User.USER_ROLE);
            createUser("Benedikt", "Müller", "benedikt.müller", "password", User.USER_ROLE);
            createUser("Charlotte", "Tulpe", "charlotte.tulpe", "password", User.USER_ROLE);
            createUser("Zoe", "Richter", "zoe.richter", "password", User.USER_ROLE);
            createUser("admin", "admin", "admin", "admin", User.ADMIN_ROLE);
        }
    }

    private void createUser(String firstname, String lastname, String name, String password, String role) {
        userRepository.save(new User(firstname, lastname, name, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }
}