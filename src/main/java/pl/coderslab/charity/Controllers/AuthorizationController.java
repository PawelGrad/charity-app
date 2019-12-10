package pl.coderslab.charity.Controllers;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.Authority.AuthorityEntity;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Repos.AuthorityRepository;
import pl.coderslab.charity.Repos.UserRepository;

import java.security.Principal;

@Controller
public class AuthorizationController {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public AuthorizationController(UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserEntity());

        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute UserEntity user, BindingResult result){
        if(result.hasErrors() || (!user.getPassword().equals(user.getPasswordConfirmation()))){
            return "redirect:register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        authorityRepository.save(new AuthorityEntity(user.getEmail(),"ROLE_USER",user));
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }



        @GetMapping("/user")
        @ResponseBody
        public Principal retrievePrincipal(Principal principal) {
            return principal;
        }

}
