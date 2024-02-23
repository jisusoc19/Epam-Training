package com.task3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task3.models.entity.Training;
import com.task3.models.service.training.iTrainingService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/apitraining")
public class trainingController {
	
	
	
	@Autowired
	private iTrainingService itrainingservice;
	
	@GetMapping("/training/{id}")
	public Training findall(@PathVariable Long id) {
		log.info("iniciando trainingController TRAINING");
		return itrainingservice.findbyid(id);
	}
	@PostConstruct
	public void init() {
		log.info("iniciando trainingController ");
	}
}
