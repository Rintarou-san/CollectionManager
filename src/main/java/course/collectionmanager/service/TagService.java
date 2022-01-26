package course.collectionmanager.service;

import course.collectionmanager.model.Tag;
import java.util.List;

public interface TagService {

    public List<Tag> allTags();

    public Tag getById(long id);
}
