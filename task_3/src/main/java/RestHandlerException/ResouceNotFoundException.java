package RestHandlerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Builder
@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private String resourcename;
	private String fieldname;
	private Object value;
	
	public ResouceNotFoundException(String resourcename, String fieldname, Object value) {
		super(String.format("%s no fue encontrado con %s='%s'",resourcename,fieldname,value ));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.value = value;
	}
	public ResouceNotFoundException(String resourcename) {
		super(String.format("no hay resgistros en el sistema '%s'",resourcename));
		this.resourcename = resourcename;

	}
	

}
