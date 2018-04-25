package br.com.active.sistemaprotocolos.api.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CadastroDiretoriaDTO {
	
	private Long id;
	private String nome;
	private String sigla;
	private String telefone;
	private String email;
	
	public CadastroDiretoriaDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Nome deverá ser preenchido")
	@Length(min = 5, max = 200, message = "Nome deve conter entre 5 e 200 caracteres")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotEmpty(message = "Sigla deverá ser preenchido")
	@Length(min = 1, max = 3, message = "Sigla deve conter entre 1 e 3 caracteres")
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@NotEmpty(message = "Email deverá ser preenchido")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres")
	@Email(message = "Email inválido")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "CadastroDiretoriaDTO [id = " + id + ", nome = " + nome +
				", sigla = " + sigla + ", telefone = " + telefone + ", email = "
				+ email + "]";
	}

}