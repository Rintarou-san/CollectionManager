package course.collectionmanager.service;

import course.collectionmanager.model.Collection;
import java.util.List;

public interface CollectionService {

    public List<Collection> allCollections();

    public List<Collection> findByTagId(Long id);

    public void add(Collection collection);

    public void edit(Collection collection);

    public void delete(long id);

    public Collection getById(long id);

    public Collection largestCollection();

    public Collection likeCollection();

    public Collection commentCollection();
}
