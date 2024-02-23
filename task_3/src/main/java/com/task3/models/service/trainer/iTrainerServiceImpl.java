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
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class iTrainerServiceImpl implements iTrainerService {
	
	
	@Autowired
	public iTrainerdao itrainerdaoJPA;
	
	@Transactional(readOnly = false)
	@Override
	public Trainer save(Trainer trainer) {
		if(trainer==null || trainer.getIsActive()==null) {
			log.error("Error en trainer save");
			throw new ResouceNotFoundException("Error al guardar" );
		} else {
			itrainerdaoJPA.save(trainer);
			log.info("trainer guardado con exito");
			
		}
		return trainer;
	}
	@Transactional(readOnly = true)
	@Override
	public Trainer findById(Long id) {
		if(itrainerdaoJPA.findById(id) == null || itrainerdaoJPA.findById(id).isEmpty()) {
			log.error("Trainer con id: " + id + "No encontrado");
			throw new ResouceNotFoundException("cliente", "id" ,id);
		}else {
			log.info("Trainer encontrado con id: " + id + " Encontrado " );
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
	    	log.error("error en Trainer update" );
	    	throw new ResouceNotFoundException("Trainer with ID " + id + " no update in the database");
	    }else {
	    	Aactual.setFirstName(trainer.getFirstName());
	    	Aactual.setLastName(trainer.getLastName());
	    	Aactual.setUsername(generateUniqueUsername(trainer.getFirstName(),trainer.getLastName()));
	    	Aactual.setTrainertype(trainer.getTrainertype());
	    	nuevotra =itrainerdaoJPA.save(Aactual);
	    	log.info("Trainer actualizado");
	    	return nuevotra;
	    }
	
	}
	
	    
	
	@PostConstruct
	public void init() {
		log.info("Iniciando el iTrainerServiceImpl");
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Trainer> findAll() {
		List<Trainer> lista = (List<Trainer>) itrainerdaoJPA.findAll();
		if(lista==null || lista.isEmpty()) {
			log.error("Lista de Trainer no encontrada" );
			throw new ResouceNotFoundException("clientes");
		}
		log.info("Lista de Trainer encontrada" );
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
		log.info("Trainer con username unico generado");
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
        log.info("Contraseña Generada de Trainer");
        return sb.toString();
    }
		
	  
	@Transactional
	@Override
	public Trainer findbyusername(String username) {
		Trainer trainer =itrainerdaoJPA.findbyusername(username).orElse(null);
		if (itrainerdaoJPA.findbyusername(username).isPresent()) {
			log.info("Trainer encontrado con el username:" + username );
			return trainer;
			
		}else {
			log.error("Trainer no encontrado con el username:" + username );
			throw new ResouceNotFoundException();
		}
		
	}

	@Transactional
	@Override
	public Boolean loggin(String username, String Password) {
		Trainer traineeE = itrainerdaoJPA.findbyusername(username).orElse(null);
		if(itrainerdaoJPA.findbyusername(username).isPresent()) {
			if(traineeE.getPassword().equals(Password));
			log.info("Trainer iniciado sesion con el username:" + username );
			return true;			
		}
		else {
			log.error("Error con el iniciado de sesion del Trainer con el username:" + username );
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
			log.info("Trainer con el username:" + username + " inicio de sesion actualizado con exito " );
			return trainerE;			
		}
		else {
			log.error("Error conla actualizacion de inicio de sesion del Trainer con el username:" + username );
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
			log.info("Trainer con el username:" + username + " Status cambiado" );
		}else {
			log.error("Trainer con el username:" + username + " no se pudo cambiar el Status" );
			throw new ResouceNotFoundException();
		}
		
		return trainer;
		
	}


}
