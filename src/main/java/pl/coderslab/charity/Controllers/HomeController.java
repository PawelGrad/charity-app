package pl.coderslab.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Config.EmailServiceImpl;
import pl.coderslab.charity.Model.Donation.DonationRepository;
import pl.coderslab.charity.Model.Institution.InstitutionRepository;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private UserServiceImp userServiceImp;
    private EmailServiceImpl emailService;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserServiceImp userServiceImp, EmailServiceImpl emailService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userServiceImp = userServiceImp;
        this.emailService = emailService;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("quantity", donationRepository.countBags().orElse(0L));
        model.addAttribute("numberOfInstitutions", institutionRepository.countInstitutions().orElse(0L));
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
