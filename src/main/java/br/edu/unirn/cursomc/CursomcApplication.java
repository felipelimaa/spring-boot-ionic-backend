package br.edu.unirn.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.unirn.cursomc.domain.Categoria;
import br.edu.unirn.cursomc.domain.Cidade;
import br.edu.unirn.cursomc.domain.Cliente;
import br.edu.unirn.cursomc.domain.Endereco;
import br.edu.unirn.cursomc.domain.Estado;
import br.edu.unirn.cursomc.domain.ItemPedido;
import br.edu.unirn.cursomc.domain.Pagamento;
import br.edu.unirn.cursomc.domain.PagamentoComBoleto;
import br.edu.unirn.cursomc.domain.PagamentoComCartao;
import br.edu.unirn.cursomc.domain.Pedido;
import br.edu.unirn.cursomc.domain.Produto;
import br.edu.unirn.cursomc.domain.enums.EstadoPagamento;
import br.edu.unirn.cursomc.domain.enums.TipoCliente;
import br.edu.unirn.cursomc.repositories.CategoriaRepository;
import br.edu.unirn.cursomc.repositories.CidadeRepository;
import br.edu.unirn.cursomc.repositories.ClienteRepository;
import br.edu.unirn.cursomc.repositories.EnderecoRepository;
import br.edu.unirn.cursomc.repositories.EstadoRepository;
import br.edu.unirn.cursomc.repositories.ItemPedidoRepository;
import br.edu.unirn.cursomc.repositories.PagamentoRepository;
import br.edu.unirn.cursomc.repositories.PedidoRepository;
import br.edu.unirn.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// categorias e produtos
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Casa, mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		// estados e cidades
		Estado est1 = new Estado(null, "Rio Grande do Norte", "RN");
		Estado est2 = new Estado(null, "Paraíba", "PB");
		Estado est3 = new Estado(null, "Pernambuco", "PE");

		Cidade c1 = new Cidade(null, "Natal", est1);
		Cidade c2 = new Cidade(null, "Mossoró", est1);
		Cidade c3 = new Cidade(null, "João Pessoa", est2);
		Cidade c4 = new Cidade(null, "Recife", est3);
		Cidade c5 = new Cidade(null, "Campina Grande", est2);

		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3, c5));
		est3.getCidades().addAll(Arrays.asList(c4));

		estadoRepository.save(Arrays.asList(est1, est2, est3));
		cidadeRepository.save(Arrays.asList(c1, c2, c3, c4, c5));

		// cliente, endereço e telefone
		Cliente cli1 = new Cliente(null, "Felipe Lima", "fearlimasi@gmail.com", "11056005483",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("999348804", "32201543"));
		Endereco e1 = new Endereco(null, "Rua Escritor Oswald de Andrade", "51", "", "Pitimbu", "59069350", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Antônio Basílio", "3598", "Setor ATI", "Lagoa Nova", "59056380", cli1,
				c1);

		Cliente cli2 = new Cliente(null, "Unimed Natal", "ati@unimednatal.com.br", "08380701000105",
				TipoCliente.PESSOAJURIDICA);
		cli2.getTelefones().addAll(Arrays.asList("988263200", "32201500"));
		Endereco e3 = new Endereco(null, "Avenida Antônio Basílio", "3598", "Setor ATI", "Lagoa Nova", "59056380", cli2,
				c1);

		Cliente cli3 = new Cliente(null, "Marina Dantas", "mariinadantas@gmail.com", "11122233344",
				TipoCliente.PESSOAFISICA);
		cli3.getTelefones().addAll(Arrays.asList("999656844", "32187639"));
		Endereco e4 = new Endereco(null, "Rua Deputado Marcílio Furtado", "570", "", "Pitimbu", "59069470", cli3, c1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		cli3.getEnderecos().addAll(Arrays.asList(e4));

		clienteRepository.save(Arrays.asList(cli1, cli2, cli3));
		enderecoRepository.save(Arrays.asList(e1, e2, e3, e4));

		// pedidos e pagamentos

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));

		// itens de pedido

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 75.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));

	}

}
