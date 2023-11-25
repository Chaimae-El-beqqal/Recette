package com.recette.delice.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.recette.delice.DTO.*;
import com.recette.delice.Entity.Recette;
import com.recette.delice.Entity.User;
import com.recette.delice.Repository.*;

import jakarta.persistence.EntityNotFoundException;

public class RecetteService {
     @Autowired
    private RecetteRepository recetteRepository;
    @Autowired 
        private UserService userService;

// creer une recette
    public boolean createRecette(RecetteDTO recette) {
         
        // Vérifiez si l'utilisateur existe
        Long userId = recette.getIdUser();
        User utilisateur = userService.getUserById(userId);
         // Vérifier si l'utilisateur existe
            if (utilisateur == null) {
                // Utilisateur non trouvé
                throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + userId);
            }

            // Créer une entité Recette à partir du DTO
            Recette newRecette = new Recette();
            newRecette.setNom(recette.getNom());
            newRecette.setIngredients(recette.getIngredients());
            newRecette.setEtapesPreparation(recette.getEtapesPreparation());
            newRecette.setDureePreparation(recette.getDureePreparation());
            newRecette.setPhoto(recette.getPhoto());
            newRecette.setUtilisateur(utilisateur);

            // Enregistrer la recette dans la base de données
            return true;
    }

//   lire la recette
    public RecetteDTO getRecetteById(Long recetteId) {
         // Récupérer la recette à partir de l'ID
    Recette recette = recetteRepository.findById(recetteId)
    .orElseThrow(() -> new EntityNotFoundException("Recette non trouvée avec l'ID : " + recetteId));

        // Créer un RecetteDTO à partir de la Recette
        RecetteDTO recetteDTO = new RecetteDTO();
        recetteDTO.setId(recette.getId());
        recetteDTO.setNom(recette.getNom());
        recetteDTO.setIngredients(recette.getIngredients());
        recetteDTO.setEtapesPreparation(recette.getEtapesPreparation());
        recetteDTO.setDureePreparation(recette.getDureePreparation());
        recetteDTO.setPhoto(recette.getPhoto());
        recetteDTO.setIdUser(recette.getUtilisateur().getId());  

        return recetteDTO;
    }

  // Mettez à jour les champs de la recette existante avec les nouvelles valeurs
    public boolean  updateRecette(Long recetteId, RecetteDTO updatedRecetteDTO) {
        Optional<Recette> existingRecetteOptional = recetteRepository.findById(recetteId);

    if (existingRecetteOptional.isPresent()) {
        Recette existingRecette = existingRecetteOptional.get();

        // Mettez à jour les propriétés de la recette existante avec les nouvelles valeurs
        existingRecette.setNom(updatedRecetteDTO.getNom());
        existingRecette.setIngredients(updatedRecetteDTO.getIngredients());
        existingRecette.setEtapesPreparation(updatedRecetteDTO.getEtapesPreparation());
        existingRecette.setDureePreparation(updatedRecetteDTO.getDureePreparation());
        existingRecette.setPhoto(updatedRecetteDTO.getPhoto());

        // Enregistrez les modifications dans la base de données
        recetteRepository.save(existingRecette);

        // Retournez true pour indiquer que la mise à jour a réussi
        return true;
    }else {
        // Gérer le cas où la recette n'est pas trouvée
        throw new EntityNotFoundException("Recette non trouvée avec l'ID : " + recetteId);
    }
    }

     // Supprimez la recette de la base de données
    public boolean deleteRecette(Long recetteId) {
           // Récupérer la recette existante à partir de l'ID
            Optional<Recette> existingRecetteOptional = recetteRepository.findById(recetteId);

            if (existingRecetteOptional.isPresent()) {
                Recette recette = existingRecetteOptional.get();

                // Supprimer la recette de la base de données
                recetteRepository.delete(recette);

                // Retournez true pour indiquer que la suppression a réussi
                return true;
            } else {
                throw new EntityNotFoundException("Recette non trouvée avec l'ID : " + recetteId);
            }
    }
       
}
