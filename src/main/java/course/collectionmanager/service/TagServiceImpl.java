package course.collectionmanager.service;

import course.collectionmanager.model.Tag;
import course.collectionmanager.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Tag> allTags() {
        List<Tag> tags = new ArrayList<>();
        repository.findAll().forEach(tags::add);
        return tags;
    }

    @Override
    @Transactional(readOnly = true)
    public Tag getById(long id) {
        return repository.findById(id).get();
    }

}
