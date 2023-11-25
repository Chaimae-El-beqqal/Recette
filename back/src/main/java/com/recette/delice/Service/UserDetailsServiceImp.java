package com.recette.delice.Service;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.recette.delice.Entity.User;
import com.recette.delice.Repository.UserRepository;
public class UserDetailsServiceImp implements UserDetailsService {
    
       
       @Autowired
    private UserRepository userRepository;
      @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<User> users = userRepository.findAllByEmail(email);

        if (!users.isEmpty()) {
            for (User user : users) {
                String encodedPassword = user.getPassword();
                return new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        encodedPassword,
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))
                );
            }
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
