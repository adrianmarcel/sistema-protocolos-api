package br.com.active.sistemaprotocolos.api.services;

import java.util.List;

import br.com.active.sistemaprotocolos.api.entities.Protocolo;

public interface ProtocoloService {
	
	/**
	 * @author adrianmarcell
	 * Retorna todos os protocolos cadastrados na aplicação
	 * 
	 * @return List<Protocolo>
	 */
	List<Protocolo> findAll();
	
	/**
	 * @author adrianmarcell
	 * Retorna os protocolos de um determinado usuário
	 * 
	 * @param usuarioId
	 * @return Optional<Protocolo>
	 */
	List<Protocolo> findByUsuarioId(Long usuarioId);
	
	/**
	 * @author adrianmarcell
	 * Retorna os protocolos de uma determinada diretoria
	 * 
	 * @param diretoriaId
	 * @return Optional<Protocolo>
	 */
	List<Protocolo> findByDiretoriaId(Long diretoriaId);
	
	/**
	 * @author adrianmarcell
	 * Cadastra um novo protocolo no banco de dados
	 * 
	 * @param protocolo
	 * @return Protocolo
	 */
	Protocolo persistir(Protocolo protocolo);

}
