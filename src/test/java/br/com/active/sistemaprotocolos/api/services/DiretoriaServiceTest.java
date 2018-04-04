package br.com.active.sistemaprotocolos.api.services;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.active.sistemaprotocolos.api.entities.Diretoria;
import br.com.active.sistemaprotocolos.api.repositories.DiretoriaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DiretoriaServiceTest {
	
	@Autowired
	private DiretoriaRepository diretoriaRepository;
	
	@Autowired
	private DiretoriaService diretoriaService;
	
	@Before
	public void setUp() throws Exception {
		Diretoria diretoria = new Diretoria();
		diretoria.setDescricao("Gabinete do Prefeito Teste");
		diretoria.setSigla("GAB");
		diretoria.setTelefone("(15) 3271-7751");
		diretoria.setEmail("gabineteteste@alambari.sp.gov.br");
		diretoriaRepository.save(diretoria);
	}
	
	@Test
	public void testBuscarTodasDiretorias() throws Exception {
		System.out.println("Buscando todas as diretorias!");
		List<Diretoria> diretorias = this.diretoriaService.findAll();
		
		System.out.println("Diretorias: " + diretorias);
	}
	
	@Test
	public void testBuscarPorDescricao() throws Exception {
		String descricao = "Teste";
		System.out.println("Buscando todas as diretorias cuja descrição contém: " + descricao);
		
		List<Diretoria> diretorias = this.diretoriaService.findByDescricao(descricao);
		
		System.out.println("Diretorias: " + diretorias);
	}
	
	@Test
	public void testPersistirDiretoria() throws Exception {
		System.out.println("Persistindo Diretoria");
		
		Diretoria diretoria = new Diretoria();
		diretoria.setDescricao("Gabinete do Prefeito Teste 2");
		diretoria.setSigla("GAB");
		diretoria.setTelefone("(15) 3271-7752");
		diretoria.setEmail("gabineteteste2@alambari.sp.gov.br");
		
		this.diretoriaService.persistir(diretoria);
		List<Diretoria> diretorias = this.diretoriaService.findAll();
		
		System.out.println("Diretorias após persistir: " + diretorias);
	}
	
	@Test
	public void testRemoverDiretoria() throws Exception {
		Long diretoriaId = 1L;
		
		System.out.println("Removendo diretoria com ID: " + diretoriaId);
		
		this.diretoriaService.remover(diretoriaId);
		
		List<Diretoria> diretorias = this.diretoriaService.findAll();
		
		System.out.println("Diretorias após remover: " + diretorias);
	}
}