package course.collectionmanager.service;

import course.collectionmanager.model.Genre;
import course.collectionmanager.repository.GenreRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository repository;

    @Override
    public List<Genre> allGenres() {
        List<Genre> genres = new ArrayList<>();
        repository.findAll().forEach(genres::add);
        return genres;
    }

}
