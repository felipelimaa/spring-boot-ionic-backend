package br.edu.unirn.cursomc.services.exceptions;

public class FileException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public FileException(String msg) {
		super(msg); //chamando superclasse RuntimeException
	}
	
	public FileException(String msg, Throwable cause) {
		super(msg,cause);
	}
	

}
