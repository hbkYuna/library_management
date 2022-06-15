package be.thomasmore.library.controllers;

import be.thomasmore.library.model.User;
import be.thomasmore.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;


@Controller
public class VisitorController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping({"", "/", "/visitorpagina"})
    public String visitorpagina() {

        return "visitorpagina";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password) {
        autologin(username, password);
        return switch (userRepository.findByUsername(username).get().getRole()) {
            case "ROLE_ADMIN" -> "redirect:/admin";
            case "ROLE_USER" -> "redirect:/user";
            default -> "visitorpagina";
        };
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerPost(Principal principal,
                               Model model,
                               @RequestParam String username,
                               @RequestParam String password) {
        if (principal != null
                || username == null || username.trim().equals("")
                || password == null || password.trim().equals(""))
            return "redirect:/";
        Optional<User> optionalUser = userRepository.findByUsername(username.trim());

        String errorMessage;
        if (optionalUser.isPresent())
            errorMessage = "Deze gebruikersnaam bestaat al!";

        else {
            String encodedPassword = encoder.encode(password.trim());
            User user = new User(username, encodedPassword, "ROLE_USER");
            userRepository.save(user);
            autologin(username, password.trim());
            return "redirect:/user";
        }
        model.addAttribute("error", errorMessage);
        model.addAttribute("username", username);
        return "registration";
    }

    private void autologin(String userName, String password) {
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}


