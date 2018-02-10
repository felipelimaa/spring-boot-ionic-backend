package br.edu.unirn.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg); //chamando superclasse RuntimeException
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	

}
