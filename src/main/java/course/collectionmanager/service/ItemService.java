package course.collectionmanager.service;

import course.collectionmanager.model.Item;
import java.util.List;

public interface ItemService {

    public List<Item> allItems();

    public List<Item> findByTagId(Long id);

    public void add(Item item);

    public void delete(long id);

    public void edit(Item item);

    public Item getById(long id);
}
