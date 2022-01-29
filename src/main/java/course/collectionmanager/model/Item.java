package course.collectionmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private Integer year;

    @Column(name = "year_of_publishing")
    private Integer yearOfPublishing;

    @Column(name = "price")
    private Double price;

    @Column(name = "country")
    private String country;

    @Column(name = "author")
    private String author;

    @Column(name = "fandom")
    private String fandom;

    @Column(name = "alcohol_percentage")
    private Double alcoholPercentage;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "alcohol_type")
    private Alcohol alcoholType;

    @Column(name = "number_of_sheets")
    private Integer numberOfSheets;

    @Column(name = "size_width")
    private Integer sizeWidth;

    @Column(name = "size_height")
    private Integer sizeHeight;

    @Column(name = "page_line")
    private Boolean pageLine;

    @Column(name = "dating")
    private Boolean dating;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "cover_type")
    private Cover coverType;

    @Column(name = "paper_density")
    private Integer paperDensity;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "item_genre",
            joinColumns = @JoinColumn(name = "itemID"),
            inverseJoinColumns = @JoinColumn(name = "genreID"))
    private List<Genre> genres;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "item_tag",
            joinColumns = @JoinColumn(name = "itemID"),
            inverseJoinColumns = @JoinColumn(name = "tagID"))
    private List<Tag> tags;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<UserLike> likes;

}
