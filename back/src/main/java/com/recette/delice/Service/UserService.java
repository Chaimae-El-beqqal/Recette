package com.recette.delice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.recette.delice.Entity.User;
import com.recette.delice.Repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

  

    public boolean createUser(UserDTO user) {
        if (userRepository.findById(user.getId()) == null) {
            // Hasher le mot de passe avant de l'enregistrer dans la base de données
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Enregistrer l'utilisateur dans la base de données
            userRepository.save(user);

            return true;
        } else {
            return false; // L'utilisateur existe déjà
        }
    }
    
}
