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
            createUser("Anne", "123456", User.USER_ROLE);
            createUser("Benedikt", "987654", User.USER_ROLE);
            createUser("Charlotte", "password", User.USER_ROLE);
            createUser("Xenia", "password", User.USER_ROLE);
            createUser("Yves", "password", User.USER_ROLE);
            createUser("Zoe", "password", User.USER_ROLE);
            createUser("admin", "admin", User.ADMIN_ROLE);
        }
    }

    private void createUser(String name, String password, String role) {
        userRepository.save(new User(name, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }
}