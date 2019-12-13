package pl.coderslab.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Config.EmailServiceImpl;
import pl.coderslab.charity.Model.Donation.DonationRepository;
import pl.coderslab.charity.Model.Donation.DonationServiceImpl;
import pl.coderslab.charity.Model.Institution.InstitutionRepository;
import pl.coderslab.charity.Model.Institution.InstitutionServiceImpl;
import pl.coderslab.charity.Model.Token.TokenEntity;
import pl.coderslab.charity.Model.Token.TokenRepository;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;

import java.util.UUID;


@Controller
public class HomeController {


    private UserServiceImp userServiceImp;
    private DonationServiceImpl donationService;
    private InstitutionServiceImpl institutionService;
    private TokenRepository tokenRepository;

    public HomeController(UserServiceImp userServiceImp, DonationServiceImpl donationService, InstitutionServiceImpl institutionService, TokenRepository tokenRepository) {
        this.userServiceImp = userServiceImp;
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.tokenRepository = tokenRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("quantity", donationService.countBags());
        model.addAttribute("numberOfInstitutions", institutionService.countInstitutions());
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
        userServiceImp.saveUser(user);
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/activate/{uuid}")
    public String activateAccount(@PathVariable String uuid){
        TokenEntity token = tokenRepository.findByUuid(uuid);
        if (token != null) {
            userServiceImp.activateAccount(uuid);
        }
        return "redirect:/login";
    }

    @GetMapping("/restorePassword")
    public String restorePassword(){
        return "passwordRestorationEmail";
    }

    @PostMapping("/restorePassword")
    public String processRestorePassword(@RequestParam("email") String email){
        UserEntity user = userServiceImp.getUserByEmail(email);
        if(user != null) {
            userServiceImp.sendPasswordResetEmail(user);
        }
        return "index";
    }

    @GetMapping("/changePassword/{uuid}")
    public String changePassword(@PathVariable String uuid, Model model){
        TokenEntity token = tokenRepository.findByUuid(uuid);
        if (token != null) {
            model.addAttribute("user", token.getUser());
            model.addAttribute("token", token);
        }
        return "passwordRestoration";
    }

    @PostMapping("/changePassword")
    public String processChangePassword(@ModelAttribute UserEntity user, BindingResult result){
        if(result.hasErrors() || (!user.getPassword().equals(user.getPasswordConfirmation()))){
           return "redirect:/login";
        }
        userServiceImp.updatePassword(user);
        return "login";
    }






}
