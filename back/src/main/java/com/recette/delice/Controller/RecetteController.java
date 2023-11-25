package com.recette.delice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.recette.delice.DTO.RecetteDTO;
import com.recette.delice.Service.RecetteService;

public class RecetteController {
    @Autowired
    private RecetteService recetteService;

    @PostMapping
    public ResponseEntity<String> createRecette(@RequestBody RecetteDTO recetteDTO) {
        boolean isCreated = recetteService.createRecette(recetteDTO);

        if (isCreated) {
            return new ResponseEntity<>("Recette créée avec succès", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Utilisateur non trouvé avec l'ID fourni", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{recetteId}")
    public ResponseEntity<RecetteDTO> getRecetteById(@PathVariable Long recetteId) {
        RecetteDTO recetteDTO = recetteService.getRecetteById(recetteId);
        return new ResponseEntity<>(recetteDTO, HttpStatus.OK);
    }

    @PutMapping("/{recetteId}")
    public ResponseEntity<String> updateRecette(@PathVariable Long recetteId, @RequestBody RecetteDTO updatedRecetteDTO) {
        boolean isUpdated = recetteService.updateRecette(recetteId, updatedRecetteDTO);

        if (isUpdated) {
            return new ResponseEntity<>("Recette mise à jour avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Recette non trouvée avec l'ID fourni", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{recetteId}")
    public ResponseEntity<String> deleteRecette(@PathVariable Long recetteId) {
        boolean isDeleted = recetteService.deleteRecette(recetteId);

        if (isDeleted) {
            return new ResponseEntity<>("Recette supprimée avec succès", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Recette non trouvée avec l'ID fourni", HttpStatus.NOT_FOUND);
        }
    }
}
