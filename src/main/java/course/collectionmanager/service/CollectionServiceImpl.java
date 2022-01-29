package course.collectionmanager.service;

import course.collectionmanager.model.Collection;
import course.collectionmanager.model.MyUser;
import course.collectionmanager.repository.CollectionRepository;
import course.collectionmanager.repository.MyUserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository repository;

    @Autowired
    private MyUserRepository userRepository;

    @Override
    public List<Collection> allCollections() {
        List<Collection> collections = new ArrayList<>();
        repository.findAll().forEach(collections::add);
        return collections;
    }

    @Override
    public void add(Collection collection) {
        UserDetails userInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUser user = userRepository.findByLogin(userInfo.getUsername());
        collection.setUser(user);
        repository.save(collection);
    }

    @Override
    @Transactional
    public void edit(Collection collection) {
        Collection existCollection = repository.findById(collection.getId()).get();
        repository.delete(existCollection);
        repository.save(collection);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Collection getById(long id) {
        return repository.findById(id).get();
    }

}
