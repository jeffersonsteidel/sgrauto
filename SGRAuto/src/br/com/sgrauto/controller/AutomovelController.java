package br.com.sgrauto.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.com.sgrauto.dao.AutomovelDAO;
import br.com.sgrauto.dao.EmpresaDAO;
import br.com.sgrauto.dao.MarcaDAO;
import br.com.sgrauto.dao.ModeloDAO;
import br.com.sgrauto.entity.Automovel;
import br.com.sgrauto.entity.AutomovelFotos;
import br.com.sgrauto.entity.Empresa;
import br.com.sgrauto.entity.Marca;
import br.com.sgrauto.entity.Modelo;
import br.com.sgrauto.validator.Validator;

public class AutomovelController {

	private Automovel automovel;
	private AutomovelFotos automovelFotos;
	private List<SelectItem> modelos = new ArrayList<SelectItem>();
	private List<SelectItem> marcas = new ArrayList<SelectItem>();
	private List<SelectItem> filiais  = new ArrayList<SelectItem>();
	private String opcao;
	private Boolean btExcluir;
	private Integer anoInicial;
	private Integer anoFinal;
	private BigDecimal precoVendaInicial;
	private BigDecimal precoVendaFinal;
	private Date dataEntradaInicial;
	private Date dataEntradaFinal;
	private List<Automovel> automovelList;
	private List<AutomovelFotos> automovelFotosList;

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public List<SelectItem> getModelos() {
		return modelos;
	}

	public void setModelos(List<SelectItem> modelos) {
		this.modelos = modelos;
	}

