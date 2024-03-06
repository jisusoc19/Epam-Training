package com.task3.Controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task3.Entity.Trainee;
import com.task3.Entity.Trainer;
import com.task3.service.trainer.iTrainerService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/apitrainer")
@Slf4j
public class itranerController {
	
	
	@Autowired
	private iTrainerService itrainerservice;
	
	
	@GetMapping("/trainer/{id}")
	public Trainer findbyid(@PathVariable Long id){
		log.info("iniciando itrainerControler findById");
		return itrainerservice.findById(id);
		
	}
	
	@GetMapping("/trainer/user/{username}")
	public Trainer findbyusername(@PathVariable String username){
		log.info("iniciando itrainerControler findByUsername");
		return itrainerservice.findbyusername(username);
		
		
	}
	
	@GetMapping("/trainer")
	public List<Trainer> findAll(){
		log.info("iniciando itrainerControler Find all");
		
		return itrainerservice.findAll();
		
		
	}
	@PostMapping("/trainer")
	public Trainer save(@RequestBody Trainer trainer) {
		log.info("iniciando itrainerControler save");
		return itrainerservice.save(trainer);
	}
	@PutMapping("/trainer/{id}")
	public Trainer update(@RequestBody Trainer trainer ,@PathVariable Long id) {
		return itrainerservice.update(trainer, id);
	}
	
	@PostConstruct
	public void init() {
		log.info("iniciando itrainerController");
	}

	
	@PatchMapping("/trainer/active")
	public Trainer active(@PathVariable String username, Boolean status){
		log.info("iniciando itrainerControler active");
		return itrainerservice.activeTranierTrainee(username, status);
	}
	
	
	

}
