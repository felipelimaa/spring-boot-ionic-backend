package br.edu.unirn.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.edu.unirn.cursomc.services.DBService;
import br.edu.unirn.cursomc.services.EmailService;
import br.edu.unirn.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean //sempre que houver uma anotação Bean estará disponível como componente do sistema (injeção de dependência)
	public EmailService emailService() {
		return new MockEmailService();
	}
	

}
