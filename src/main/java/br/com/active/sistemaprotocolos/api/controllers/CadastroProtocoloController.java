package br.com.active.sistemaprotocolos.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.active.sistemaprotocolos.api.dtos.CadastroProtocoloDTO;
import br.com.active.sistemaprotocolos.api.entities.Diretoria;
import br.com.active.sistemaprotocolos.api.entities.Protocolo;
import br.com.active.sistemaprotocolos.api.entities.Usuario;
import br.com.active.sistemaprotocolos.api.response.Response;
import br.com.active.sistemaprotocolos.api.services.DiretoriaService;
import br.com.active.sistemaprotocolos.api.services.ProtocoloService;
import br.com.active.sistemaprotocolos.api.services.UsuarioService;

@RestController
@RequestMapping("api/cadastrar-protocolo")
@CrossOrigin(origins = "*")
public class CadastroProtocoloController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroProtocoloController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private DiretoriaService diretoriaService;
	
	@Autowired
	private ProtocoloService protocoloService;
	
	public CadastroProtocoloController() {
		
	}
	
	/*
	   Cadastra um novo protocolo no sistema
	 * 
	 * @author adrianmarcel
	 * 
	 * @param cadastroProtocoloDTO
	 * @param result
	 * @return ResponseEntity<Response<CadastroProtocoloDTO>>
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroProtocoloDTO>> cadastrar(@Valid @RequestBody CadastroProtocoloDTO cadastroProtocoloDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando protocolo: {}", cadastroProtocoloDTO.toString());
		
		Response<CadastroProtocoloDTO> response = new Response<CadastroProtocoloDTO>();
		
		Usuario usuario = this.validarDadosUsuario(cadastroProtocoloDTO, result);
		Diretoria diretoria = this.validarDadosDiretoria(cadastroProtocoloDTO, result);
		
		Protocolo protocolo = this.converterDTOparaProtocolo(cadastroProtocoloDTO, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar dados de cadastro de protocolo: {}", result.getAllErrors());
			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.protocoloService.persistir(protocolo);
		
		response.setData(this.converterCadastroProtocolo(protocolo, usuario, diretoria));
		
		return ResponseEntity.ok(response);
	}
	
	/*
	   Valida se os dados do usuário já existem no banco de dados
	 * 
	 * @author adrianmarcel
	 * @param cadastroProtocoloDTO
	 * @param result
	 * @return Usuario
	 * @throws NoSuchAlgorithmException
	 */
	private Usuario validarDadosUsuario(CadastroProtocoloDTO cadastroProtocoloDTO, BindingResult result) throws NoSuchAlgorithmException {
		Optional<Usuario> usuario = this.usuarioService.findById(cadastroProtocoloDTO.getUsuarioId());
		
		if (!usuario.isPresent()) {
			result.addError(new ObjectError("usuario", "Usuário não existe no sistema"));
			throw new NoSuchAlgorithmException("Usuário não existe no sistema");
		}
		
		return usuario.get();
	}
	
	/*
	   Valida se os dados da diretoria já existem no banco de dados
	 * 
	 * @author adrianmarcel
	 * @param cadastroProtocoloDTO
	 * @param result
	 * @return Diretoria
	 * @throws NoSuchAlgorithmException
	 */
	private Diretoria validarDadosDiretoria(CadastroProtocoloDTO cadastroProtocoloDTO, BindingResult result) throws NoSuchAlgorithmException {
		Optional<Diretoria> diretoria = this.diretoriaService.findById(cadastroProtocoloDTO.getDiretoriaId());
		
		if (!diretoria.isPresent()) {
			result.addError(new ObjectError("diretoria", "Diretoria não existe no sistema"));
			throw new NoSuchAlgorithmException("Diretoria não existe no sistema");
		}
		
		return diretoria.get();
	}
	
	/*
	   Converte os dados do DTO para um protocolo
	 * 
	 * @author adrianmarcel
	 * @param cadastroProtocoloDTO
	 * @param result
	 * @return Protocolo
	 * @throws NoSuchAlgorithmException
	 */
	private Protocolo converterDTOparaProtocolo(CadastroProtocoloDTO cadastroProtocoloDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		
		Protocolo protocolo = new Protocolo();
		protocolo.setDescricao(cadastroProtocoloDTO.getDescricao());
		protocolo.setDiretoriaId(cadastroProtocoloDTO.getDiretoriaId());
		protocolo.setEmail(cadastroProtocoloDTO.getEmail());
		protocolo.setUsuarioId(cadastroProtocoloDTO.getUsuarioId());
		
		return protocolo;
	}
	
	/*
	   Popular o DTO com os dados cadastrados
	 * 
	 * @author adrianmarcel
	 * @param cadastroProtocoloDTO
	 * @return Protocolo
	 */
	private CadastroProtocoloDTO converterCadastroProtocolo(Protocolo protocolo, Usuario usuario, Diretoria diretoria) {
		
		CadastroProtocoloDTO cadastroProtocoloDTO = new CadastroProtocoloDTO();
		cadastroProtocoloDTO.setId(protocolo.getId());
		cadastroProtocoloDTO.setDescricao(protocolo.getDescricao());
		cadastroProtocoloDTO.setDiretoriaId(diretoria.getId());
		cadastroProtocoloDTO.setEmail(protocolo.getEmail());
		cadastroProtocoloDTO.setUsuarioId(usuario.getId());
		
		return cadastroProtocoloDTO;
	}

}