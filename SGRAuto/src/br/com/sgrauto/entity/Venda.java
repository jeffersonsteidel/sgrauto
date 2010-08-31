package br.com.sgrauto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Date data;
	private Boolean vendaVista;
	private Boolean vendaPrazo;
	private BigDecimal desconto;
	private Integer qtdePrestacoes;
	private BigDecimal valorEntrada;
	private BigDecimal valorPrestacao;
	private BigDecimal valorVista;
	private BigDecimal valorPrazo;
	private Double taxaJuros;
	private BigDecimal valorTotal;

	private Empresa filial;
	private Pessoa funcionario;
	private Pessoa cliente;
	private Automovel automovel;
	private Empresa financeira;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getVendaVista() {
		return vendaVista;
	}

	public void setVendaVista(Boolean vendaVista) {
		this.vendaVista = vendaVista;
	}

	public Boolean getVendaPrazo() {
		return vendaPrazo;
	}

	public void setVendaPrazo(Boolean vendaPrazo) {
		this.vendaPrazo = vendaPrazo;
	}

	public BigDecimal getDesconto() {
		if (desconto != null) {
			desconto = desconto.setScale(2, RoundingMode.HALF_EVEN);
		}
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Integer getQtdePrestacoes() {
		return qtdePrestacoes;
	}

	public void setQtdePrestacoes(Integer qtdePrestacoes) {
		this.qtdePrestacoes = qtdePrestacoes;
	}

	public BigDecimal getValorEntrada() {
		if (valorEntrada != null) {
			valorEntrada = valorEntrada.setScale(2, RoundingMode.HALF_EVEN);
		}
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public BigDecimal getValorPrestacao() {
		if (valorPrestacao != null) {
			valorPrestacao = valorPrestacao.setScale(2, RoundingMode.HALF_EVEN);
		}
		return valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public BigDecimal getValorVista() {
		if (valorVista != null) {
			valorVista = valorVista.setScale(2, RoundingMode.HALF_EVEN);
		}
		return valorVista;
	}

	public void setValorVista(BigDecimal valorVista) {
		this.valorVista = valorVista;
	}

	public BigDecimal getValorPrazo() {
		if (valorPrazo != null) {
			valorPrazo = valorPrazo.setScale(2, RoundingMode.HALF_EVEN);
		}
		return valorPrazo;
	}

	public void setValorPrazo(BigDecimal valorPrazo) {
		this.valorPrazo = valorPrazo;
	}

	public Double getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(Double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public Empresa getFilial() {
		return filial;
	}

	public void setFilial(Empresa filial) {
		this.filial = filial;
	}

	public Pessoa getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Pessoa funcionario) {
		this.funcionario = funcionario;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public Empresa getFinanceira() {
		return financeira;
	}

	public void setFinanceira(Empresa financeira) {
		this.financeira = financeira;
	}

	public BigDecimal getValorTotal() {
		if (valorTotal != null) {
			valorTotal = valorTotal.setScale(2, RoundingMode.HALF_EVEN);
		}
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
