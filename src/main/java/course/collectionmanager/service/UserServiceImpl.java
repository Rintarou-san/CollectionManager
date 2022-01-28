package course.collectionmanager.service;

import course.collectionmanager.model.MyUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import course.collectionmanager.repository.MyUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    private MyUserRepository repository;

    @Override
    public List<MyUser> allUsers() {
        List<MyUser> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public boolean add(MyUser user) {
        if (repository.findByLogin(user.getLogin()) != null || repository.findByEmail(user.getEmail()) != null) {
            return false;
        }
        user.setDateOfRegistration(new Date());
        user.setPassword(bCrypt.encode(user.getPassword()));
        user.setRole("USER");
        user.setDesign("light");
        user.setLanguage("english");
        repository.save(user);
        return true;
    }

    @Override
    public boolean delete(long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public MyUser getById(long id) {
        return repository.findById(id).get();
    }

    @Override
    @Transactional
    public void setStatus(MyUser user) {
        repository.setStatusById(user.isStatus(), user.getId());
    }

    @Override
    @Transactional
    public void setRole(MyUser user) {
        repository.setRoleById(user.getRole(), user.getId());
    }

    @Override
    @Transactional
    public void setLastLogin(MyUser user) {
        repository.setLastLoginById(new Date(), user.getId());
    }

    @Override
    public MyUser findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    @Transactional
    public void setDescription(String description) {
        UserDetails userInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUser user = repository.findByLogin(userInfo.getUsername());
        repository.setDescriptionById(description, user.getId());
    }
}