package pl.coderslab.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.Model.Category.CategoryEntity;
import pl.coderslab.charity.Model.Donation.DonationEntity;
import pl.coderslab.charity.Model.Institution.InstitutionEntity;
import pl.coderslab.charity.Repos.CategoryRepository;
import pl.coderslab.charity.Repos.DonationRepository;
import pl.coderslab.charity.Repos.InstitutionRepository;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private CategoryRepository categoryRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
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



}
