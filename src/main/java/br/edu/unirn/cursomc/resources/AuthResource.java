package br.edu.unirn.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unirn.cursomc.dto.EmailDTO;
import br.edu.unirn.cursomc.security.JWTUtil;
import br.edu.unirn.cursomc.security.UserSS;
import br.edu.unirn.cursomc.services.AuthService;
import br.edu.unirn.cursomc.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	//endpoint protegido, só acessa se tiver autenticação
	@RequestMapping(value="/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		//collect user logado
		UserSS user = UserService.authenticated();
		//gera um novo token para esse usuario
		String token = jwtUtil.generateToken(user.getUsername());
		//add token no cabeçalho da requisição
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto){
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}

}
