package course.collectionmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User_like")
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private MyUser user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "item_id")
    private Item item;
}
