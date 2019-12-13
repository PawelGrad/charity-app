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
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;


@Controller
public class HomeController {


    private UserServiceImp userServiceImp;
    private EmailServiceImpl emailService;
    private DonationServiceImpl donationService;
    private InstitutionServiceImpl institutionService;

    public HomeController(UserServiceImp userServiceImp, EmailServiceImpl emailService, DonationServiceImpl donationService, InstitutionServiceImpl institutionService) {
        this.userServiceImp = userServiceImp;
        this.emailService = emailService;
        this.donationService = donationService;
        this.institutionService = institutionService;
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

    @GetMapping("/mailTest")
    public String mail(){
        emailService.sendSimpleMessage();
        return "login";
    }





}
