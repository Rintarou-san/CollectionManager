package course.collectionmanager.converter;

import course.collectionmanager.model.Genre;
import course.collectionmanager.repository.GenreRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToGenreList implements Converter<String[], List<Genre>> {

     @Autowired
    private GenreRepository repository;
    
    @Override
    public List<Genre> convert(String[] source) {
         List<Genre> genresList = new ArrayList<>();
        for (String genre : source) {
            genresList.add(repository.findById(Long.parseLong(genre)).get());
        }
        return genresList;
    }
    
}
