package course.collectionmanager.service;

import course.collectionmanager.model.Metal;
import course.collectionmanager.repository.MetalRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetalServiceImpl implements MetalService {

    @Autowired
    private MetalRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Metal> allMetals() {
        List<Metal> metals = new ArrayList<>();
        repository.findAll().forEach(metals::add);
        return metals;
    }

}
