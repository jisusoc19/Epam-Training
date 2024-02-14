package com.task3.models.service.trainee;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import com.task3.Task3Application;
import com.task3.models.Repository.iTraineedao;
import com.task3.models.entity.Trainee;
import com.task3.models.entity.Trainer;
import com.task3.models.entity.User;

import RestHandlerException.ResouceNotFoundException;
import jakarta.annotation.PostConstruct;

@Service
public class iTraineServiceImpl implements iTraineeService {
	private static final Logger logger = LoggerFactory.getLogger(Task3Application.class);

	@Autowired
	private iTraineedao trainerdaoJpa;

	@Transactional(readOnly = true)
	@Override
	public List<Trainee> findAll() {
		List<Trainee> lista = (List<Trainee>) trainerdaoJpa.findAll();
		if(lista==null || lista.isEmpty()) {
			throw new ResouceNotFoundException("clientes");
		}
		logger.info("Lista de Trainee Ejecutada");
		return (List<Trainee>) trainerdaoJpa.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Trainee findById(Long id) {
		if (trainerdaoJpa.findById(id) == null || trainerdaoJpa.findById(id).isEmpty() ) {
			logger.error("No se encontró el trainee con el id: " + id);
			throw new ResouceNotFoundException("cliente", "id" ,id);
		} else {
			logger.info("Trainee con el id: " + id + " encontrado con éxito.");
			return trainerdaoJpa.findById(id).orElse(null);
		}
	}

	@Transactional
	@Override
	public Trainee save(Trainee trainee) {
		if(trainee==null ) {
			logger.error("Error en guardar" );
			throw new NullPointerException("Error al guardar" );
		} else {
			trainee.setUsername(generateUniqueUsername(trainee.getFirstName(),trainee.getLastName()));
			trainee.setPasword(generatePasword(trainee.getPasword()));
			trainerdaoJpa.save(trainee);
			logger.info("Trainee guardado con exito");
			return trainee;
		}
		
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Long id) {
		if(trainerdaoJpa.findById(id) == null || trainerdaoJpa.findById(id).isEmpty()){
			throw new ResouceNotFoundException("Error al borrar");
		}	
		else{
			trainerdaoJpa.deleteById(id);
			logger.info("trainee con el id :" + id + " eliminado con exito");
			
		}
	}

	@PostConstruct
	public void init() {
		logger.info("iTraineServiceImpl ha sido inicializado");
	}

	@Transactional
	@Override
	public Trainee update(Trainee trainee, Long id) {
		Trainee Aactual = trainerdaoJpa.findById(id).orElse(null);
		Trainee nuevotra = trainee;
		if (!trainerdaoJpa.findById(id).isPresent()) {
			// Update the trainee
			throw new ResouceNotFoundException("Trainer with ID " + id + " no update in the database");
		}else {
			Aactual.setDateBirth(trainee.getDateBirth());
			Aactual.setIsActive(trainee.getIsActive());
			Aactual.setUsername(generateUniqueUsername(trainee.getFirstName(),trainee.getLastName()));
			Aactual.setAddres(trainee.getAddres());
			logger.info("Trainer actualizado");
			nuevotra =trainerdaoJpa.save(Aactual);
			return nuevotra;
		}
	
	}
	@Transactional
	@Override
	public String generateUniqueUsername(String firstName, String lastName) {
		String gUsername = firstName.toLowerCase() + "." + lastName.toLowerCase();
		
		while (trainerdaoJpa.findbyusername(gUsername).isPresent()) {
			Integer n = 1;
			gUsername = gUsername + n;
			n++;
		}
		return gUsername;
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
	public Trainee findbyusername(String username) {
		Trainee trainer =trainerdaoJpa.findbyusername(username).orElse(null);
		if (trainerdaoJpa.findbyusername(username).isPresent()) {
			return trainer;
			
		}else {
			throw new ResouceNotFoundException();
		}
	}

	@Transactional
	@Override
	public Boolean loggin(String username, String Password) {
		Trainee traineeE = trainerdaoJpa.findbyusername(username).orElse(null);
		if(trainerdaoJpa.findbyusername(username).isPresent()) {
			if(traineeE.getPasword().equals(Password));
			return true;			
		}
		else {
			return false;
		}
		
	}
	@Transactional
	@Override
	public Trainee logginUpdate(String username, String PasswordNew, String Passwordold) {
		Trainee traineeE = trainerdaoJpa.findbyusername(username).orElse(null);
		if(traineeE.getUsername().equals(username)) {
			if(traineeE.getPasword().equals(Passwordold));
			traineeE.setPasword(PasswordNew);
			trainerdaoJpa.save(traineeE);
			return traineeE;			
		}
		else {
			return null;
		}
	}
	@Transactional
	@Override
	public void deleteByusername(String username) {
		if(trainerdaoJpa.findbyusername(username).isPresent()) {
			trainerdaoJpa.deleteByUsername(username);
		}else {
			throw new ResouceNotFoundException();
		}
		
	}
	
	@Transactional
	@Override
	public Trainee updateStatususername(String username, Boolean status) {
		Trainee traineeA = trainerdaoJpa.findbyusername(username).orElse(null);
		traineeA.setIsActive(status);
		trainerdaoJpa.save(traineeA);
		return traineeA;
	}

}
