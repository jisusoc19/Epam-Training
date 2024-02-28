package com.task3.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task3.service.trainingType.training_typeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/trainingtype")
public class trainingtypeController {
	@Autowired
	private training_typeService trainingtypeservice;
	
	@GetMapping("/list")
	public void findAll(){
		log.info("iniciando el controlador trainingtypecontroller");
		trainingtypeservice.findALL();
	}
}
