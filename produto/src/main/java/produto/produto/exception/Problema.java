package produto.produto.exception;

import java.time.LocalDateTime;
import java.util.List;

public class Problema {
	
	private int status;
	private String title;
	private LocalDateTime dataHora;
	
	private List<Campos> inputs;
	
	public static class Campos {
		private String nome;
		private String message;
		
		
		
		public Campos(String nome, String message) {
			super();
			this.nome = nome;
			this.message = message;
		}
		
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public List<Campos> getInputs() {
		return inputs;
	}

	public void setInputs(List<Campos> inputs) {
		this.inputs = inputs;
	}
	
	
	
	

}
