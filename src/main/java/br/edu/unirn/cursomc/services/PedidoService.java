package br.edu.unirn.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unirn.cursomc.domain.Pedido;
import br.edu.unirn.cursomc.repositories.PedidoRepository;
import br.edu.unirn.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo; //instancia da dependencia
	
	public Pedido find(Integer id) { //busca de categoria
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	
	
	
}
