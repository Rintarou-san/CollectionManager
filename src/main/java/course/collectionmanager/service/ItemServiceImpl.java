package course.collectionmanager.service;

import course.collectionmanager.model.Item;
import course.collectionmanager.repository.ItemRepository;
import course.collectionmanager.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Item> allItems() {
        List<Item> items = new ArrayList<>();
        repository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public void add(Item item) {
        repository.save(item);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void edit(Item item) {

    }

    @Override
    public Item getById(long id) {
        return (Item) repository.findById(id).get();
    }

    @Override
    public List<Item> findByTagId(Long id) {
        List<Item> items = new ArrayList<>();
        repository.findByTags(tagRepository.findById(id).get()).forEach(items::add);
        return items;
    }

}
