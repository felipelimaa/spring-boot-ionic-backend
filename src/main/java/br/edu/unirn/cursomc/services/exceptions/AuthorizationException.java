package br.edu.unirn.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg) {
		super(msg); //chamando superclasse RuntimeException
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg,cause);
	}
	

}
