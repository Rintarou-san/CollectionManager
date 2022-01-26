package course.collectionmanager.controller;

import course.collectionmanager.model.Collection;
import course.collectionmanager.service.CollectionService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collections")
public class CollectionRestController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> all() {
        List<Collection> collections = collectionService.allCollections();

        return collections != null && !collections.isEmpty() ? new ResponseEntity<>(collections, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/best")
    public ResponseEntity<Object> bestCollections() {
        List<Collection> collections = new ArrayList<>();

        //Search...
        return !collections.isEmpty() ? new ResponseEntity<>(collections, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
