package SPRING_FREESTYLE_TodoList.service;

import SPRING_FREESTYLE_TodoList.model.Users;
import SPRING_FREESTYLE_TodoList.model.UserPrincipal;
import SPRING_FREESTYLE_TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//3
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserPrincipal(user);
    }
}
