package course.collectionmanager.service;

import course.collectionmanager.model.Item;
import course.collectionmanager.repository.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Transactional
    @Override
    public List<Item> allItems() {
        List<Item> items = new ArrayList<>();
        repository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public void add(Item item) {

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

}
