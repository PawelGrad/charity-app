package pl.coderslab.charity.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.Donation.DonationRepository;
import pl.coderslab.charity.Model.Donation.DonationServiceImpl;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;

import javax.xml.ws.Binding;

@Controller
@RequestMapping("/user")
public class UserController {

    UserServiceImp userServiceImp;
    DonationServiceImpl donationService;

    public UserController(UserServiceImp userServiceImp, DonationServiceImpl donationService) {
        this.userServiceImp = userServiceImp;
        this.donationService = donationService;
    }

    @RequestMapping("")
    public String mainPage(){
        return "userMainPage";
    }

    @GetMapping("/edit")
    public String editData(Model model){
        model.addAttribute("user",userServiceImp.getCurrentUser());
        return "editUserData";
    }

    @PostMapping("/edit")
    public String editData(@ModelAttribute UserEntity userEntity, BindingResult result){
        if(result.hasErrors()) {
            return "redirect:/user/edit";
        }
        userServiceImp.updateUser(userEntity);
        return "redirect:/user";
    }
    @GetMapping("/changePassword")
    public String changePassword(Model model){
        model.addAttribute("user",userServiceImp.getCurrentUser());
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute UserEntity userEntity, BindingResult result){
        if(result.hasErrors() || (!userEntity.getPassword().equals(userEntity.getPasswordConfirmation()))) {
            return "redirect:/user/changePassword";
        }
        userServiceImp.updatePassword(userEntity);
        return "redirect:/user";
    }

    @GetMapping("/donations")
    public String myDonations(Model model) {
        model.addAttribute("donations", donationService.myDonations(userServiceImp.getCurrentUser().getId()));
        return "myDonations";
    }




}
