package course.collectionmanager.controller;

import course.collectionmanager.model.Collection;
import course.collectionmanager.model.Item;
import course.collectionmanager.service.CollectionService;
import course.collectionmanager.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionRestController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/collections/all")
    public ResponseEntity<Object> all() {
        List<Collection> collections = collectionService.allCollections();
        return collections != null && !collections.isEmpty() ? new ResponseEntity<>(collections, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/tags")
    public ResponseEntity<Object> byTag(@RequestParam(name = "id") Long id) {
        List<Collection> collections = collectionService.findByTagId(id);
        List<Item> items = itemService.findByTagId(id);
        List<Object> resp = new ArrayList<>();
        resp.addAll(0, collections);
        resp.addAll(collections.size(), items);
        return collections != null && !collections.isEmpty() ? new ResponseEntity<>(resp, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/collections/best")
    public ResponseEntity<Object> bestCollections() {
        List<Collection> collections = new ArrayList<>();

        //Search...
        return !collections.isEmpty() ? new ResponseEntity<>(collections, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
