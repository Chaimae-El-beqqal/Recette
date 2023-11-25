package com.recette.delice.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.recette.delice.DTO.*;

import com.recette.delice.Entity.*;

import com.recette.delice.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void updateAccessToken(long id, String accessToken) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        user.setAccessToken(accessToken);
        userRepository.save(user);
    }

    public String getAccessToken(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getAccessToken();
        } else {
            return null; // or you can return a default value or handle the case accordingly
        }
    }


    //==========================================

    public UserDTO getUserDTOById(Long userId) {
            // Récupérer la user à partir de l'ID
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("user non trouvée avec l'ID : " + userId));

            // Créer un userDTO à partir de la user
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());

            return userDTO;
    }
    public User getUserById(Long userId) {
            // Récupérer la user à partir de l'ID
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("user non trouvée avec l'ID : " + userId));
            return user;
    }
  

    public boolean createUser(UserDTO userDTO) {
        // Vérifier si l'utilisateur existe déjà par son nom d'utilisateur
    User existingUser = userRepository.getReferenceById(userDTO.getId());

    if (existingUser == null) {
        // Hasher le mot de passe avant de l'enregistrer dans la base de données
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Convertir le UserDTO en entité User
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());  // Notez que le mot de passe a déjà été hashé

        // Enregistrer l'utilisateur dans la base de données
        userRepository.save(newUser);

        return true;
    } else {
        return false; // L'utilisateur existe déjà
    }}
    
}
