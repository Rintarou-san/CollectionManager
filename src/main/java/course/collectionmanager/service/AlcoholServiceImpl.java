package course.collectionmanager.service;

import course.collectionmanager.model.Alcohol;
import course.collectionmanager.repository.AlcoholRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlcoholServiceImpl implements AlcoholService {

    @Autowired
    private AlcoholRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Alcohol> allAlcohols() {
        List<Alcohol> alcohols = new ArrayList<>();
        repository.findAll().forEach(alcohols::add);
        return alcohols;
    }

}
