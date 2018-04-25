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

import br.com.active.sistemaprotocolos.api.dtos.CadastroUsuarioDTO;
import br.com.active.sistemaprotocolos.api.entities.Diretoria;
import br.com.active.sistemaprotocolos.api.entities.Usuario;
import br.com.active.sistemaprotocolos.api.enums.PerfilEnum;
import br.com.active.sistemaprotocolos.api.response.Response;
import br.com.active.sistemaprotocolos.api.services.DiretoriaService;
import br.com.active.sistemaprotocolos.api.services.UsuarioService;
import br.com.active.sistemaprotocolos.api.utils.PasswordUtils;

@RestController
@RequestMapping("api/cadastrar-usuario")
@CrossOrigin(origins = "*")
public class CadastroUsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(CadastroUsuarioController.class);
	
	@Autowired
	private DiretoriaService diretoriaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public CadastroUsuarioController() {
		
	}
	
	/*
	   Cadastra um novo usuario no sistema
	 * 
	 * @author adrianmarcel
	 * 
	 * @param cadastroUsuarioDTO
	 * @param result
	 * @return ResponseEntity<Response<CadastroUsuarioDTO>>
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroUsuarioDTO>> cadastrar(@Valid @RequestBody CadastroUsuarioDTO cadastroUsuarioDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando usuario: {}", cadastroUsuarioDTO.toString());
		
		Response<CadastroUsuarioDTO> response = new Response<CadastroUsuarioDTO>();
		
		Diretoria diretoria = this.validarDadosDiretoria(cadastroUsuarioDTO, result);
		
		Usuario usuario = this.converterDTOparaUsuario(cadastroUsuarioDTO, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar dados de cadastro de usuario: {}", result.getAllErrors());
			
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.usuarioService.persistir(usuario);
		
		response.setData(this.converterCadastroUsuario(usuario, diretoria));
		
		return ResponseEntity.ok(response);
	}
	
	/*
	   Valida se os dados da diretoria já existem no banco de dados
	 * 
	 * @author adrianmarcel
	 * @param cadastroUsuarioDTO
	 * @param result
	 * @return Diretoria
	 * @throws NoSuchAlgorithmException
	 */
	private Diretoria validarDadosDiretoria(CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult result) throws NoSuchAlgorithmException {
		Optional<Diretoria> diretoria = this.diretoriaService.findById(cadastroUsuarioDTO.getDiretoriaId());
		
		if (!diretoria.isPresent()) {
			result.addError(new ObjectError("diretoria", "Diretoria não existe no sistema"));
			throw new NoSuchAlgorithmException("Diretoria não existe no sistema");
		}
		
		return diretoria.get();
	}
	
	/*
	   Converte os dados do DTO para um usuario
	 * 
	 * @author adrianmarcel
	 * @param cadastroUsuarioDTO
	 * @param result
	 * @return Usuario
	 * @throws NoSuchAlgorithmException
	 */
	private Usuario converterDTOparaUsuario(CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		
		Usuario usuario = new Usuario();
		usuario.setDiretoriaId(cadastroUsuarioDTO.getDiretoriaId());
		usuario.setEmail(cadastroUsuarioDTO.getEmail());
		usuario.setLogin(cadastroUsuarioDTO.getLogin());
		usuario.setSenha(PasswordUtils.gerarBCrypt(cadastroUsuarioDTO.getSenha()));
		usuario.setNome(cadastroUsuarioDTO.getNome());
		usuario.setPerfil(cadastroUsuarioDTO.getPerfil().equalsIgnoreCase("ADMING") ? PerfilEnum.ROLE_ADMIN_GERAL :
			              cadastroUsuarioDTO.getPerfil().equalsIgnoreCase("ADMIN") ? PerfilEnum.ROLE_ADMIN :
			              PerfilEnum.ROLE_USER);
		
		return usuario;
	}
	
	/*
	   Popular o DTO com os dados cadastrados
	 * 
	 * @author adrianmarcel
	 * @param cadastroUsuarioDTO
	 * @return Usuario
	 */
	private CadastroUsuarioDTO converterCadastroUsuario(Usuario usuario, Diretoria diretoria) {
		
		CadastroUsuarioDTO cadastroUsuarioDTO = new CadastroUsuarioDTO();
		cadastroUsuarioDTO.setId(usuario.getId());
		cadastroUsuarioDTO.setNome(usuario.getNome());
		cadastroUsuarioDTO.setDiretoriaId(diretoria.getId());
		cadastroUsuarioDTO.setLogin(usuario.getLogin());
		cadastroUsuarioDTO.setEmail(usuario.getEmail());
		cadastroUsuarioDTO.setSenha(usuario.getSenha());
		cadastroUsuarioDTO.setPerfil(usuario.getPerfil().toString());
		
		return cadastroUsuarioDTO;
	}
}