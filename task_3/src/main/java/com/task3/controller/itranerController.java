package com.task3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/trainer/{id}", method = RequestMethod.GET)
	public Trainer findbyid(@PathVariable Long id){
		return itrainerservice.findById(id);
		
		
	}
	@RequestMapping(value = "/trainer" , method = RequestMethod.POST)
	public Trainer save(@RequestBody Trainer trainer) {
		return itrainerservice.save(trainer);
	}
	@RequestMapping(value = "/trainer/{id}", method = RequestMethod.PUT)
	public Trainer update(@RequestBody Trainer trainer ,@PathVariable Long id) {
		return itrainerservice.update(trainer, id);
	}
	
	@PostConstruct
	public void init() {
		logger.info("iniciando itrainerController");
	}
	

}
