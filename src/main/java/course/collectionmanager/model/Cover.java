package course.collectionmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cover")
public class Cover {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String type;

    @OneToMany(mappedBy = "coverType", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Item> items;

    @Override
    public String toString() {
        return type;
    }
    
    
}
