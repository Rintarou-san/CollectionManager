package course.collectionmanager.converter;

import course.collectionmanager.model.Tag;
import course.collectionmanager.repository.TagRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToTagList implements Converter<String[], List<Tag>> {

    @Autowired
    private TagRepository repository;

    @Override
    public List<Tag> convert(String[] source) {
        List<Tag> tagsList = new ArrayList<>();
        for (String tag : source) {
            tagsList.add(repository.findById(Long.parseLong(tag)).get());
        }
        return tagsList;
    }
    
}
