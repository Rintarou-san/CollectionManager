package course.collectionmanager.converter;

import course.collectionmanager.model.ItemField;
import course.collectionmanager.repository.FieldRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToFieldList implements Converter<String[], List<ItemField>> {

    @Autowired
    private FieldRepository repository;

    @Override
    public List<ItemField> convert(String[] source) {
        List<ItemField> fieldsList = new ArrayList<>();
        for (String field : source) {
            fieldsList.add(repository.findById(Long.parseLong(field)).get());
        }
        return fieldsList;
    }

}
