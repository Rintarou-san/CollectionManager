package course.collectionmanager.controller;

import course.collectionmanager.model.MyUser;
import course.collectionmanager.service.CollectionService;
import course.collectionmanager.service.TagService;
import course.collectionmanager.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService serviceUser;

    @Autowired
    private TagService serviceTag;
    
    @Autowired
    private CollectionService serviceCollection;

    @GetMapping(value = "/")
    public String home(Model model, Principal principal) {
        String theme = principal == null ?  "light" : serviceUser.findByLogin(principal.getName()).getDesign();
        model.addAttribute("tags", serviceTag.allTags());
        model.addAttribute("object", new MyUser());
        model.addAttribute("design", theme);
        model.addAttribute("largeCollection", serviceCollection.largestCollection());
        model.addAttribute("likeCollection", serviceCollection.likeCollection());
        model.addAttribute("commentCollection", serviceCollection.commentCollection());
        return "index";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", serviceUser.allUsers());
        return "adminPage";
    }

}
