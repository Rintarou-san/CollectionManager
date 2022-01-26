package course.collectionmanager.service;

import course.collectionmanager.model.MyUser;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import course.collectionmanager.repository.MyUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MyUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        repository.setLastLoginById(new Date(), user.getId());
        UserDetails userDetails = User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        return userDetails;
    }
}