	public List<SelectItem> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<SelectItem> marcas) {
		this.marcas = marcas;
	}

	public List<SelectItem> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<SelectItem> filiais) {
		this.filiais = filiais;
	}

	public AutomovelFotos getAutomovelFotos() {
		return automovelFotos;
	}

	public void setAutomovelFotos(AutomovelFotos automovelFotos) {
		this.automovelFotos = automovelFotos;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Integer getAnoInicial() {
		return anoInicial;
	}

	public void setAnoInicial(Integer anoInicial) {
		this.anoInicial = anoInicial;
	}

	public Integer getAnoFinal() {
		return anoFinal;
	}

	public void setAnoFinal(Integer anoFinal) {
		this.anoFinal = anoFinal;
	}

	public BigDecimal getPrecoVendaInicial() {
		if (precoVendaInicial != null) {
			precoVendaInicial = precoVendaInicial.setScale(2,
					RoundingMode.HALF_EVEN);
		}
		return precoVendaInicial;
	}

	public void setPrecoVendaInicial(BigDecimal precoVendaInicial) {
		this.precoVendaInicial = precoVendaInicial;
	}

	public BigDecimal getPrecoVendaFinal() {
		if (precoVendaFinal != null) {
			precoVendaFinal = precoVendaFinal.setScale(2,
					RoundingMode.HALF_EVEN);
		}
		return precoVendaFinal;
	}

	public void setPrecoVendaFinal(BigDecimal precoVendaFinal) {
		this.precoVendaFinal = precoVendaFinal;
	}

	public Date getDataEntradaInicial() {
		return dataEntradaInicial;
	}

	public void setDataEntradaInicial(Date dataEntradaInicial) {
		this.dataEntradaInicial = dataEntradaInicial;
	}

	public Date getDataEntradaFinal() {
		return dataEntradaFinal;
	}

	public void setDataEntradaFinal(Date dataEntradaFinal) {
		this.dataEntradaFinal = dataEntradaFinal;
	}

	public List<Automovel> getAutomovelList() {
		return automovelList;
	}

	public void setAutomovelList(List<Automovel> automovelList) {
		this.automovelList = automovelList;
	}

	public List<AutomovelFotos> getAutomovelFotosList() {
		return automovelFotosList;
	}

	public void setAutomovelFotosList(List<AutomovelFotos> automovelFotosList) {
		this.automovelFotosList = automovelFotosList;
	}

	public Boolean getBtExcluir() {
		return btExcluir;
	}

	public void setBtExcluir(Boolean btExcluir) {
		this.btExcluir = btExcluir;
	}

	public void abrir() throws IOException {
		automovel = new Automovel();
		this.setAutomovelFotosList(new ArrayList<AutomovelFotos>());
		modelos = new ArrayList<SelectItem>();
		marcas = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		listarMarcas();
		automovel.setModelo(new Modelo());
		automovel.getModelo().setMarca(new Marca());
		automovel.setFilial(new Empresa());
		listarFiliais();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarAutomovel.jsp");
	}

	public void salvar() {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		automovel.setChassi(automovel.getChassi().toUpperCase());
		automovel.setCombustivel(automovel.getCombustivel().toUpperCase());
		automovel.setCor(automovel.getCor().toUpperCase());
		automovel.setDescricao(automovel.getDescricao().toUpperCase());
		automovel.setPlaca(automovel.getPlaca().toUpperCase());
		if (Validator.validarPlaca(automovel.getPlaca())) {
			automovelDAO.save(automovel);
			automovel = new Automovel();
			automovel.setModelo(new Modelo());
			automovel.getModelo().setMarca(new Marca());
			automovel.setFilial(new Empresa());
			modelos = new ArrayList<SelectItem>();
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Placa inválida!",
					"Placa inválida!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		}
	}

	public void carregar() throws IOException {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		automovelDAO.refresh(automovel);
		listarMarcas();
		listarModelos();
		listarFiliais();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarAutomovel.jsp");
	}

	public void excluir() throws IOException {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		automovelDAO.delete(automovel);
		this.setAutomovelList(automovelDAO.list());
	}

	public List<Automovel> listar() throws IOException {
		opcao = null;
		automovel = new Automovel();
		automovel.setModelo(new Modelo());
		automovel.getModelo().setMarca(new Marca());
		automovel.setFilial(new Empresa());
		marcas = new ArrayList<SelectItem>();
		modelos = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		this.setBtExcluir(true);
		AutomovelDAO automovelDAO = new AutomovelDAO();
		this.setAutomovelList(automovelDAO.list());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarAutomovel.jsp");
		return this.getAutomovelList();
	}

	public List<Automovel> listarByEstoque() throws IOException {
		opcao = "em Estoque";
		automovel = new Automovel();
		automovel.setModelo(new Modelo());
		automovel.getModelo().setMarca(new Marca());
		automovel.setFilial(new Empresa());
		marcas = new ArrayList<SelectItem>();
		modelos = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		this.setBtExcluir(true);
		AutomovelDAO automovelDAO = new AutomovelDAO();
		this.setAutomovelList(automovelDAO.listByEstoque());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarAutomovel.jsp");
		return this.getAutomovelList();
	}

	public List<Automovel> listarByVendidos() throws IOException {
		opcao = "Vendidos";
		automovel = new Automovel();
		automovel.setModelo(new Modelo());
		automovel.getModelo().setMarca(new Marca());
		automovel.setFilial(new Empresa());
		marcas = new ArrayList<SelectItem>();
		modelos = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		this.setBtExcluir(false);
		AutomovelDAO automovelDAO = new AutomovelDAO();
		this.setAutomovelList(automovelDAO.listByVendidos());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarAutomovel.jsp");
		return this.getAutomovelList();
	}

	public List<SelectItem> listarFiliais() throws IOException {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		List<Empresa> empresaList = new ArrayList<Empresa>();
		empresaList = empresaDAO.listByFiliaisAtivas();
		for (Empresa empresa : empresaList) {
			filiais.add(new SelectItem(empresa.getCodigo(), empresa
					.getRazaoSocial()));
		}
		return filiais;
	}

	public List<SelectItem> listarMarcas() throws IOException {
		MarcaDAO marcaDAO = new MarcaDAO();
		List<Marca> marcaList = new ArrayList<Marca>();
		marcaList = marcaDAO.list();
		for (Marca marca : marcaList) {
			marcas.add(new SelectItem(marca.getCodigo(), marca.getDescricao()));
		}
		return marcas;
	}

	public List<SelectItem> listarModelos() {
		ModeloDAO modeloDAO = new ModeloDAO();
		modelos = new ArrayList<SelectItem>();
		List<Modelo> modeloList = new ArrayList<Modelo>();
		modeloList = modeloDAO.listByMarca(automovel.getModelo().getMarca());
		for (Modelo modelo : modeloList) {
			modelos.add(new SelectItem(modelo.getCodigo(), modelo
					.getDescricao()));
		}
		return modelos;
	}

	public void abrirFotos() throws IOException {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		automovelDAO.refresh(automovel);
		automovelFotos = new AutomovelFotos();
		this.setAutomovelFotosList(new ArrayList<AutomovelFotos>());
		automovelFotos.setAutomovel(automovel);
		this.setAutomovelFotosList(automovelDAO.carregarFotos(automovel));
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarAutomovelFotos.jsp");
	}

	public void listener(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		automovelFotos.setFoto(item.getData());
		AutomovelDAO automovelDAO = new AutomovelDAO();
		automovelFotos.setAutomovel(automovel);
		automovelDAO.saveFotos(automovelFotos);
		this.setAutomovelFotosList(automovelDAO.carregarFotos(automovel));
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		if (this.getAutomovelFotosList().size() > 0) {
			stream.write(this.getAutomovelFotosList().get((Integer) object)
					.getFoto());
		}
	}

	public void excluirFotos() throws IOException {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		automovelDAO.deleteFotos(automovelFotos);
		this.getAutomovelFotosList().clear();
		abrirFotos();
	}

	public void abrirPorFiltro() throws IOException {
		automovel = new Automovel();
		automovel.setFilial(new Empresa());
		automovel.setModelo(new Modelo());
		automovel.getModelo().setMarca(new Marca());
		filiais = new ArrayList<SelectItem>();
		listarFiliais();
		this.setAutomovelList(new ArrayList<Automovel>());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarAutomovelFilter.jsp");
	}

	public List<Automovel> listarPorFiltro() throws IOException {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		this.setAutomovelList(new ArrayList<Automovel>());
		this.setAutomovelList(automovelDAO.listByFilter(automovel, anoInicial,
				anoFinal, precoVendaInicial, precoVendaFinal,
				dataEntradaInicial, dataEntradaFinal));
		if (this.getAutomovelList().size() == 0) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Nenhum registro encontrado para o filtro informado!",
					"Nenhum registro encontrado para o filtro informado!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		}
		automovel = new Automovel();
		automovel.setModelo(new Modelo());
		automovel.getModelo().setMarca(new Marca());
		automovel.setFilial(new Empresa());
		this.setAnoInicial(null);
		this.setAnoFinal(null);
		this.setDataEntradaInicial(null);
		this.setDataEntradaFinal(null);
		this.setPrecoVendaInicial(null);
		this.setPrecoVendaFinal(null);
		filiais = new ArrayList<SelectItem>();
		listarFiliais();
		return this.getAutomovelList();
	}
}
