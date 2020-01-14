package pl.app.charity.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.app.charity.Model.Institution.InstitutionEntity;
import pl.app.charity.Model.Institution.InstitutionServiceImpl;
import pl.app.charity.Model.UserEntity.UserEntity;
import pl.app.charity.Model.UserEntity.UserServiceImp;
import pl.app.charity.Model.Institution.InstitutionRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private InstitutionRepository institutionRepository;
    private UserServiceImp userServiceImp;
    private InstitutionServiceImpl institutionService;

    public AdminController(InstitutionRepository institutionRepository, UserServiceImp userServiceImp, InstitutionServiceImpl institutionService) {
        this.institutionRepository = institutionRepository;
        this.userServiceImp = userServiceImp;
        this.institutionService = institutionService;
    }

    @RequestMapping("")
    public String mainPage(){
        return "adminMainPage";
    }

    @RequestMapping("/users/all")
    public String users(Model model){
        model.addAttribute("users", userServiceImp.getUsers());
        return "userList";
    }

    @GetMapping("/users/remove/{id}")
    public String removeUser(@PathVariable Long id){
        userServiceImp.removeUser(id);
        return "redirect:/admin/users/all";
    }

    @GetMapping("/users/block/{id}")
    public String blockUser(@PathVariable Long id){
        userServiceImp.blockUser(userServiceImp.getUserById(id));
        return "redirect:/admin/users/all";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userServiceImp.getUserById(id));
        return "editUser";
    }

    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute UserEntity userEntity, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/admin/users/all";
        }
        userServiceImp.updateUser(userEntity);
        return "redirect:/admin/users/all";
    }

    @RequestMapping("/admins/all")
    public String admins(Model model){
        model.addAttribute("admins", userServiceImp.getAdmins());
        return "adminList";
    }

    @GetMapping("/admins/add")
    public String addAdmin(Model model){
        model.addAttribute("admin", new UserEntity());
        return "addUser";
    }

    @PostMapping("/admins/add")
    public String processAddAdmin(@ModelAttribute UserEntity userEntity,BindingResult result){
        if(result.hasErrors() || !userServiceImp.isAcceptable(userEntity)){
            return "redirect:/admin/admins/add";
        }
        userServiceImp.addAdmin(userEntity);
        return "redirect:/admin/admins/all";
    }

    @GetMapping("/admins/remove/{id}")
    public String removeAdmin(@PathVariable Long id){
        if(userServiceImp.getCurrentUser().getId().equals(id)){
            return "redirect:/admin/admins/all";
        }
        userServiceImp.removeUser(id);
        return "redirect:/admin/admins/all";
    }

    @GetMapping("/admins/edit/{id}")
    public String editAdmin(@PathVariable Long id, Model model){
        model.addAttribute("admin", userServiceImp.getUserById(id));
        return "editAdmin";
    }

    @PostMapping("/admins/edit")
    public String editAdmin(@ModelAttribute UserEntity userEntity, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/admin/admins/all";
        }
        userServiceImp.updateUser(userEntity);
        return "redirect:/admin/admins/all";
    }

    @RequestMapping("/institutions/all")
    public String institutions(Model model){
        model.addAttribute("institutions", institutionRepository.findNotArchivized());
        return "institutionList";
    }

    @GetMapping("/institutions/add")
    public String addInstitution(Model model){
        model.addAttribute("institution", new InstitutionEntity());
        return "addInstitution";
    }

    @PostMapping("/institutions/add")
    public String processAddInstitution(@ModelAttribute InstitutionEntity institutionEntity, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/admin/institutions/add";
        }
        institutionRepository.save(institutionEntity);
        return "redirect:/admin/institutions/all";
    }

    @GetMapping("/institutions/remove/{id}")
    public String removeInstitution(@PathVariable Long id){
        institutionService.remove(institutionService.findById(id));
        return "redirect:/admin/institutions/all";
    }

    @GetMapping("/institutions/edit/{id}")
    public String editInstitution(@PathVariable Long id, Model model){
        model.addAttribute("institution", institutionRepository.findById(id));
        return "editInstitution";
    }

    @PostMapping("/institutions/edit/{id}")
    public String editInstitution(@ModelAttribute InstitutionEntity institutionEntity, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/admin/institutions/all";
        }
        institutionRepository.save(institutionEntity);
        return "redirect:/admin/institutions/all";
    }
}
