package br.com.active.sistemaprotocolos.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.active.sistemaprotocolos.api.entities.Usuario;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "UsuarioRepository.findByNome",
			    query = "SELECT usr FROM Usuario usr WHERE usr.nome LIKE :nome")
})
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findAll();
	
	Usuario findById(Long id);
	
	List<Usuario> findByNome(@Param("nome") String nome);
	
}