package br.edu.unirn.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.edu.unirn.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
