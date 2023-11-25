package com.recette.delice.Entity;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import lombok.Data;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Vous pouvez ajouter d'autres propriétés utilisateur si nécessaire

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Recette> recettes = new HashSet<>();

   
}