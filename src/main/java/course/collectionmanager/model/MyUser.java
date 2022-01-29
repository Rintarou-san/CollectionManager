package course.collectionmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class MyUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordRepeat;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_registration")
    private Date dateOfRegistration;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private boolean status;

    @Column(name = "design")
    private String design;

    @Column(name = "language")
    private String language;
    
    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserLike> likes;
    
    @OneToMany(mappedBy="user")
    @JsonBackReference
    private List<course.collectionmanager.model.Collection> collections;
    
}
