package course.collectionmanager.controller;

import course.collectionmanager.model.Collection;
import course.collectionmanager.model.Comment;
import course.collectionmanager.model.Item;
import course.collectionmanager.model.UserLike;
import course.collectionmanager.service.AlcoholService;
import course.collectionmanager.service.CollectionService;
import course.collectionmanager.service.CommentService;
import course.collectionmanager.service.CoverService;
import course.collectionmanager.service.FieldService;
import course.collectionmanager.service.GenreService;
import course.collectionmanager.service.JewelryService;
import course.collectionmanager.service.LikeService;
import course.collectionmanager.service.MetalService;
import course.collectionmanager.service.TagService;
import course.collectionmanager.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/collection")
public class CollectionController {

    @Autowired
    private FieldService serviceField;

    @Autowired
    private TagService serviceTag;

    @Autowired
    private CollectionService serviceCollection;

    @Autowired
    private CommentService serviceComment;

    @Autowired
    private LikeService serviceLike;

    @Autowired
    private AlcoholService serviceAlcohol;

    @Autowired
    private CoverService serviceCover;

    @Autowired
    private GenreService serviceGenre;

    @Autowired
    private UserService serviceUser;

    @Autowired
    private MetalService serviceMetal;

    @Autowired
    private JewelryService serviceJewelry;

    @GetMapping(value = "/{title}")
    public String detail(@RequestParam(name = "id") long id, Model model, Principal principal) {
        String theme = principal == null ? "light" : serviceUser.findByLogin(principal.getName()).getDesign();
        model.addAttribute("design", theme);
        model.addAttribute("collection", serviceCollection.getById(id));
        model.addAttribute("collections", serviceCollection.allCollections());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("newLike", new UserLike());
        return "detail_collection";
    }

    @GetMapping(value = "/create")
    public String createPage(Model model, Principal principal) {
        String theme = principal == null ? "light" : serviceUser.findByLogin(principal.getName()).getDesign();
        model.addAttribute("design", theme);
        model.addAttribute("title", "Create Collection");
        model.addAttribute("url", "/collection/create");
        model.addAttribute("object", new Collection());
        model.addAttribute("tags", serviceTag.allTags());
        model.addAttribute("fields", serviceField.allFields());
        return "form_collection";
    }

    @PostMapping(value = "/create")
    public String create(Collection collection) {
        serviceCollection.add(collection);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String delete(@RequestParam(name = "id") long id) {
        serviceCollection.delete(id);
        return "redirect:/";
    }

    @PostMapping(value = "/add-comment")
    public String addComment(Comment comment, HttpServletRequest request) {
        serviceComment.add(comment);
        return "redirect:" + request.getHeader("referer");
    }

    @PostMapping(value = "/like")
    public String addLike(UserLike like, HttpServletRequest request) {
        serviceLike.add(like);
        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping(value = "/{title}/{id}/add")
    public String addPage(@PathVariable(name = "id") long id, Model model, HttpServletRequest request, Principal principal) {
        String theme = principal == null ? "light" : serviceUser.findByLogin(principal.getName()).getDesign();
        model.addAttribute("design", theme);
        model.addAttribute("object", new Item());
        model.addAttribute("collection", serviceCollection.getById(id));
        model.addAttribute("tags", serviceTag.allTags());
        model.addAttribute("alcohol", serviceAlcohol.allAlcohols());
        model.addAttribute("materials", serviceCover.allCovers());
        model.addAttribute("exGenre", serviceGenre.allGenres());
        model.addAttribute("metals", serviceMetal.allMetals());
        model.addAttribute("jewelries", serviceJewelry.allJewelries());
        return "form_item";
    }

    @GetMapping(value = "/edit")
    public String editCollectionPage(@RequestParam(name = "id") Long id, Model model, Principal principal) {
        String theme = principal == null ? "light" : serviceUser.findByLogin(principal.getName()).getDesign();
        model.addAttribute("design", theme);
        model.addAttribute("editCollection", serviceCollection.getById(id));
        model.addAttribute("tags", serviceTag.allTags());
        model.addAttribute("fields", serviceField.allFields());
        return "form_collection";
    }

    @PostMapping(value = "/edit")
    public String editCollection(Collection collection) {
        serviceCollection.edit(collection);
        return "redirect:/user/profile";
    }
}
