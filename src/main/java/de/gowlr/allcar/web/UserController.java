package de.gowlr.allcar.web;

import de.gowlr.allcar.entities.*;
import de.gowlr.allcar.repositories.*;
import de.gowlr.allcar.services.*;
import lombok.NoArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@RequestMapping("/users/")
@Controller
public class UserController {

    private final UserRepository UserRepository;
    private final UserService UserService;
    @Autowired
    private PasswordEncoder Encoder;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.UserRepository = userRepository;
        this.UserService = userService;
    }

    @GetMapping
    public String userIndex(Model model) {
        model.addAttribute("users", UserService.findAll());
        return "users/index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) boolean error, Model model,
            @RequestParam(required = false) Integer id) {
        String msg = error ? "Bitte Passwort und Nutzernamen prüfen!" : "";
        model.addAttribute("err", error);
        model.addAttribute("msg", msg);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";

    }

    @GetMapping("register")
    public String showSignUpForm(Model model) {
        EcUserEntity user = new EcUserEntity();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("add")
    public String addEcUserEntity(@Valid EcUserEntity user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        user.setPassword(Encoder.encode(user.getPassword()));
        user.setRole("USER");
        UserRepository.save(user);
        return "redirect:/users/login";
    }

    @GetMapping("edit")
    public String showUpdateForm(Model model) {

        EcUserEntity currUser = EcUserEntity.getCurrentUser();

        EcUserEntity user = UserRepository.findById(currUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        model.addAttribute("user", user);

        return "user/edit-user";
    }

    @PostMapping("update")
    public String updateUser(@Valid EcUserEntity user, BindingResult result, Model model) {
        EcUserEntity currUser = EcUserEntity.getCurrentUser();
        user.setId(currUser.getId());
        if (result.hasErrors()) {
            return "redirect:/users/edit";
        }

        UserRepository.save(user);

        return "redirect:/";
    }

}