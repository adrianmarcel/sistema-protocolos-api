package br.com.active.sistemaprotocolos.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.active.sistemaprotocolos.api.entities.Diretoria;
import br.com.active.sistemaprotocolos.api.repositories.DiretoriaRepository;
import br.com.active.sistemaprotocolos.api.services.DiretoriaService;

@Service
public class DiretoriaServiceImpl implements DiretoriaService {

	private static final Logger log = LoggerFactory.getLogger(DiretoriaServiceImpl.class);
	
	@Autowired
	private DiretoriaRepository diretoriaRepository;
	
	@Override
	public List<Diretoria> findAll() {
		log.info("Buscando todas as diretorias cadastradas na aplicação!");
		return this.diretoriaRepository.findAll();
	}
	
	@Override
	public Optional<Diretoria> findById(Long id) {
		log.info("Buscando um determinado diretoria pelo ID: {} na aplicação!", id);
		return Optional.ofNullable(this.diretoriaRepository.findById(id));
	}
	
	@Override
	public List<Diretoria> findByDescricao(String descricao) {
		log.info("Buscando diretorias pelo nome {} cadastradas na aplicação!", descricao);
		return this.diretoriaRepository.findByDescricao(descricao);
	}
	
	@Override
	public Diretoria persistir(Diretoria diretoria) {
		log.info("Persistindo diretoria: {}", diretoria);
		return this.diretoriaRepository.save(diretoria);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo diretoria com ID: {}", id);
		this.diretoriaRepository.delete(id);
	}
}