package com.recette.delice.DTO;

import java.util.List;

import lombok.Data;

@Data
public class RecetteDTO {
    private Long id;
    private String nom;

    private List<String> ingredients;

    private List<String> etapesPreparation;

    private int dureePreparation;

    private String photo;
    private Long idUser;
}
