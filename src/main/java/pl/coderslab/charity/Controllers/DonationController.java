package pl.coderslab.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.Donation.DonationEntity;
import pl.coderslab.charity.Repos.CategoryRepository;
import pl.coderslab.charity.Repos.DonationRepository;
import pl.coderslab.charity.Repos.InstitutionRepository;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Controller
public class DonationController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private CategoryRepository categoryRepository;

    public DonationController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/form")
    public String formStepOne(Model model) {
        model.addAttribute("donation", new DonationEntity());
        return "form";
    }

    @PostMapping("/form2")
    public String formStepTwo(@ModelAttribute DonationEntity donation, BindingResult result, Model model) {

        model.addAttribute("donation", donation);

        return "form2";
    }

    @PostMapping("/form3")
    public String formStepThree(@ModelAttribute DonationEntity donation, BindingResult result, Model model) {


        model.addAttribute("donation", donation);

        return "form3";
    }

    @PostMapping("/form4")
    public String formStepFour(@ModelAttribute DonationEntity donation, BindingResult result, Model model) {

        model.addAttribute("donation", donation);

        return "form4";
    }

    @PostMapping("/saveForm")
    public String formSave(@ModelAttribute DonationEntity donation, BindingResult result, Model model) {
        System.out.println(donation.toString());
        donationRepository.save(donation);
        return "index";
    }

    @ModelAttribute
    public void addCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
    }
    @ModelAttribute
    public void addInstitutions(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
    }


}

