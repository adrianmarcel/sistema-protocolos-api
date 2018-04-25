package br.com.active.sistemaprotocolos.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class CadastroUsuarioDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private Long diretoriaId;
	private String perfil;
	
	public CadastroUsuarioDTO() {
		
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

	@NotEmpty(message = "Email deverá ser preenchido")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Login deverá ser informado")
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty(message = "Senha deverá ser preenchido")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getDiretoriaId() {
		return diretoriaId;
	}

	public void setDiretoriaId(Long diretoriaId) {
		this.diretoriaId = diretoriaId;
	}

	@NotEmpty(message = "Perfil deverá ser preenchido")
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	@Override
	public String toString() {
		return "CadastroUsuarioDTO [id = " + id + ", nome = " + nome + ", email = " + email
				+ ", login = " + login + ", diretoriaId = " + diretoriaId
				+ ", perfil = " + perfil + "]";
	}

}