package br.edu.unirn.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.edu.unirn.cursomc.domain.Cidade;
import br.edu.unirn.cursomc.domain.Cliente;
import br.edu.unirn.cursomc.domain.Endereco;
import br.edu.unirn.cursomc.domain.enums.TipoCliente;
import br.edu.unirn.cursomc.dto.ClienteDTO;
import br.edu.unirn.cursomc.dto.ClienteNewDTO;
import br.edu.unirn.cursomc.repositories.CidadeRepository;
import br.edu.unirn.cursomc.repositories.ClienteRepository;
import br.edu.unirn.cursomc.repositories.EnderecoRepository;
import br.edu.unirn.cursomc.services.exceptions.DataIntegrityException;
import br.edu.unirn.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo; // instancia da dependencia
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) { // busca de categoria
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null); // checagem se o ID está nulo, se não estiver nulo o metodo save considera como
							// atualização
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); // busca e verifica se existe
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possua pedidos");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	// Paginação de dados
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
