package pl.coderslab.charity.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.Donation.DonationEntity;
import pl.coderslab.charity.Model.Donation.DonationServiceImpl;
import pl.coderslab.charity.Model.UserEntity.UserEntity;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceImp userServiceImp;
    private DonationServiceImpl donationService;

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
        UserEntity user = userServiceImp.getCurrentUser();
        user.setPassword("");
        model.addAttribute("user",user);
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

    @PostMapping("/donations/details")
    public String donationDetails(@RequestParam("id") Long id, Model model){
        model.addAttribute("donation", donationService.findById(id));
        return "donationDetails";
    }

    @PostMapping("/donations/details/confirm")
    public String donationDetailsConfirm(@RequestParam("id") Long id){
        DonationEntity donation = donationService.findById(id);
        donationService.flipDelivered(donation);
        return "redirect:/user/donations";
    }

    @ModelAttribute
    public void currentUser(Model model) {
        model.addAttribute("user", userServiceImp.getCurrentUser());
    }




}
