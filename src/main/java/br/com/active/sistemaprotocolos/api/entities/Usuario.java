package br.com.active.sistemaprotocolos.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.active.sistemaprotocolos.api.enums.PerfilEnum;

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable {

	private static final long serialVersionUID = 421433288676211222L;

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private PerfilEnum perfil;
	private Long diretoriaId;
	private Date dataCriacao;
	private Date dataAtualizacao;
	
	public Usuario() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "login", nullable = false)
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	@Column(name = "diretoria_id", nullable = false)
	public Long getDiretoriaId() {
		return diretoriaId;
	}
	
	public void setDiretoriaId(Long diretoriaId) {
		this.diretoriaId = diretoriaId;
	}

	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}
	
	@Override
	public String toString() {
		return "Usuario [id = " + id + ", nome = " + nome + ", email = " + email
				+ ", login = " + login + ", perfil = " + perfil + ", diretoriaId = " + diretoriaId 
				+", dataCriacao = " + dataCriacao + ", dataAtualizacao = " 
				+ dataAtualizacao + "]";
	}
}