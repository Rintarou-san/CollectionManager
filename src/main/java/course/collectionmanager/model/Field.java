package course.collectionmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Field")
public class Field {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "class_field")
    private String classField;

    @Column(name = "type")
    private String type;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "collection_field",
            joinColumns = @JoinColumn(name = "fieldID"),
            inverseJoinColumns = @JoinColumn(name = "collectionID"))
    private List<Collection> collections;

}
