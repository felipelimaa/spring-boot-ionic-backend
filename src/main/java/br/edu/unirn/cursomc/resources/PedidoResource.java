package br.edu.unirn.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unirn.cursomc.domain.Pedido;
import br.edu.unirn.cursomc.services.PedidoService;

@RestController // controlador REST
@RequestMapping("/pedidos") // endpoint de resposta
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody /* Converte o JSON em objeto Java */ Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		// coleta a URI do novo recurso inserido
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataPedido") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction
			) {
		Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
