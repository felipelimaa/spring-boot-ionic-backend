package br.edu.unirn.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unirn.cursomc.domain.ItemPedido;
import br.edu.unirn.cursomc.domain.PagamentoComBoleto;
import br.edu.unirn.cursomc.domain.Pedido;
import br.edu.unirn.cursomc.domain.enums.EstadoPagamento;
import br.edu.unirn.cursomc.repositories.ClienteRepository;
import br.edu.unirn.cursomc.repositories.ItemPedidoRepository;
import br.edu.unirn.cursomc.repositories.PagamentoRepository;
import br.edu.unirn.cursomc.repositories.PedidoRepository;
import br.edu.unirn.cursomc.repositories.ProdutoRepository;
import br.edu.unirn.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo; //instancia da dependencia
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) { //busca de categoria
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + 
					", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null); //garantir inserção de um novo pedido
		obj.setDataPedido(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getDataPedido());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;		
	}
	
	
	
}
