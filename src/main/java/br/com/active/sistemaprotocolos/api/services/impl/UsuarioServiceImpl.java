package br.com.active.sistemaprotocolos.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.active.sistemaprotocolos.api.entities.Usuario;
import br.com.active.sistemaprotocolos.api.repositories.UsuarioRepository;
import br.com.active.sistemaprotocolos.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public List<Usuario> findAll() {
		log.info("Buscando todas os usuários cadastrados na aplicação!");
		return this.usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		log.info("Buscando um determinado usuário pelo ID: {} na aplicação!", id);
		return Optional.ofNullable(this.usuarioRepository.findById(id));
	}

	@Override
	public List<Usuario> findByNome(String descricao) {
		log.info("Buscando um determinado usuário pelo nome: {} na aplicação!", descricao);
		return this.usuarioRepository.findByNome(descricao);
	}

	@Override
	public Usuario persistir(Usuario usuario) {
		log.info("Persistindo usuário: {}", usuario);
		return this.usuarioRepository.save(usuario);
	}
	
	@Override
	public void remover(Long id) {
		log.info("Removendo usuario com ID: {}", id);
		this.usuarioRepository.delete(id);
	}
}
