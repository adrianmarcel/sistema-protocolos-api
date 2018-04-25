package br.com.active.sistemaprotocolos.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.active.sistemaprotocolos.api.entities.Diretoria;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "DiretoriaRepository.findByDescricao",
			    query = "SELECT dir FROM Diretoria dir WHERE dir.descricao LIKE ?1")
})
public interface DiretoriaRepository extends JpaRepository<Diretoria, Long>{
	
	List<Diretoria> findAll();
	
	Diretoria findById(Long id);
	
	List<Diretoria> findByDescricao(@Param("descricao") String descricao);

}