package br.com.sgrauto.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sgrauto.dao.AutomovelDAO;
import br.com.sgrauto.dao.EmpresaDAO;
import br.com.sgrauto.dao.PessoaDAO;
import br.com.sgrauto.dao.VendaDAO;
import br.com.sgrauto.entity.Automovel;
import br.com.sgrauto.entity.Cidade;
import br.com.sgrauto.entity.Empresa;
import br.com.sgrauto.entity.Estado;
import br.com.sgrauto.entity.Marca;
import br.com.sgrauto.entity.Modelo;
import br.com.sgrauto.entity.Pessoa;
import br.com.sgrauto.entity.Venda;
import br.com.sgrauto.validator.Validator;

public class VendaController {

	private Venda venda;
	private List<SelectItem> filiais = new ArrayList<SelectItem>();
	private List<SelectItem> vendedores = new ArrayList<SelectItem>();
	private List<SelectItem> financeiras = new ArrayList<SelectItem>();
	private String formaPagamento;
	private Date dataInicial;
	private Date dataFinal;
	private List<Venda> vendaList;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<SelectItem> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<SelectItem> filiais) {
		this.filiais = filiais;
	}

	public List<SelectItem> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<SelectItem> vendedores) {
		this.vendedores = vendedores;
	}

	public List<SelectItem> getFinanceiras() {
		return financeiras;
	}

	public void setFinanceiras(List<SelectItem> financeiras) {
		this.financeiras = financeiras;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public List<Venda> getVendaList() {
		return vendaList;
	}

	public void setVendaList(List<Venda> vendaList) {
		this.vendaList = vendaList;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public void abrir() throws IOException {
		venda = new Venda();
		venda.setFilial(new Empresa());
		venda.setAutomovel(new Automovel());
		venda.getAutomovel().setModelo(new Modelo());
		venda.getAutomovel().getModelo().setMarca(new Marca());
		venda.getFilial().setCidade(new Cidade());
		venda.getFilial().getCidade().setEstado(new Estado());
		venda.setCliente(new Pessoa());
		venda.setFinanceira(new Empresa());
		venda.setFuncionario(new Pessoa());
		formaPagamento = null;
		filiais = new ArrayList<SelectItem>();
		vendedores = new ArrayList<SelectItem>();
		financeiras = new ArrayList<SelectItem>();
		listarFiliais();
		listarFinanceiras();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarVenda.jsp");
	}

	public void salvar() throws IOException {
		VendaDAO vendaDAO = new VendaDAO();
		Double descontoMaximo = 0.0;
		if ("VISTA".equals(formaPagamento)) {
			venda.setVendaVista(true);
			venda.setVendaPrazo(false);
			venda.setValorPrazo(null);
			venda.setQtdePrestacoes(null);
			venda.setTaxaJuros(null);
			venda.setValorEntrada(null);
			venda.setFinanceira(null);
			if (venda.getValorVista() != null) {
				descontoMaximo = venda.getValorVista().doubleValue()
						* (new Double(10) / new Double(100));
			}
			if (venda.getCliente().getCodigo() != null) {
				if (venda.getAutomovel().getCodigo() != null) {
					if (venda.getDesconto().doubleValue() <= descontoMaximo) {
						vendaDAO.save(venda);
						venda = new Venda();
						venda.setAutomovel(new Automovel());
						venda.getAutomovel().setModelo(new Modelo());
						venda.getAutomovel().getModelo().setMarca(new Marca());
						venda.setFilial(new Empresa());
						venda.getFilial().setCidade(new Cidade());
						venda.getFilial().getCidade().setEstado(new Estado());
						venda.setCliente(new Pessoa());
						venda.setFinanceira(new Empresa());
						venda.setFuncionario(new Pessoa());
						filiais = new ArrayList<SelectItem>();
						vendedores = new ArrayList<SelectItem>();
						financeiras = new ArrayList<SelectItem>();
						formaPagamento = null;
						listarFiliais();
						listarFinanceiras();
					} else {
						FacesMessage message = new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"O Desconto não pode ser superior a 10% o valor do veículo!",
								"O Desconto não pode ser superior a 10% o valor do veículo!");
						FacesContext.getCurrentInstance().addMessage(
								"erroMarker", message);
					}
				} else {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"O Automóvel não foi informado!",
							"O Automóvel não foi informado!");
					FacesContext.getCurrentInstance().addMessage("erroMarker",
							message);
				}
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"O Cliente não foi informado!",
						"O Cliente não foi informado!");
				FacesContext.getCurrentInstance().addMessage("erroMarker",
						message);
			}
		} else if ("PRAZO".equals(formaPagamento)) {
			venda.setVendaVista(false);
			venda.setVendaPrazo(true);
			venda.setValorTotal(venda.getValorPrazo());
			if (venda.getValorVista() != null) {
				descontoMaximo = venda.getValorVista().doubleValue()
						* (new Double(5) / new Double(100));
			}
			if (venda.getCliente().getCodigo() != null) {
				if (venda.getAutomovel().getCodigo() != null) {
					if (venda.getDesconto().doubleValue() <= descontoMaximo) {
						vendaDAO.save(venda);
						venda = new Venda();
						venda.setAutomovel(new Automovel());
						venda.getAutomovel().setModelo(new Modelo());
						venda.getAutomovel().getModelo().setMarca(new Marca());
						venda.setFilial(new Empresa());
						venda.getFilial().setCidade(new Cidade());
						venda.getFilial().getCidade().setEstado(new Estado());
						venda.setCliente(new Pessoa());
						venda.setFinanceira(new Empresa());
						venda.setFuncionario(new Pessoa());
						filiais = new ArrayList<SelectItem>();
						vendedores = new ArrayList<SelectItem>();
						financeiras = new ArrayList<SelectItem>();
						formaPagamento = null;
						listarFiliais();
						listarFinanceiras();
					} else {
						FacesMessage message = new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"O Desconto não pode ser superior a 5% o valor do veículo!",
								"O Desconto não pode ser superior a 5% o valor do veículo!");
						FacesContext.getCurrentInstance().addMessage(
								"erroMarker", message);
					}
				} else {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"O Automóvel não foi informado!",
							"O Automóvel não foi informado!");
					FacesContext.getCurrentInstance().addMessage("erroMarker",
							message);
				}
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"O Cliente não foi informado!",
						"O Cliente não foi informado!");
				FacesContext.getCurrentInstance().addMessage("erroMarker",
						message);
			}
		}
	}

	public void carregar() throws IOException {
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.refresh(venda);
		filiais = new ArrayList<SelectItem>();
		vendedores = new ArrayList<SelectItem>();
		financeiras = new ArrayList<SelectItem>();
		listarFiliais();
		listarVendedores();
		listarFinanceiras();
		if (venda.getVendaPrazo()) {
			formaPagamento = "PRAZO";
		} else {
			formaPagamento = "VISTA";
		}
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarVenda.jsp");
	}

	public List<Venda> listar() throws IOException {
		VendaDAO vendaDAO = new VendaDAO();
		venda = new Venda();
		filiais = new ArrayList<SelectItem>();
		vendedores = new ArrayList<SelectItem>();
		this.setVendaList(vendaDAO.list());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarVenda.jsp");
		return this.getVendaList();
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

	public List<SelectItem> listarFinanceiras() throws IOException {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		List<Empresa> empresaList = new ArrayList<Empresa>();
		empresaList = empresaDAO.listByFinanceirasAtivas();
		for (Empresa empresa : empresaList) {
			financeiras.add(new SelectItem(empresa.getCodigo(), empresa
					.getRazaoSocial()));
		}
		return financeiras;
	}

	public List<SelectItem> listarVendedores() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		if (venda.getFilial().getCodigo() != null) {
			empresaDAO.refresh(venda.getFilial());
			vendedores = new ArrayList<SelectItem>();
			List<Pessoa> vendedoresList = new ArrayList<Pessoa>();
			vendedoresList = pessoaDAO.listByFuncionariosAtivosByFilial(venda
					.getFilial());
			for (Pessoa pessoa : vendedoresList) {
				vendedores.add(new SelectItem(pessoa.getCodigo(), pessoa
						.getNome()));
			}
		}
		return vendedores;
	}

	public void carregarAutomovel() {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		String placa = venda.getAutomovel().getPlaca().toUpperCase();
		venda.setAutomovel(new Automovel());
		venda.getAutomovel().setModelo(new Modelo());
		venda.getAutomovel().getModelo().setMarca(new Marca());
		venda.getAutomovel().setPlaca(placa);
		if (Validator.validarPlaca(venda.getAutomovel().getPlaca())) {
			Automovel automovel = new Automovel();
			automovel = automovelDAO.automovelByEstoque(venda.getAutomovel());
			if (automovel == null) {
				venda.setAutomovel(new Automovel());
				venda.setValorVista(null);
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Automóvel indisponível!",
						"Automóvel indisponível!");
				FacesContext.getCurrentInstance().addMessage("erroMarker",
						message);
			} else {
				venda.setAutomovel(automovel);
				venda.setValorVista(venda.getAutomovel().getValorDeVenda());
			}
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Placa inválida!",
					"Placa inválida!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		}
	}

	public void carregarCliente() throws ParseException {
		if (Validator.validarCNPJCPF(venda.getCliente().getCpf())) {
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa cliente = new Pessoa();
			cliente = pessoaDAO.buscarCliente(venda.getCliente());
			if (cliente != null) {
				venda.setCliente(cliente);
			} else {
				venda.setCliente(new Pessoa());
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Cliente não cadastrado!",
						"Cliente não cadastrado!");
				FacesContext.getCurrentInstance().addMessage("erroMarker",
						message);
			}
		} else {
			venda.setCliente(new Pessoa());
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "CPF inválido!",
					"CPF nválido!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		}
	}

	public void calcularDesconto() {
		if (venda.getDesconto() != null && venda.getValorVista() != null) {
			BigDecimal precoTotal = venda.getValorVista().subtract(
					venda.getDesconto());
			venda.setValorTotal(precoTotal);
		} else {
			venda.setValorTotal(venda.getValorVista());
		}
	}

	public void calcularFinanciamento() {
		if (venda.getVendaVista() != null && venda.getDesconto() != null
				&& venda.getValorEntrada() != null
				&& venda.getValorVista() != null
				&& venda.getTaxaJuros() != null
				&& venda.getQtdePrestacoes() != null) {
			Double valor = venda.getValorVista().doubleValue()
					- venda.getDesconto().doubleValue()
					- venda.getValorEntrada().doubleValue();
			Double taxa = venda.getTaxaJuros() / 100;
			Integer prestacoes = venda.getQtdePrestacoes();
			Double valorPrestacao = valor
					* ((taxa * Math.pow((1 + taxa), prestacoes)))
					/ (Math.pow(1 + taxa, prestacoes) - 1);

			venda.setValorPrestacao(new BigDecimal(valorPrestacao));
			Double valorAPrazo = venda.getValorEntrada().doubleValue()
					+ (venda.getQtdePrestacoes() * valorPrestacao);
			venda.setValorPrazo(new BigDecimal(valorAPrazo));
			venda.setValorTotal(venda.getValorVista().subtract(
					venda.getDesconto()));
		}
	}

	public void escolherFormaPagamento() throws IOException {
		if ("VISTA".equals(formaPagamento)) {
			venda.setVendaVista(true);
			venda.setVendaPrazo(false);
			venda.setDesconto(null);
			venda.setValorTotal(null);
		} else if ("PRAZO".equals(formaPagamento)) {
			venda.setVendaVista(false);
			venda.setVendaPrazo(true);
			venda.setDesconto(null);
			venda.setValorTotal(null);
		}
	}

	public void abrirPorFiltro() throws IOException {
		venda = new Venda();
		this.setVendaList(new ArrayList<Venda>());
		venda.setAutomovel(new Automovel());
		venda.getAutomovel().setModelo(new Modelo());
		venda.getAutomovel().getModelo().setMarca(new Marca());
		venda.setFilial(new Empresa());
		venda.getFilial().setCidade(new Cidade());
		venda.getFilial().getCidade().setEstado(new Estado());
		venda.setCliente(new Pessoa());
		venda.setFinanceira(new Empresa());
		venda.setFuncionario(new Pessoa());
		filiais = new ArrayList<SelectItem>();
		vendedores = new ArrayList<SelectItem>();
		financeiras = new ArrayList<SelectItem>();
		formaPagamento = null;
		listarFiliais();
		listarFinanceiras();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarVendaFilter.jsp");
	}

	public List<Venda> listarPorFiltro() throws IOException {
		VendaDAO vendaDAO = new VendaDAO();
		this.setVendaList(new ArrayList<Venda>());
		this.setVendaList(vendaDAO.listByFilter(venda, dataInicial, dataFinal));
		if (this.getVendaList().size() == 0) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Nenhum registro encontrado para o filtro informado!",
					"Nenhum registro encontrado para o filtro informado!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
			venda = new Venda();
		}
		venda.setFilial(new Empresa());
		venda.setAutomovel(new Automovel());
		venda.setCliente(new Pessoa());
		venda.setFinanceira(new Empresa());
		venda.setFuncionario(new Pessoa());
		filiais = new ArrayList<SelectItem>();
		vendedores = new ArrayList<SelectItem>();
		financeiras = new ArrayList<SelectItem>();
		formaPagamento = null;
		this.setDataInicial(null);
		this.setDataFinal(null);
		listarFiliais();
		listarFinanceiras();
		return this.getVendaList();
	}

	public void carregarAutomovelVendido() {
		AutomovelDAO automovelDAO = new AutomovelDAO();
		String placa = venda.getAutomovel().getPlaca().toUpperCase();
		venda.setAutomovel(new Automovel());
		venda.getAutomovel().setPlaca(placa);
		if (Validator.validarPlaca(venda.getAutomovel().getPlaca())) {
			Automovel automovel = new Automovel();
			automovel = automovelDAO.automovelByVendido(venda.getAutomovel());
			if (automovel == null) {
				venda.setAutomovel(new Automovel());
				venda.setValorVista(null);
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Automóvel indisponível!",
						"Automóvel indisponível!");
				FacesContext.getCurrentInstance().addMessage("erroMarker",
						message);
			} else {
				venda.setAutomovel(automovel);
				venda.setValorVista(venda.getAutomovel().getValorDeVenda());
			}
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Placa inválida!",
					"Placa inválida!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		}
	}
}
