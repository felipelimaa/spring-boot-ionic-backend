package br.edu.unirn.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unirn.cursomc.domain.Categoria;
import br.edu.unirn.cursomc.repositories.CategoriaRepository;
import br.edu.unirn.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo; //instancia da dependencia
	
	public Categoria buscar(Integer id) { //busca de categoria
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //checagem se o ID está nulo, se não estiver nulo o metodo save considera como atualização
		return repo.save(obj);
	}
	
}
