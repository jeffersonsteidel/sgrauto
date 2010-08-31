package br.com.sgrauto.entity;

import java.io.Serializable;

public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String descricao;
	private Estado estado;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
