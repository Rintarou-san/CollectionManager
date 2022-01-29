package course.collectionmanager.service;

import course.collectionmanager.model.Metal;
import course.collectionmanager.repository.MetalRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetalServiceImpl implements MetalService {

    @Autowired
    private MetalRepository repository;

    @Override
    public List<Metal> allMetals() {
        List<Metal> metals = new ArrayList<>();
        repository.findAll().forEach(metals::add);
        return metals;
    }

}
