package  br.com.sgrauto.entity;

import java.io.Serializable;
import java.util.List;

public class Estoque implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Integer quantidade;
	private Marca marca;
	private  Modelo modelo;
	
	private List<Estoque> estoque;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Estoque> estoque) {
		this.estoque = estoque;
	}
}
