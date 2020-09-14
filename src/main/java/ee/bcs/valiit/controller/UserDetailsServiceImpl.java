package ee.bcs.valiit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    Integer id = 0;

    public Integer getIdLogIn() {
return id;
    }


    @Autowired
    private BankRepository bankRepository;



    @Override
    public UserDetails loadUserByUsername(String username){

        //Get currently logged in user id
        id = bankRepository.getClientId(username);

        ////Get password HASH to check the password when logging in
        String password = bankRepository.getPassword(username);

        return User.withUsername(username)
                .password(password)
                .roles("USER").build();
    }
}
