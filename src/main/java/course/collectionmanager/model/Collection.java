package course.collectionmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Collection")
public class Collection {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "collection")
    @JsonManagedReference
    private List<Item> items;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "collection_tag",
            joinColumns = @JoinColumn(name = "collectionID"),
            inverseJoinColumns = @JoinColumn(name = "tagID"))
    private List<Tag> tags;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "collection_field",
            joinColumns = @JoinColumn(name = "collectionID"),
            inverseJoinColumns = @JoinColumn(name = "fieldID"))
    private List<ItemField> fields;

    @OneToMany(mappedBy = "collection")
    @JsonManagedReference
    private List<UserLike> likes;
    
    @OneToMany(mappedBy = "collection")
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private MyUser user;

}
