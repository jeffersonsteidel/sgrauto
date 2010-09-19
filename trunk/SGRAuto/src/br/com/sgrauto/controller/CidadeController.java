package br.com.sgrauto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sgrauto.dao.CidadeDAO;
import br.com.sgrauto.dao.EstadoDAO;
import br.com.sgrauto.entity.Cidade;
import br.com.sgrauto.entity.Estado;

public class CidadeController {

	private Cidade cidade;
	private List<SelectItem> estados = new ArrayList<SelectItem>();
	private List<Cidade> cidadeList;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<SelectItem> getEstados() {
		return estados;
	}

	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidadeList() {
		return cidadeList;
	}
	public void setCidadeList(List<Cidade> cidadeList) {
		this.cidadeList = cidadeList;
	}
	public void abrir() throws IOException {
		cidade = new Cidade();
		cidade.setEstado(new Estado());
		estados = new ArrayList<SelectItem>();
		listarEstados();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarCidade.jsp");
	}

	public void salvar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidade.setDescricao(cidade.getDescricao().toUpperCase());
		cidadeDAO.save(cidade);
		cidade = new Cidade();
		cidade.setEstado(new Estado());
	}

	public void carregar() throws IOException {
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.refresh(cidade);
		listarEstados();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarCidade.jsp");
	}

	public void excluir() throws IOException {
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.delete(cidade);
		this.setCidadeList(cidadeDAO.list());
	}

	public List<Cidade> listar() throws IOException {
		cidade = new Cidade();
		cidade.setEstado(new Estado());
		estados = new ArrayList<SelectItem>();
		CidadeDAO cidadeDAO = new CidadeDAO();
		this.setCidadeList(cidadeDAO.list());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarCidade.jsp");
		return this.getCidadeList();
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
}
