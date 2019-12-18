package pl.coderslab.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.Donation.DonationServiceImpl;
import pl.coderslab.charity.Model.Institution.InstitutionServiceImpl;
import pl.coderslab.charity.Model.Token.TokenEntity;
import pl.coderslab.charity.Model.Token.TokenRepository;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;
import pl.coderslab.charity.Utils.Utils;

import java.time.LocalDateTime;


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
        if(result.hasErrors() || !userServiceImp.isAcceptable(user)){
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
            userServiceImp.activateAccount(tokenRepository.findByUuid(uuid).getUser());
        }
        return "redirect:/login";
    }

    @GetMapping("/restorePassword")
    public String restorePassword(){
        return "passwordRestorationEmail";
    }

    @PostMapping("/restorePassword")
    public String processRestorePassword(@RequestParam("email") String email, Model model){
        UserEntity user = userServiceImp.getUserByEmail(email);
        if(user == null) {
            model.addAttribute("error", "Uzytkownik nie isnieje");
            return "passwordRestorationEmail";
        }
        userServiceImp.sendPasswordResetEmail(user);
        return "index";
    }

    @GetMapping("/changePassword/{uuid}")
    public String changePassword(@PathVariable String uuid, Model model){
        TokenEntity token = tokenRepository.findByUuid(uuid);
        if (token != null && !token.isExprired()) {
            UserEntity user = token.getUser();
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("token", token.getUuid());
            return "passwordRestoration";
        } else {
            return "redirect:/restorePassword";
        }

    }

    @PostMapping("/changePassword/{uuid}")
    public String processChangePassword(@PathVariable String uuid, @ModelAttribute UserEntity user, BindingResult result, Model model){
        if(result.hasErrors() || !Utils.checkPwd(user.getPassword())){
            return "redirect:/changePassword/"+uuid ;
        } else if (!user.getPassword().equals(user.getPasswordConfirmation())){
            user.setPassword("");
            model.addAttribute("user", user);
            model.addAttribute("token", tokenRepository.findByUuid(uuid));
            model.addAttribute("error", "Podane hasla nie sa takie same");
            return "passwordRestoration";
        }
        user.getTokens().remove(tokenRepository.findByUuid(uuid));
        userServiceImp.updatePassword(user);
        return "redirect:/login";
    }

    @ModelAttribute
    public void currentUser(Model model) {
        model.addAttribute("user", userServiceImp.getCurrentUser());
    }





}
