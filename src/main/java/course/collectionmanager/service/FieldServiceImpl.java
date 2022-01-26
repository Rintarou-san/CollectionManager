package course.collectionmanager.service;

import course.collectionmanager.model.Field;
import course.collectionmanager.repository.FieldRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository repository;

    @Override
    public List<Field> allFields() {
        List<Field> fields = new ArrayList<>();
        repository.findAll().forEach(fields::add);
        return fields;
    }

    @Override
    public Field findById(long id) {
        return repository.findById(id).get();
    }

}
