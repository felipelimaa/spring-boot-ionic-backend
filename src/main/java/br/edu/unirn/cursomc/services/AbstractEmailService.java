package br.edu.unirn.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.edu.unirn.cursomc.domain.Cliente;
import br.edu.unirn.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	//capturando o valor de default.sender em application.properties
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm); //metodo da Interface EmailService
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) { //metodo pode ser acessado por subclasses
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail()); //destinatário
		sm.setFrom(sender); //remetente
		sm.setSubject("Pedido Confirmado! Nº: " + obj.getId()); //Titulo e-mail
		sm.setSentDate(new Date(System.currentTimeMillis())); //Data envio e-mail
		sm.setText(obj.toString()); //Corpo do e-mail
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm); //metodo da Interface EmailService
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail()); //destinatário
		sm.setFrom(sender); //remetente
		sm.setSubject("Solicitação de nova senha"); //Titulo e-mail
		sm.setSentDate(new Date(System.currentTimeMillis())); //Data envio e-mail
		sm.setText("Sua nova senha para acesso é: " + newPass ); //Corpo do e-mail
		return sm;
	}

}
