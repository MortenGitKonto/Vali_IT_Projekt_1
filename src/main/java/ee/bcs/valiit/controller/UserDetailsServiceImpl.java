package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        String password = bankRepository.getPassword(username);
        return User.withUsername(username)
                .password(password)
                .roles("USER").build();
    }
}
