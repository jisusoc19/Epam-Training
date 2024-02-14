package com.task3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task3.models.service.trainingType.training_typeService;

@RestController
@RequestMapping("/trainingtype")
public class trainingtypeController {
	@Autowired
	private training_typeService trainingtypeservice;
	
	@GetMapping("/list")
	public void findAll(){
		trainingtypeservice.findALL();
	}
}
