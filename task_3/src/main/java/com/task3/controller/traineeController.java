package com.task3.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.task3.Entity.Trainee;
import com.task3.service.trainee.iTraineeService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/apitrainee")
public class traineeController {
	
	@Autowired
	private iTraineeService traineeservice;

	

	@GetMapping("/trainee")
	public List<Trainee> index(){
		log.info("traineeControler GetList iniciado");
		return traineeservice.findAll();
		
	}

//	public Trainee findbyid(@PathVariable Long id) {
//		return traineeservice.findById(id);
//
//	}
	@PostMapping("/trainee")
	public Trainee save(@RequestBody Trainee trainee) {
		log.info("traineeControler save iniciado");
		return traineeservice.save(trainee);

	}
	@GetMapping("/trainee/{id}")
	public Trainee findById(@PathVariable Long id){
		log.info("traineeControler GetById iniciado");
		return traineeservice.findById(id);

	}
	@DeleteMapping("/trainee/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("traineeControler delete iniciado");
		traineeservice.delete(id);
		return new ResponseEntity<>("Trainee eliminado con exito",HttpStatus.OK);
	}
	
	@PostConstruct
	public void init() {
		log.info("traineeControler iniciado");
	}
	@PutMapping("/trainee/{id}")
	public Trainee update(@RequestBody Trainee trainee , @PathVariable Long id) {
		log.info("traineeControler Update iniciado");
		return traineeservice.update(trainee, id);
		
	}

	@PatchMapping("/trainee/{username}/{status}")
	public Trainee updateTraineestatus(@PathVariable String username, Boolean status) {
		log.info("iniciando traineeControler updateTraineestatus ");
		return traineeservice.updateStatususername(username, status);
	}

}
