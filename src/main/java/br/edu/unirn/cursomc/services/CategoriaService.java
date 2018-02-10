package br.edu.unirn.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unirn.cursomc.domain.Categoria;
import br.edu.unirn.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo; //instancia da dependencia
	
	public Categoria buscar(Integer id) { //busca de categoria
		Categoria obj = repo.findOne(id);
		return obj;
	}
	
}
