package course.collectionmanager.repository;

import course.collectionmanager.model.MyUser;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    public MyUser findByLogin(String login);

    public MyUser findByEmail(String email);

    @Modifying
    @Query("update MyUser u set u.status=?1 where u.id=?2")
    void setStatusById(boolean status, Long id);

    @Modifying
    @Query("update MyUser u set u.role=?1 where u.id=?2")
    void setRoleById(String role, Long id);

    @Modifying
    @Query("update MyUser u set u.design=?1 where u.id=?2")
    void setDesignById(String design, Long id);

    @Modifying
    @Query("update MyUser u set u.description=?1 where u.id=?2")
    void setDescriptionById(String description, Long id);

    @Transactional
    @Modifying
    @Query("update MyUser u set u.lastLogin=?1 where u.id=?2")
    void setLastLoginById(Date lastLogin, Long id);
}
