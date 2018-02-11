package br.edu.unirn.cursomc;

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
import br.edu.unirn.cursomc.domain.Produto;
import br.edu.unirn.cursomc.domain.enums.TipoCliente;
import br.edu.unirn.cursomc.repositories.CategoriaRepository;
import br.edu.unirn.cursomc.repositories.CidadeRepository;
import br.edu.unirn.cursomc.repositories.ClienteRepository;
import br.edu.unirn.cursomc.repositories.EnderecoRepository;
import br.edu.unirn.cursomc.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// categorias e produtos
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 75.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));

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
		Cliente cli1 = new Cliente(null, "Felipe Lima", "fearlimasi@gmail.com", "11056005483", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("999348804", "32201543"));
		Endereco e1 = new Endereco(null, "Rua Escritor Oswald de Andrade", "51", "", "Pitimbu", "59069350", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Antônio Basílio", "3598", "Setor ATI", "Lagoa Nova", "59056380", cli1, c1);

		Cliente cli2 = new Cliente(null, "Unimed Natal", "ati@unimednatal.com.br", "08380701000105", TipoCliente.PESSOAJURIDICA);
		cli2.getTelefones().addAll(Arrays.asList("988263200", "32201500"));
		Endereco e3 = new Endereco(null, "Avenida Antônio Basílio", "3598", "Setor ATI", "Lagoa Nova", "59056380", cli2, c1);

		Cliente cli3 = new Cliente(null, "Marina Dantas", "mariinadantas@gmail.com", "11122233344",	TipoCliente.PESSOAFISICA);
		cli3.getTelefones().addAll(Arrays.asList("999656844", "32187639"));
		Endereco e4 = new Endereco(null, "Rua Deputado Marcílio Furtado", "570", "", "Pitimbu", "59069470", cli3, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		cli3.getEnderecos().addAll(Arrays.asList(e4));
		
		clienteRepository.save(Arrays.asList(cli1,cli2,cli3));
		enderecoRepository.save(Arrays.asList(e1,e2,e3,e4));
		
	}

}
