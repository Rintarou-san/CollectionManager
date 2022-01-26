package course.collectionmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Tag")
public class Tag {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy="tags")
    @JsonBackReference
    private Set<Item> items=new HashSet();

    @ManyToMany(mappedBy="tags")
    @JsonBackReference
    private Set<Collection> collections=new HashSet();

    @Override
    public String toString() {
        return "{" + "id=" + id + 
                ", name=" + name + 
                ", items=" + items + 
                ", collections=" + collections + '}';
    }
    
    
}
