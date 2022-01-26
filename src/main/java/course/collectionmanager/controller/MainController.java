package course.collectionmanager.controller;

import course.collectionmanager.service.ItemService;
import course.collectionmanager.service.TagService;
import course.collectionmanager.service.UserService;
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
    private ItemService serviceItem;

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("tags", serviceTag.allTags());
        model.addAttribute("items", serviceItem.allItems());
        return "home";
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", serviceUser.allUsers());
        return "adminPage";
    }

}
