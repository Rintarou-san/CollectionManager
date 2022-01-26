package course.collectionmanager.converter;

import course.collectionmanager.model.Field;
import course.collectionmanager.repository.FieldRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToFieldList implements Converter<String[], List<Field>> {

    @Autowired
    private FieldRepository repository;

    @Override
    public List<Field> convert(String[] source) {
        List<Field> fieldsList = new ArrayList<>();
        for (String field : source) {
            fieldsList.add(repository.findById(Long.parseLong(field)).get());
        }
        return fieldsList;
    }

}
