package course.collectionmanager.controller;

import course.collectionmanager.model.MyUser;
import course.collectionmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        model.addAttribute("object", new MyUser());
        return "form_login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(Model model) {
        model.addAttribute("title", "You sure? Log out");
        return "logout";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("object", new MyUser());
        return "form_registration";
    }

    @PostMapping(value = "/registration")
    public String registrationUser(MyUser user, Model model) {
        if (!service.add(user)) {
            model.addAttribute("message", "This login or email already exist");
            return "redirect:/registration";
        }
        return "redirect:/";
    }
    
    @PostMapping(value="/change-user")
    public String blockUser(MyUser user){
        service.setStatus(user);
        service.setRole(user);
        return "redirect:/admin";
    }
    
    @GetMapping(value="/delete-user")
    public String deleteUser(@RequestParam(name="id", required = true) long id) {
        service.delete(id);
        return "redirect:/admin";
    }
}
