package course.collectionmanager.controller;

import course.collectionmanager.model.Field;
import course.collectionmanager.model.Item;
import course.collectionmanager.service.FieldService;
import course.collectionmanager.service.ItemService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private FieldService fieldService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> all() {
        List<Item> items = itemService.allItems();
        return items != null && !items.isEmpty() ? new ResponseEntity<>(items, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{title}")
    public String detail(@PathVariable(name = "title") String title, @RequestParam(name = "id", required = true) long id, Model model) {
        model.addAttribute("item", itemService.getById(id));
        model.addAttribute("items", itemService.allItems());
       return "detail_item";
    }
    
    @GetMapping(value="/fields")
    public ResponseEntity<Object> allFields() {
        List<Field> fields = fieldService.allFields();
        return fields != null && !fields.isEmpty() ? new ResponseEntity<>(fields, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(value = "/add")
    public String addItem(HttpServletRequest request, Item item) {
        itemService.add(item);
        return "redirect:" + request.getHeader("referer");
    }
}
