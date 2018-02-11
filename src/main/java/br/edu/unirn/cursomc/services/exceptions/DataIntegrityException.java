package br.edu.unirn.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg); //chamando superclasse RuntimeException
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg,cause);
	}
	

}
