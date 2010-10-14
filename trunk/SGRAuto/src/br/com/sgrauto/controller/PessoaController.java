package br.com.sgrauto.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sgrauto.dao.CidadeDAO;
import br.com.sgrauto.dao.EmpresaDAO;
import br.com.sgrauto.dao.EstadoDAO;
import br.com.sgrauto.dao.PessoaDAO;
import br.com.sgrauto.encripty.Encripty;
import br.com.sgrauto.entity.Cidade;
import br.com.sgrauto.entity.Empresa;
import br.com.sgrauto.entity.Estado;
import br.com.sgrauto.entity.Pessoa;
import br.com.sgrauto.validator.Validator;

public class PessoaController {

	private Pessoa pessoa;
	private List<SelectItem> estados = new ArrayList<SelectItem>();
	private List<SelectItem> cidades = new ArrayList<SelectItem>();
	private List<SelectItem> filiais = new ArrayList<SelectItem>();
	private List<Pessoa> pessoaList;
	private String senhaTemporaria;
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<SelectItem> getEstados() {
		return estados;
	}

	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}

	public List<SelectItem> getCidades() {
		return cidades;
	}

	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}

	public List<SelectItem> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<SelectItem> filiais) {
		this.filiais = filiais;
	}

	public List<Pessoa> getPessoaList() {
		return pessoaList;
	}
	public void setPessoaList(List<Pessoa> pessoaList) {
		this.pessoaList = pessoaList;
	}
	
	public String getSenhaTemporaria() {
		return senhaTemporaria;
	}

	public void setSenhaTemporaria(String senhaTemporaria) {
		this.senhaTemporaria = senhaTemporaria;
	}
	
	public void abrirCliente() throws IOException {
		pessoa = new Pessoa();
		pessoa.setCidade(new Cidade());
		pessoa.getCidade().setEstado(new Estado());
		pessoa.setFilial(new Empresa());
		estados = new ArrayList<SelectItem>();
		cidades = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		listarEstados();
		listarFiliais();
		pessoa.setIndCliente(true);
		pessoa.setIndFuncionario(false);
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarPessoaCliente.jsp");
	}

	public void abrirFuncionario() throws IOException {
		pessoa = new Pessoa();
		pessoa.setCidade(new Cidade());
		pessoa.getCidade().setEstado(new Estado());
		pessoa.setFilial(new Empresa());
		estados = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		cidades = new ArrayList<SelectItem>();
		listarEstados();
		listarFiliais();
		pessoa.setIndFuncionario(true);
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarPessoaFuncionario.jsp");
	}
	
	public void salvar() throws NoSuchAlgorithmException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoa.setBairro(pessoa.getBairro().toUpperCase());
		pessoa.setCargo(pessoa.getCargo().toUpperCase());
		pessoa.setEmail(pessoa.getEmail().toUpperCase());
		pessoa.setEndereco(pessoa.getEndereco().toUpperCase());
		pessoa.setTelefone(pessoa.getTelefone().toUpperCase());
		pessoa.setCep(pessoa.getCep().toUpperCase());
		pessoa.setNome(pessoa.getNome().toUpperCase());
		if(pessoa.getLogin() != null){
			pessoa.setLogin(pessoa.getLogin().toUpperCase());
		}
		pessoa.setIndDesat(false);
		if ((pessoa.getSenha() != null && pessoa.getSenha() != "") && pessoa.getCodigo() == null) {
			String senha = Encripty.criptografaSenha(pessoa.getSenha());
			pessoa.setSenha(senha);
		}
		if(senhaTemporaria != null &&  pessoa.getCodigo() != null){
			pessoa.setSenha(senhaTemporaria);
			senhaTemporaria = null;
		}
		if (Validator.validarCNPJCPF(pessoa.getCpf())) {
			if (Validator.validarEmail(pessoa.getEmail())) {
				pessoaDAO.save(pessoa);
				pessoa = new Pessoa();
				pessoa.setCidade(new Cidade());
				pessoa.getCidade().setEstado(new Estado());
				pessoa.setFilial(new Empresa());
				cidades = new ArrayList<SelectItem>();
			} else {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "E-mail inválido!",
						"E-mail inválido!");
				FacesContext.getCurrentInstance().addMessage("erroMarker",
						message);
			}
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "CPF inválido!",
					"CPF inválido!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		}
	}

	public void carregarCliente() throws IOException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.refresh(pessoa);
		listarEstados();
		listarCidades();
		listarFiliais();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarPessoaCliente.jsp");
	}
	
	public void carregarFuncionario() throws IOException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.refresh(pessoa);
		this.senhaTemporaria = pessoa.getSenha();
		listarEstados();
		listarCidades();
		listarFiliais();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarPessoaFuncionario.jsp");
	}


	public void ativar() throws IOException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.refresh(pessoa);
		pessoa.setIndDesat(false);
		pessoaDAO = new PessoaDAO();
		pessoa.setDataSaida(null);
		pessoaDAO.save(pessoa);
		this.setPessoaList(listarFuncionarios());
	}

	public void desativar() throws IOException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.refresh(pessoa);
		pessoa.setIndDesat(true);
		pessoaDAO = new PessoaDAO();
		pessoa.setDataSaida(new Date());
		pessoaDAO.save(pessoa);
		this.setPessoaList(listarFuncionarios());
	}
	
	public List<Pessoa> listarClientes() throws IOException {
		pessoa = new Pessoa();
		pessoa.setCidade(new Cidade());
		pessoa.getCidade().setEstado(new Estado());
		estados = new ArrayList<SelectItem>();
		cidades = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		PessoaDAO pessoaDAO = new PessoaDAO();
		this.setPessoaList(pessoaDAO.listByCliente());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarPessoaCliente.jsp");
		return this.getPessoaList();
	}
	
	public List<Pessoa> listarFuncionarios() throws IOException {
		pessoa = new Pessoa();
		pessoa.setCidade(new Cidade());
		estados = new ArrayList<SelectItem>();
		cidades = new ArrayList<SelectItem>();
		filiais = new ArrayList<SelectItem>();
		PessoaDAO pessoaDAO = new PessoaDAO();
		this.setPessoaList(pessoaDAO.listByFuncionario());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarPessoaFuncionario.jsp");
		return this.getPessoaList();
	}

	public List<SelectItem> listarEstados() throws IOException {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> estadoList = new ArrayList<Estado>();
		estadoList = estadoDAO.list();
		for (Estado estado : estadoList) {
			estados.add(new SelectItem(estado.getCodigo(), estado
					.getDescricao()));
		}
		return estados;
	}

	public List<SelectItem> listarCidades() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidades = new ArrayList<SelectItem>();
		List<Cidade> cidadeList = new ArrayList<Cidade>();
		cidadeList = cidadeDAO.listByEstado(pessoa.getCidade().getEstado());
		for (Cidade cidade : cidadeList) {
			cidades.add(new SelectItem(cidade.getCodigo(), cidade
					.getDescricao()));
		}
		return cidades;
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

}
