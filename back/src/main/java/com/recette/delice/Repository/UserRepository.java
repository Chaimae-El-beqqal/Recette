package com.recette.delice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recette.delice.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findAllByEmail(String email);
    
}
