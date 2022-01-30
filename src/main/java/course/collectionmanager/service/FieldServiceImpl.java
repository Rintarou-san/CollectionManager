package course.collectionmanager.service;

import course.collectionmanager.model.ItemField;
import course.collectionmanager.repository.FieldRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<ItemField> allFields() {
        List<ItemField> fields = new ArrayList<>();
        repository.findAll().forEach(fields::add);
        return fields;
    }

    @Override
    @Transactional(readOnly = true)
    public ItemField findById(long id) {
        return repository.findById(id).get();
    }

}
