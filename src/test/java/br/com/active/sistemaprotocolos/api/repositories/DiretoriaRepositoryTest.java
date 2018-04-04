package br.com.active.sistemaprotocolos.api.repositories;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.active.sistemaprotocolos.api.entities.Diretoria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DiretoriaRepositoryTest {
	
	@Autowired
	private DiretoriaRepository diretoriaRepository;
	
	@Before
	public void setUp() throws Exception {
		Diretoria diretoria = new Diretoria();
		diretoria.setDescricao("Gabinete do Prefeito Teste");
		diretoria.setSigla("GAB");
		diretoria.setTelefone("(15) 3271-7751");
		diretoria.setEmail("gabineteteste@alambari.sp.gov.br");
		diretoriaRepository.save(diretoria);
	}
	
	@After
	public final void tearDown() {
		this.diretoriaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarTodasDiretorias() {
		System.out.println("Buscando todas as diretorias!");
		
		List<Diretoria> diretoria = this.diretoriaRepository.findAll();
		
		System.out.println("Diretorias: " + diretoria.get(0));
	}
	
	@Test
	public void testBuscarPorDescricao() {
		String descricao = "Teste";
		System.out.println("Buscando todas as diretorias que contém a descricao: " + descricao);
		
		List<Diretoria> diretoria = this.diretoriaRepository.findByDescricao(descricao);
		
		System.out.println("Buscando por descrição: " + diretoria);
	}
}