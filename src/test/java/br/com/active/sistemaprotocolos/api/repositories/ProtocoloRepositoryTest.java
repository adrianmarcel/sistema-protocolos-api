package br.com.active.sistemaprotocolos.api.repositories;

import java.util.List;

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
import br.com.active.sistemaprotocolos.api.entities.Protocolo;
import br.com.active.sistemaprotocolos.api.entities.Usuario;
import br.com.active.sistemaprotocolos.api.enums.PerfilEnum;
import br.com.active.sistemaprotocolos.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProtocoloRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private DiretoriaRepository diretoriaRepository;
	
	@Autowired
	private ProtocoloRepository protocoloRepository;
	
	private Long idUsuario = 0L;
	private Long idDiretoria = 0L;
	
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
		
		System.out.println("Setando ID do usuário na variável");
		idUsuario = usuario.getId();
		
		System.out.println("Setando ID da diretoria na variável");
		idDiretoria = diretoria.getId();
		
		System.out.println("Usuário cadastrado!");
		
		Protocolo protocolo = new Protocolo();
		protocolo.setDiretoriaId(diretoria.getId());
		protocolo.setUsuarioId(usuario.getId());
		protocolo.setEmail("gabineteteste@alambari.sp.gov.br");
		protocolo.setDescricao("Teste de criação de protocolo");
		protocoloRepository.save(protocolo);
		
		System.out.println("Protocolo cadastrado com sucesso!");
	}
	
	@After
	public final void tearDown() {
		this.diretoriaRepository.deleteAll();
		this.usuarioRepository.deleteAll();
		this.protocoloRepository.deleteAll();
	}
	
	@Test
	@WithMockUser
	public void testBuscarProtocolos() {
		System.out.println("Buscando todos os protocolos cadastrados na aplicação!");
		List<Protocolo> protocolo = this.protocoloRepository.findAll();
		
		System.out.println("Quantidade de protocolos encontrados: " + protocolo.size());
		System.out.println("Protocolos: " + protocolo.toString());
	}
	
	@Test
	@WithMockUser
	public void testBuscarProtocolosPorUsuario() {
		System.out.println("Buscando todos os protocolos para o usuário cujo ID é igual a: " + idUsuario);
		List<Protocolo> protocolo = this.protocoloRepository.findByUsuarioId(idUsuario);
		
		System.out.println("Quantidade de protocolos encontrados: " + protocolo.size());
		System.out.println("Protocolos: " + protocolo.toString());
	}
	
	@Test
	@WithMockUser
	public void testBuscarProtocolosPorDiretoria() {
		System.out.println("Buscando todos os protocolos para a diretoria cujo ID é igual a: " + idDiretoria);
		List<Protocolo> protocolo = this.protocoloRepository.findByDiretoriaId(idDiretoria);
		
		System.out.println("Quantidade de protocolos encontrados: " + protocolo.size());
		System.out.println("Protocolos: " + protocolo.toString());
	}
}