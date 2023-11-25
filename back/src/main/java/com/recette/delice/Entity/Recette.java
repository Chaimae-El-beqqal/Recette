package com.recette.delice.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "recettes")
@Data
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;

    @ElementCollection
    private List<String> ingredients;

    @ElementCollection
    private List<String> etapesPreparation;

    private int dureePreparation;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User utilisateur;

    
}