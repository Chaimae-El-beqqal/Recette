package com.recette.delice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.recette.delice.DTO.UserDTO;
import com.recette.delice.Service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO user = userService.getUserDTOById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO user) {
        boolean isCreated = userService.createUser(user);

        if (isCreated) {
            return new ResponseEntity<>("Utilisateur enregistré avec succès", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("L'utilisateur existe déjà", HttpStatus.CONFLICT);
        }
    }
}