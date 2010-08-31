package br.com.sgrauto.entity;

import java.io.Serializable;

public class AutomovelFotos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Automovel automovel;
	private byte[] foto;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Automovel getAutomovel() {
		return automovel;
	}
	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
