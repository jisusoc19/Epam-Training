package com.task3.Repository;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task3.Entity.Trainee;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

public interface iTraineedao extends CrudRepository<Trainee,Long> {
	
	
	@Transactional
	@Query("SELECT t FROM Trainer t WHERE t.username = :username")
	Optional<Trainee> findbyusername(String username);
	@Transactional
	@Query("SELECT t FROM Trainer t WHERE t.username = :username AND t.password = :password")
	Optional<Trainee> loggin(String username, String password );

    @Transactional
    @Modifying
    @Query("DELETE FROM Trainer t WHERE t.username = :username")
    void deleteByUsername(String username);
    
   

}
