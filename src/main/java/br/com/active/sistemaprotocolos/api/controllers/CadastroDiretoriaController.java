package br.com.active.sistemaprotocolos.api.controllers;

import java.security.NoSuchAlgorithmException;

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

import br.com.active.sistemaprotocolos.api.dtos.CadastroDiretoriaDTO;
import br.com.active.sistemaprotocolos.api.entities.Diretoria;
import br.com.active.sistemaprotocolos.api.response.Response;
import br.com.active.sistemaprotocolos.api.services.DiretoriaService;

@RestController
@RequestMapping("api/cadastrar-diretoria")
@CrossOrigin(origins = "*")
public class CadastroDiretoriaController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroDiretoriaController.class);
	
	@Autowired
	private DiretoriaService diretoriaService;
	
	public CadastroDiretoriaController() {
		
	}
	
	/*
	   Cadastra uma nova diretoria no sistema
	 * 
	 * @author adrianmarcel
	 * 
	 * @param cadastroDiretoriaDTO
	 * @param result
	 * @return ResponseEntity<Response<CadastroDiretoriaDTO>>
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroDiretoriaDTO>> cadastrar(@Valid @RequestBody CadastroDiretoriaDTO cadastroDiretoriaDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando diretoria:  {}", cadastroDiretoriaDTO.toString());
		
		Response<CadastroDiretoriaDTO> response = new Response<CadastroDiretoriaDTO>();
		
		Diretoria diretoria = this.converterDTOparaDiretoria(cadastroDiretoriaDTO, result);
		
		try {
			this.diretoriaService.persistir(diretoria);
		} catch (Exception e) {
			result.addError(new ObjectError("diretoria", e.getMessage()));
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterCadastroDiretoria(diretoria));
		
		return ResponseEntity.ok(response);
	}
	
	/*
	   Converte os dados do DTO para uma diretoria
	 * 
	 * @author adrianmarcel
	 * @param cadastroDiretoriaDTO
	 * @param result
	 * @return Diretoria
	 * @throws NoSuchAlgorithmException
	 */
	private Diretoria converterDTOparaDiretoria(CadastroDiretoriaDTO cadastroDiretoriaDTO, BindingResult result)
			throws NoSuchAlgorithmException {

		Diretoria diretoria = new Diretoria();
		diretoria.setDescricao(cadastroDiretoriaDTO.getNome());
		diretoria.setEmail(cadastroDiretoriaDTO.getEmail());
		diretoria.setSigla(cadastroDiretoriaDTO.getSigla());
		diretoria.setTelefone(cadastroDiretoriaDTO.getTelefone());
		
		return diretoria;
	}
	
	/*
	   Popular o DTO com os dados cadastrados
	 * 
	 * @author adrianmarcel
	 * @param cadastroDiretoriaDTO
	 * @return Diretoria
	 */
	private CadastroDiretoriaDTO converterCadastroDiretoria(Diretoria diretoria) {
		
		CadastroDiretoriaDTO cadastroDiretoriaDTO = new CadastroDiretoriaDTO();
		cadastroDiretoriaDTO.setId(diretoria.getId());
		cadastroDiretoriaDTO.setNome(diretoria.getDescricao());
		cadastroDiretoriaDTO.setEmail(diretoria.getEmail());
		cadastroDiretoriaDTO.setSigla(diretoria.getSigla());
		cadastroDiretoriaDTO.setTelefone(diretoria.getTelefone());
		
		return cadastroDiretoriaDTO;
	}
}