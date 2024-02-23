package com.task3.models.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task3.models.entity.Training;
import com.task3.models.entity.User;

import jakarta.annotation.PostConstruct;
@Repository
public interface iTrainingdao extends JpaRepository<Training, Long> {


}
