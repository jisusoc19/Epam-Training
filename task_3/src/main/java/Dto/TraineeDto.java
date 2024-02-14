package Dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@Data
public class TraineeDto implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String addres;
	final String firstNam;
	final String lastName;
	
	

}
