package pl.coderslab.charity.Controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.Authority.AuthorityEntity;
import pl.coderslab.charity.Model.Category.CategoryEntity;
import pl.coderslab.charity.Model.Donation.DonationEntity;
import pl.coderslab.charity.Model.Institution.InstitutionEntity;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Repos.*;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<InstitutionEntity> institutions = institutionRepository.findAll();
        int quantity = donationRepository.countBags();
        int numberOfInstitutions = institutionRepository.countInstitutions();
        model.addAttribute("institutions",institutions);
        model.addAttribute("quantity", quantity);
        model.addAttribute("numberOfInstitutions", numberOfInstitutions);
        return "index";
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


}
