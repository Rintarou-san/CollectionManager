package course.collectionmanager.service;

import course.collectionmanager.model.Jewelry;
import course.collectionmanager.repository.JewelryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JewelryServiceImpl implements JewelryService {

    @Autowired
    private JewelryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Jewelry> allJewelries() {
        List<Jewelry> jewelries = new ArrayList<>();
        repository.findAll().forEach(jewelries::add);
        return jewelries;
    }

}
