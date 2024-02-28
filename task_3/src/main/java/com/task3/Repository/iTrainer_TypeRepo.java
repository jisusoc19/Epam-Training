package com.task3.Repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task3.Entity.Trainer;
import com.task3.Entity.Training_Type;

import jakarta.annotation.PostConstruct;
@Repository
public interface iTrainer_TypeRepo extends CrudRepository<Training_Type,Long> {

}
