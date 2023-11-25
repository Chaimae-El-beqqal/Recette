package com.recette.delice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recette.delice.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
