package br.com.sgrauto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sgrauto.dao.CidadeDAO;
import br.com.sgrauto.dao.EmpresaDAO;
import br.com.sgrauto.dao.EstadoDAO;
import br.com.sgrauto.entity.Cidade;
import br.com.sgrauto.entity.Empresa;
import br.com.sgrauto.entity.Estado;
import br.com.sgrauto.validator.Validator;

public class EmpresaController {

	private Empresa empresa;
	private List<SelectItem> estados = new ArrayList<SelectItem>();
	private List<SelectItem> cidades = new ArrayList<SelectItem>();
	private List<Empresa> empresaList;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
	
	public List<Empresa> getEmpresaList() {
		return empresaList;
	}
	public void setEmpresaList(List<Empresa> empresaList) {
		this.empresaList = empresaList;
	}

	public void abrir() throws IOException {
		empresa = new Empresa();
		estados = new ArrayList<SelectItem>();
		cidades = new ArrayList<SelectItem>();
		listarEstados();
		empresa.setCidade(new Cidade());
		empresa.getCidade().setEstado(new Estado());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarEmpresa.jsp");
	}

	public void salvar() {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresa.setBairro(empresa.getBairro().toUpperCase());
		empresa.setCnpj(empresa.getCnpj().toUpperCase());
		empresa.setRazaoSocial(empresa.getRazaoSocial().toUpperCase());
		empresa.setNomeFantasia(empresa.getNomeFantasia().toUpperCase());
		empresa.setCep(empresa.getCep().toUpperCase());
		empresa.setContato(empresa.getContato().toUpperCase());
		empresa.setEndereco(empresa.getEndereco().toUpperCase());
		empresa.setTelefone(empresa.getTelefone().toUpperCase());
		if (Validator.validarCNPJCPF(empresa.getCnpj())) {
			empresaDAO.save(empresa);
			empresa = new Empresa();
			empresa.setCidade(new Cidade());
			empresa.getCidade().setEstado(new Estado());
			cidades = new ArrayList<SelectItem>();
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "CNPJ inválido!",
					"CNPJ inválido!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		}
	}

	public void carregar() throws IOException {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.refresh(empresa);
		listarEstados();
		listarCidades();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarEmpresa.jsp");
	}

	public void ativar() throws IOException {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.refresh(empresa);
		empresa.setIndDesat(false);
		empresaDAO = new EmpresaDAO();
		empresaDAO.save(empresa);
		this.setEmpresaList(listar());
	}

	public void desativar() throws IOException {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.refresh(empresa);
		empresa.setIndDesat(true);
		empresaDAO = new EmpresaDAO();
		empresaDAO.save(empresa);
		this.setEmpresaList(listar());
	}

	public void excluir() throws IOException {
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresaDAO.delete(empresa);
		this.setEmpresaList(listar());
	}

	public List<Empresa> listar() throws IOException {
		empresa = new Empresa();
		empresa.setCidade(new Cidade());
		empresa.getCidade().setEstado(new Estado());
		estados = new ArrayList<SelectItem>();
		cidades = new ArrayList<SelectItem>();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		this.setEmpresaList(empresaDAO.list());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarEmpresa.jsp");
		return this.getEmpresaList();
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
		cidadeList = cidadeDAO.listByEstado(empresa.getCidade().getEstado());
		for (Cidade cidade : cidadeList) {
			cidades.add(new SelectItem(cidade.getCodigo(), cidade
					.getDescricao()));
		}
		return cidades;
	}

}
