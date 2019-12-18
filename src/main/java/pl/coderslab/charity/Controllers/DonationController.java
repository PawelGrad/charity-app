package pl.coderslab.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.Donation.DonationEntity;
import pl.coderslab.charity.Model.Category.CategoryRepository;
import pl.coderslab.charity.Model.Donation.DonationRepository;
import pl.coderslab.charity.Model.Donation.DonationServiceImpl;
import pl.coderslab.charity.Model.Institution.InstitutionRepository;
import pl.coderslab.charity.Model.Institution.InstitutionServiceImpl;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Controller
@RequestMapping("/user")
public class DonationController {

    private CategoryRepository categoryRepository;
    private InstitutionServiceImpl institutionService;
    private DonationServiceImpl donationService;
    private UserServiceImp userServiceImp;

    public DonationController(CategoryRepository categoryRepository, InstitutionServiceImpl institutionService, DonationServiceImpl donationService, UserServiceImp userServiceImp) {
        this.categoryRepository = categoryRepository;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping("/form")
    public String formStepOne(Model model) {
        model.addAttribute("donation", new DonationEntity());
        return "form";
    }

    @PostMapping("/form2")
    public String formStepTwo(@ModelAttribute DonationEntity donation, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "redirect:form";
        }
        model.addAttribute("donation", donation);
        return "form2";
    }

    @PostMapping("/form3")
    public String formStepThree(@ModelAttribute DonationEntity donation, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "redirect:form2";
        }

        model.addAttribute("donation", donation);
        return "form3";
    }

    @PostMapping("/form4")
    public String formStepFour(@ModelAttribute DonationEntity donation, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "redirect:form3";
        }
        model.addAttribute("donation", donation);
        return "form4";
    }

    @PostMapping("/saveForm")
    public String formSave(@Valid @ModelAttribute DonationEntity donation, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:form";
        }
        donation.setUser(userServiceImp.getCurrentUser());
        donationService.newDonation(donation);
        return "form-confirmation";
    }

    @ModelAttribute
    public void addCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
    }
    @ModelAttribute
    public void addInstitutions(Model model) {
        model.addAttribute("institutions", institutionService.findAllNotArchivized());
    }


}

