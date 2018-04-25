package br.com.active.sistemaprotocolos.api.dtos;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CadastroProtocoloDTO {
	
	private Long id;
	private Long diretoriaId;
	private Long usuarioId;
	private String email;
	private String descricao;
	
	public CadastroProtocoloDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDiretoriaId() {
		return diretoriaId;
	}
	
	public void setDiretoriaId(Long diretoriaId) {
		this.diretoriaId = diretoriaId;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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

	@NotEmpty(message = "Descrição deverá ser preenchido")
	@Length(min = 5, max = 200, message = "Descricao deve conter entre 5 e 200 caracteres")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "CadastroProtocoloDTO [id = " + id + ", diretoriaId = " + diretoriaId +
				", usuarioId = " + usuarioId + ", email = " + email + ", descricao = "
				+ descricao + "]";
	}
}