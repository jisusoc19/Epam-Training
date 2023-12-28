package com.task3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task3.models.entity.Training;
import com.task3.models.service.training.iTrainingService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/apitraining")
public class trainingController {
	
	private static final Logger logger = LoggerFactory.getLogger(traineeController.class);
	
	@Autowired
	private iTrainingService itrainingservice;
	
	@RequestMapping(value = "/training/{id}")
	public Training findall(@PathVariable Long id) {
		return itrainingservice.findbyid(id);
	}
	@PostConstruct
	public void init() {
		logger.info("iniciando Controlador TRAINING");
	}
}
