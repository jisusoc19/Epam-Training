package com.task3.controller;




import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.task3.models.entity.Trainee;
import com.task3.models.service.trainee.iTraineeService;
import org.slf4j.Logger;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/apitrainee")
public class traineeController {
	
	private static final Logger logger = LoggerFactory.getLogger(traineeController.class);
	
	@Autowired
	private final iTraineeService traineeservice;

	public traineeController(iTraineeService traineeservice) {
		logger.info("Controller GetAll iniciado");
		this.traineeservice = traineeservice;
	}

	@GetMapping("/trainee")
	public void index(){
		logger.info("Controller GetById iniciado");
		traineeservice.findAll();
	}


//	public Trainee findbyid(@PathVariable Long id) {
//		return traineeservice.findById(id);
//
//	}
	@PostMapping("/trainee")
	public Trainee save(@RequestBody Trainee trainee) {
		return traineeservice.save(trainee);

	}
	@GetMapping("/trainee/{id}")
	public Trainee findById(@PathVariable Long id){
		logger.info("Controller GetById iniciado");
		return traineeservice.findById(id);

	}
	@DeleteMapping("/trainee/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		logger.info("Controller Delete iniciado");
		traineeservice.delete(id);
		return new ResponseEntity<>("Trainee eliminado con exito",HttpStatus.OK);
	}
	
	@PostConstruct
	public void init() {
		logger.info("Controller iniciado");
	}
	@PutMapping("/trainee/{id}")
	public Trainee update(@RequestBody Trainee trainee , @PathVariable Long id) {
		logger.info("Controller Update iniciado");
		return traineeservice.update(trainee, id);
		
	}

}
