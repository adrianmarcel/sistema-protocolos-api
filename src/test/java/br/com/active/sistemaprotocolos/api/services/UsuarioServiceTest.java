package br.com.active.sistemaprotocolos.api.services;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.active.sistemaprotocolos.api.entities.Diretoria;
import br.com.active.sistemaprotocolos.api.entities.Usuario;
import br.com.active.sistemaprotocolos.api.enums.PerfilEnum;
import br.com.active.sistemaprotocolos.api.repositories.DiretoriaRepository;
import br.com.active.sistemaprotocolos.api.repositories.UsuarioRepository;
import br.com.active.sistemaprotocolos.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DiretoriaRepository diretoriaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Before
	public void setUp() throws Exception {
		Diretoria diretoria = new Diretoria();
		diretoria.setDescricao("Gabinete do Prefeito Teste");
		diretoria.setSigla("GAB");
		diretoria.setTelefone("(15) 3271-7751");
		diretoria.setEmail("gabineteteste@alambari.sp.gov.br");
		diretoriaRepository.save(diretoria);
		
		System.out.println("Diretoria cadastrada!");
		
		Usuario usuario = new Usuario();
		usuario.setNome("Maurício Laynner");
		usuario.setEmail("mauriciolaynner@gmail.com");
		usuario.setLogin("mlaynner");
		usuario.setSenha(PasswordUtils.gerarBCrypt("teste"));
		usuario.setDiretoriaId(diretoria.getId());
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN_GERAL);
		usuarioRepository.save(usuario);
		
		System.out.println("Usuário cadastrado!");
	}
	
	@After
	public final void tearDown() {
		this.diretoriaRepository.deleteAll();
		this.usuarioRepository.deleteAll();
	}
	
	@Test
	@WithMockUser
	public void testBuscarTodosUsuarios() throws Exception {
		System.out.println("Buscando todos os usuários!");
		
		List<Usuario> usuarios = this.usuarioService.findAll();
		
		System.out.println("Usuários: " + usuarios);
				
	}
	
	@Test
	@WithMockUser
	public void testBuscarUsuariosPorNome() throws Exception {
		String nome = "Laynner";
		System.out.println("Buscando usuários por nome: " + nome);
		
		List<Usuario> usuarios = this.usuarioService.findByNome(nome);
		
		System.out.println("Usuários encontrados: " + usuarios);
	}
	
	@Test
	@WithMockUser
	public void testBuscarUsuarioPorId() throws Exception {
		Long id = 1L;
		System.out.println("Buscando usuário que contém o ID" + id);
		
		Optional<Usuario> usuario = this.usuarioService.findById(id);
		
		System.out.println("Usuário encontrado: " + usuario);
	}
	
	@Test
	@WithMockUser
	public void testPersistirUsuario() throws Exception {
		System.out.println("Persistindo usuário ... ");
		
		Diretoria diretoria = new Diretoria();
		diretoria.setDescricao("Gabinete do Prefeito Teste");
		diretoria.setSigla("GAB");
		diretoria.setTelefone("(15) 3271-7751");
		diretoria.setEmail("gabineteteste@alambari.sp.gov.br");
		diretoriaRepository.save(diretoria);
		
		System.out.println("Diretoria cadastrada!");
		
		Usuario usuario = new Usuario();
		usuario.setNome("Maurício Laynner");
		usuario.setEmail("mauriciolaynner@gmail.com");
		usuario.setLogin("mlaynner");
		usuario.setSenha(PasswordUtils.gerarBCrypt("teste"));
		usuario.setDiretoriaId(diretoria.getId());
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN_GERAL);
		usuarioService.persistir(usuario);
	
		System.out.println("Buscando todos os usuários após persistência!");
		
		List<Usuario> usuarios = this.usuarioService.findAll();
		
		System.out.println("Usuários após persistência: " + usuarios);
	}
	
	@Test
	@WithMockUser
	public void testRemoverUsuario() throws Exception {
		Long usuarioId = 1L;
		
		System.out.println("Removendo usuario com ID: " + usuarioId);
		
		this.usuarioService.remover(usuarioId);
		
		List<Usuario> usuarios = this.usuarioService.findAll();
		
		System.out.println("Usuários após remover: " + usuarios);
	}
}