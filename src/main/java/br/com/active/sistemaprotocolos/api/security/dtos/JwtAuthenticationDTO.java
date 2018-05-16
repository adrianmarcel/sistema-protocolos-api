package br.com.active.sistemaprotocolos.api.security.dtos;

import org.hibernate.validator.constraints.NotEmpty;

public class JwtAuthenticationDTO {
	
	private String login;
	private String senha;

	public JwtAuthenticationDTO() {
		
	}

	@NotEmpty(message = "Login não pode ser vazio.")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationRequestDTO [login=" + login + ", senha=" + senha + "]";
	}	
}