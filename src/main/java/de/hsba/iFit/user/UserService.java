package de.hsba.ifit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void Seed(String firstname, String lastname, String name, String password, String role) {
        userRepository.save(new User(firstname, lastname, name, passwordEncoder.encode(password), role));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findUsers() {
        return userRepository.findByRole(User.USER_ROLE);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}