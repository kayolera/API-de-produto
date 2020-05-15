package produto.produto.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
			var campos = new ArrayList<Problema.Campos>();
			
			for(ObjectError er : ex.getBindingResult().getAllErrors()) {
				String nome = ((FieldError)er).getField();
				String mes = er.getDefaultMessage();
				
				campos.add(new Problema.Campos(nome, mes));
			}
			
			var problema  = new Problema();
			problema.setStatus(status.value());
			problema.setTitle("Um ou mais campos não estão preenchidos ");
			problema.setDataHora(LocalDateTime.now());
			problema.setInputs(campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

}
