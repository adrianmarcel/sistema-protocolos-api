package br.com.active.sistemaprotocolos.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "protocolo")
public class Protocolo implements Serializable {

	private static final long serialVersionUID = 1147709337422772447L;

	private Long id;
	private String descricao;
	private String email;
	private Long usuarioId;
	private Long diretoriaId;
	private Date dataCriacao;
	private Date dataAtualizacao;
	
	public Protocolo() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "usuario_id", nullable = false)
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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
		return "Protocolo [id = " + id + ", descricao = " + descricao + ", email = " + email
			    + ", usuarioId = " + usuarioId + ", diretoriaId = " + diretoriaId 
				+ ", dataCriacao = " + dataCriacao + ", dataAtualizacao = " + dataAtualizacao + "]";
	}	
}