package course.collectionmanager.service;

import course.collectionmanager.model.MyUser;
import java.util.List;

public interface UserService {

    public List<MyUser> allUsers();
    
    public MyUser findByLogin(String login);
    
    public MyUser getById(long id);

    public boolean add(MyUser user);
    
    public void setStatus(MyUser user);
    
    public void setRole(MyUser user);
    
    public void setLastLogin(MyUser user);

    public boolean delete(long id);

}
