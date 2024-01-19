package com.task3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task3.models.entity.Trainer;
import com.task3.models.service.trainer.iTrainerService;

import jakarta.annotation.PostConstruct;
@RestController
@RequestMapping("/apitrainer")
public class itranerController {
	
	private static final Logger logger = LoggerFactory.getLogger(itranerController.class);
	
	@Autowired
	private iTrainerService itrainerservice;
	
	@GetMapping("/apitrainer/{id}")
	public Trainer findbyid(@PathVariable Long id){
		return itrainerservice.findById(id);
		
		
	}
	@GetMapping("/trainer")
	public void findAll(){
		logger.info("iniciando itrainerControler Find all");
		itrainerservice.findAll();
		
		
	}
	@PostMapping("/trainer")
	public Trainer save(@RequestBody Trainer trainer) {
		return itrainerservice.save(trainer);
	}
	@PutMapping("/trainer/{id}")
	public Trainer update(@RequestBody Trainer trainer ,@PathVariable Long id) {
		return itrainerservice.update(trainer, id);
	}
	
	@PostConstruct
	public void init() {
		logger.info("iniciando itrainerController");
	}
	

}
