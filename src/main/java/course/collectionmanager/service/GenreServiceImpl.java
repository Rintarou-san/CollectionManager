package course.collectionmanager.service;

import course.collectionmanager.model.Genre;
import course.collectionmanager.repository.GenreRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> allGenres() {
        List<Genre> genres = new ArrayList<>();
        repository.findAll().forEach(genres::add);
        return genres;
    }

}
