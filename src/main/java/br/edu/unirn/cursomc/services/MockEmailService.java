package br.edu.unirn.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class); //STATIC: Unico para toda chamada de LOG que existir
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado!");
	}
	

}
