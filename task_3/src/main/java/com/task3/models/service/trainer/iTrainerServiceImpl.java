package com.task3.models.service.trainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task3.models.Repository.iTrainerdao;
import com.task3.models.entity.Trainee;
import com.task3.models.entity.Trainer;
import com.task3.models.entity.User;

import RestHandlerException.ResouceNotFoundException;
import jakarta.annotation.PostConstruct;

@Service
public class iTrainerServiceImpl implements iTrainerService {
	private static final Logger logger = LoggerFactory.getLogger(iTrainerServiceImpl.class);
	
	@Autowired
	public iTrainerdao itrainerdaoJPA;
	
	@Transactional(readOnly = false)
	@Override
	public Trainer save(Trainer trainer) {
		if(trainer==null || trainer.getIsActive()==null) {
			throw new ResouceNotFoundException("Error al guardar" );
		} else {
			itrainerdaoJPA.save(trainer);
			logger.info("trainer guardado con exito");
			
		}
		return trainer;
	}
	@Transactional(readOnly = true)
	@Override
	public Trainer findById(Long id) {
		if(itrainerdaoJPA.findById(id) == null || itrainerdaoJPA.findById(id).isEmpty()) {
			logger.error("Trainer con id: " + id + "No encontrado");
			throw new ResouceNotFoundException("cliente", "id" ,id);
		}else {
			logger.info("Trainer encontrado con id: " + id + " Encontrado " );
			return itrainerdaoJPA.findById(id).orElse(null);
		}
		
	}
	@Transactional(readOnly = false)
	@Override
	public Trainer update(Trainer trainer, Long id) {
		Trainer Aactual = itrainerdaoJPA.findById(id).orElse(null);
		Trainer nuevotra=null;
	    if (!itrainerdaoJPA.findById(id).isPresent()) {
	        // Update the trainer
	    	throw new ResouceNotFoundException("Trainer with ID " + id + " no update in the database");
	    }else {
	    	Aactual.setFirstName(trainer.getFirstName());
	    	Aactual.setLastName(trainer.getLastName());
	    	Aactual.setUsername(generateUniqueUsername(trainer.getFirstName(),trainer.getLastName()));
	    	Aactual.setTrainertype(trainer.getTrainertype());
	    	nuevotra =itrainerdaoJPA.save(Aactual);
	    	logger.info("Trainer actualizado");
	    	return nuevotra;
	    }
	
	}
	
	    
	
	@PostConstruct
	public void init() {
		logger.info("Iniciando el iTrainerServiceImpl");
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Trainer> findAll() {
		List<Trainer> lista = (List<Trainer>) itrainerdaoJPA.findAll();
		if(lista==null || lista.isEmpty()) {
			throw new ResouceNotFoundException("clientes");
		}
		return (List<Trainer>) itrainerdaoJPA.findAll();
	}
	
	@Transactional
	@Override
	public String generateUniqueUsername(String firstName, String lastName) {
		String gUsername = firstName.toLowerCase() + "." + lastName.toLowerCase();
		String username = gUsername;
		Integer nSerail = 1;
		while (itrainerdaoJPA.findbyusername(username).isPresent()) {
			username = gUsername + nSerail;
			nSerail++;
		}
		
		return username;
	}
	@Transactional
	@Override
	public String generatePasword(String password) {
	    int length = 10;
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(letras.length());
            sb.append(letras.charAt(randomIndex));
        }  
        return sb.toString();
    }
		
	  
	@Transactional
	@Override
	public Trainer findbyusername(String username) {
		Trainer trainer =itrainerdaoJPA.findbyusername(username).orElse(null);
		if (itrainerdaoJPA.findbyusername(username).isPresent()) {
			return trainer;
			
		}else {
			throw new ResouceNotFoundException();
		}
		
	}

	@Transactional
	@Override
	public Boolean loggin(String username, String Password) {
		Trainer traineeE = itrainerdaoJPA.findbyusername(username).orElse(null);
		if(itrainerdaoJPA.findbyusername(username).isPresent()) {
			if(traineeE.getPassword().equals(Password));
			return true;			
		}
		else {
			return false;
		}
		
	}
	@Override
	@Transactional
	public Trainer logginUpdate(String username, String PasswordNew, String Passwordold) {
		Trainer trainerE = itrainerdaoJPA.findbyusername(username).orElse(null);
		if(trainerE.getUsername().equals(username)) {
			if(trainerE.getPassword().equals(Passwordold));
			trainerE.setPassword(PasswordNew);
			itrainerdaoJPA.save(trainerE);
			return trainerE;			
		}
		else {
			return null;
		}
	}
	@Transactional
	@Override
	public Trainer activeTranierTrainee(String username, Boolean status) {
		
		Trainer trainer = itrainerdaoJPA.findbyusername(username).orElse(null);
		if(itrainerdaoJPA.findbyusername(username).isPresent()) {
			trainer.setIsActive(status);
			itrainerdaoJPA.save(trainer);
		}else {
			throw new ResouceNotFoundException();
		}
		
		return trainer;
		
	}


}
