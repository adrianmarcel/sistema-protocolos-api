package br.com.active.sistemaprotocolos.api.services;

import java.util.List;

import br.com.active.sistemaprotocolos.api.entities.Diretoria;

public interface DiretoriaService {
	
	/**
	 * @author adrianmarcell
	 * Retorna todas as diretorias cadastradas no banco de dados
	 * 
	 * @return List<Diretoria>
	 */
	List<Diretoria> findAll();
	
	/**
	 * @author adrianmarcell
	 * Retorna diretorias na busca pelo nome
	 * 
	 * @return List<Diretoria>
	 */
	List<Diretoria> findByDescricao(String descricao);
	
	/**
	 * @author adrianmarcell
	 * Cadastra uma nova diretoria no banco de dados.
	 * 
	 * @param diretoria
	 * @return Diretoria
	 */
	Diretoria persistir(Diretoria diretoria);
	
	/**
	 * @author adrianmarcell
	 * Apaga uma diretoria no banco de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);

}