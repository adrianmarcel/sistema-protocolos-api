package br.com.active.sistemaprotocolos.api.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.active.sistemaprotocolos.api.entities.Protocolo;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "ProtocoloRepository.findByUsuarioId",
			    query = "SELECT proc FROM Protocolo proc WHERE proc.usuario.id = :usuarioId"),
	@NamedQuery(name = "ProtocoloRepository.findByDiretoriaId",
    			query = "SELECT proc FROM Protocolo proc WHERE proc.diretoria.id = :diretoriaId")
})
public interface ProtocoloRepository extends JpaRepository<Protocolo, Long>{
	
	List<Protocolo> findByUsuarioId(@Param("usuarioId") Long usuarioId);
	
	Page<Protocolo> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);
	
	List<Protocolo> findByDiretoriaId(@Param("diretoriaId") Long diretoriaId);
	
	Page<Protocolo> findByDiretoriaId(@Param("diretoriaId") Long diretoriaId, Pageable pageable);
	
}