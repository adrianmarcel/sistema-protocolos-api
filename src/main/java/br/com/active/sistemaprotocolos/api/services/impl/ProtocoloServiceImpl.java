package br.com.active.sistemaprotocolos.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.active.sistemaprotocolos.api.entities.Protocolo;
import br.com.active.sistemaprotocolos.api.repositories.ProtocoloRepository;
import br.com.active.sistemaprotocolos.api.services.ProtocoloService;

@Service
public class ProtocoloServiceImpl implements ProtocoloService {

	private static final Logger log = LoggerFactory.getLogger(ProtocoloServiceImpl.class);
	
	@Autowired
	private ProtocoloRepository protocoloRepository;
	
	@Override
	public List<Protocolo> findAll() {
		log.info("Buscando todos os protocolos cadastrados na aplicação!");
		return this.protocoloRepository.findAll();
	}

	@Override
	public List<Protocolo> findByUsuarioId(Long usuarioId) {
		log.info("Buscando todos os protocolos relacionados ao usuário com ID {} cadastrados na aplicação!", usuarioId);
		return this.protocoloRepository.findByUsuarioId(usuarioId);
	}

	@Override
	public List<Protocolo> findByDiretoriaId(Long diretoriaId) {
		log.info("Buscando todos os protocolos relacionados a diretoria com ID {} cadastrados na aplicação!", diretoriaId);
		return this.protocoloRepository.findByDiretoriaId(diretoriaId);
	}

	@Override
	public Protocolo persistir(Protocolo protocolo) {
		log.info("Persistindo protocolo: {}", protocolo);
		return this.protocoloRepository.save(protocolo);
	}
}