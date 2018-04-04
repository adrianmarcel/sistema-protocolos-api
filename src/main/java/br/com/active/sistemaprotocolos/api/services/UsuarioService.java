package br.com.active.sistemaprotocolos.api.services;

import java.util.List;
import java.util.Optional;

import br.com.active.sistemaprotocolos.api.entities.Usuario;

public interface UsuarioService {
	
	/**
	 * @author adrianmarcell
	 * Retorna todos os usuários cadastrados no banco de Dados.
	 * 
	 * @param id
	 * @return Optional<Usuario>
	 */
	List<Usuario> findAll();
	
	/**
	 * @author adrianmarcell
	 * Retorna determinado usuário numa busca pelo ID.
	 * 
	 * @param id
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> findById(Long id);
	
	/**
	 * @author adrianmarcell
	 * Retorna determinado usuário numa busca pelo nome.
	 * 
	 * @param descricao
	 * @return List<Usuario>
	 */
	List<Usuario> findByNome(String descricao);
	
	/**
	 * @author adrianmarcell
	 * Cadastra um novo usuário no banco de dados.
	 * 
	 * @param usuario
	 * @return Usuario
	 */
	Usuario persistir(Usuario usuario);
	
	/**
	 * @author adrianmarcell
	 * Apaga um usuario no banco de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);
}