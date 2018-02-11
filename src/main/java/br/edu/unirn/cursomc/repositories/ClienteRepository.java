package br.edu.unirn.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unirn.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
		
	@Transactional(readOnly=true) //não necessita ser envolvida em uma transação no banco de dados
	Cliente findByEmail(String email);
	
	@Transactional(readOnly=true) //não necessita ser envolvida em uma transação no banco de dados
	Cliente findByCpfOuCnpj(String cpfOuCnpj);
	
}
