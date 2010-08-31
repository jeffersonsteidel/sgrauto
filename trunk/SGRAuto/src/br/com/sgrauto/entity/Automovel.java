package br.com.sgrauto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Automovel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String placa;
	private String descricao;
	private String chassi;
	private Integer ano;
	private Integer anoModelo;
	private String cor;
	private String combustivel;
	private Integer kmRodados;
	private Boolean kitGas;
	private Boolean arCondicionado;
	private Boolean arQuente;
	private Boolean arFrio;
	private Boolean vidroEletrico;
	private Boolean vidroVerde;
	private Boolean isulfilm;
	private Boolean airBag;
	private Boolean abs;
	private Boolean travaEletrica;
	private Boolean alarme;
	private Boolean cdPlayer;
	private Boolean desembacador;
	private Boolean limpadorTraseiro;
	private Boolean direcaoHidraulica;
	private Boolean rodaLigaLeve;
	private Boolean cambioAutomatico;
	private String outros;
	private BigDecimal valorDeVenda;
	private BigDecimal valorDeCompra;
	private Date dataEntrada;
	private Date dataSaida;
	private Boolean indVendido = false;
	private Modelo modelo;
	private Empresa filial;
	
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

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public Integer getKmRodados() {
		return kmRodados;
	}

	public void setKmRodados(Integer kmRodados) {
		this.kmRodados = kmRodados;
	}

	public Boolean getKitGas() {
		return kitGas;
	}

	public void setKitGas(Boolean kitGas) {
		this.kitGas = kitGas;
	}

	public Boolean getArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(Boolean arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	public Boolean getArQuente() {
		return arQuente;
	}

	public void setArQuente(Boolean arQuente) {
		this.arQuente = arQuente;
	}

	public Boolean getArFrio() {
		return arFrio;
	}

	public void setArFrio(Boolean arFrio) {
		this.arFrio = arFrio;
	}

	public Boolean getVidroEletrico() {
		return vidroEletrico;
	}

	public void setVidroEletrico(Boolean vidroEletrico) {
		this.vidroEletrico = vidroEletrico;
	}

	public Boolean getVidroVerde() {
		return vidroVerde;
	}

	public void setVidroVerde(Boolean vidroVerde) {
		this.vidroVerde = vidroVerde;
	}

	public Boolean getIsulfilm() {
		return isulfilm;
	}

	public void setIsulfilm(Boolean isulfilm) {
		this.isulfilm = isulfilm;
	}

	public Boolean getAirBag() {
		return airBag;
	}

	public void setAirBag(Boolean airBag) {
		this.airBag = airBag;
	}

	public Boolean getAbs() {
		return abs;
	}

	public void setAbs(Boolean abs) {
		this.abs = abs;
	}

	public Boolean getTravaEletrica() {
		return travaEletrica;
	}

	public void setTravaEletrica(Boolean travaEletrica) {
		this.travaEletrica = travaEletrica;
	}

	public Boolean getAlarme() {
		return alarme;
	}

	public void setAlarme(Boolean alarme) {
		this.alarme = alarme;
	}

	public Boolean getCdPlayer() {
		return cdPlayer;
	}

	public void setCdPlayer(Boolean cdPlayer) {
		this.cdPlayer = cdPlayer;
	}

	public Boolean getDesembacador() {
		return desembacador;
	}

	public void setDesembacador(Boolean desembacador) {
		this.desembacador = desembacador;
	}

	public Boolean getLimpadorTraseiro() {
		return limpadorTraseiro;
	}

	public void setLimpadorTraseiro(Boolean limpadorTraseiro) {
		this.limpadorTraseiro = limpadorTraseiro;
	}

	public Boolean getDirecaoHidraulica() {
		return direcaoHidraulica;
	}

	public void setDirecaoHidraulica(Boolean direcaoHidraulica) {
		this.direcaoHidraulica = direcaoHidraulica;
	}

	public Boolean getRodaLigaLeve() {
		return rodaLigaLeve;
	}

	public void setRodaLigaLeve(Boolean rodaLigaLeve) {
		this.rodaLigaLeve = rodaLigaLeve;
	}

	public Boolean getCambioAutomatico() {
		return cambioAutomatico;
	}

	public void setCambioAutomatico(Boolean cambioAutomatico) {
		this.cambioAutomatico = cambioAutomatico;
	}

	public String getOutros() {
		return outros;
	}

	public void setOutros(String outros) {
		this.outros = outros;
	}

	public BigDecimal getValorDeVenda() {
		if (valorDeVenda != null) {
			valorDeVenda = valorDeVenda.setScale(2, RoundingMode.HALF_EVEN);
		}
		return valorDeVenda;
	}

	public void setValorDeVenda(BigDecimal valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public BigDecimal getValorDeCompra() {
		if (valorDeCompra != null) {
			valorDeCompra = valorDeCompra.setScale(2, RoundingMode.HALF_EVEN);
		}
		return valorDeCompra;
	}

	public void setValorDeCompra(BigDecimal valorDeCompra) {
		this.valorDeCompra = valorDeCompra;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Boolean getIndVendido() {
		return indVendido;
	}

	public void setIndVendido(Boolean indVendido) {
		this.indVendido = indVendido;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Empresa getFilial() {
		return filial;
	}

	public void setFilial(Empresa filial) {
		this.filial = filial;
	}
}
