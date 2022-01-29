package course.collectionmanager.controller;

import course.collectionmanager.model.MyUser;
import course.collectionmanager.service.TagService;
import course.collectionmanager.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private TagService serviceTag;

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        ///model.addAttribute("object", new MyUser());
        return "redirect:/";
    }

    @GetMapping(value = "/registration")
    public String registrationPage(Model model, Principal principal) {
        String theme = principal == null ? "light" : service.findByLogin(principal.getName()).getDesign();
        model.addAttribute("design", theme);
        model.addAttribute("tags", serviceTag.allTags());
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

    @PostMapping(value = "/change-user")
    public String blockUser(MyUser user) {
        service.setStatus(user);
        service.setRole(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete-user")
    public String deleteUser(@RequestParam(name = "id", required = true) long id) {
        service.delete(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/user/edit")
    public String editUser(String text, HttpServletRequest request) {
        service.setDescription(text);
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping(value = "/user/profile")
    public String profileUser(Model model) {
        UserDetails userInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUser user = service.findByLogin(userInfo.getUsername());
        model.addAttribute("user", user);
        return "detail_user";
    }

    @PostMapping(value = "/user/theme")
    public String changeTheme(HttpServletRequest request) {
        service.setDesign(request.getParameter("theme"), Long.valueOf(request.getParameter("id")));
        return "redirect:" + request.getHeader("referer");
    }
}
