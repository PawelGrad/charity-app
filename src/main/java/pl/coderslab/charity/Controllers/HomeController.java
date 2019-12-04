package pl.coderslab.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.Model.Institution.InstitutionEntity;
import pl.coderslab.charity.Repos.InstitutionRepository;


@Controller
public class HomeController {

    InstitutionRepository institutionRepository;

    public HomeController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }

    @RequestMapping("/login")
    public String homeTest(Model model){
//        InstitutionEntity institutionEntity = new InstitutionEntity();
//        institutionEntity.setDescritpion("aaa");
//        institutionEntity.setName("BBB");
//
//        institutionRepository.save(institutionEntity);
        return "index";
    }

    @RequestMapping("/aa")
    @ResponseBody
    public String test(){
        return "HURR";
    }

}
