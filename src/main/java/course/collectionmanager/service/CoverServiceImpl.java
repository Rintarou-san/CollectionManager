package course.collectionmanager.service;

import course.collectionmanager.model.Cover;
import course.collectionmanager.repository.CoverRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverServiceImpl implements CoverService {

    @Autowired
    private CoverRepository repository;

    @Override
    public List<Cover> allCovers() {
        List<Cover> covers = new ArrayList<>();
        repository.findAll().forEach(covers::add);
        return covers;
    }

}
