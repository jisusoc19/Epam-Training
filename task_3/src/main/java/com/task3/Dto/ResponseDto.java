package com.task3.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseDto {
	private int numoferrors;
	private String message;
	
}
