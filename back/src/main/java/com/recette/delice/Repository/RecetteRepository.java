package com.recette.delice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recette.delice.Entity.Recette;

public interface RecetteRepository extends JpaRepository<Recette, Long>{
    
}
