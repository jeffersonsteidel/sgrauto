package br.com.sgrauto.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.sgrauto.dao.EstadoDAO;
import br.com.sgrauto.entity.Estado;

public class EstadoController {

	private Estado estado = new Estado();
	private List<Estado> estadoList;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getEstadoList() {
		return estadoList;
	}

	public void setEstadoList(List<Estado> estadoList) {
		this.estadoList = estadoList;
	}

	public void abrir() throws IOException {
		estado = new Estado();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarEstado.jsp");
	}

	public void salvar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		estado.setDescricao(estado.getDescricao().toUpperCase());
		estado.setUf(estado.getUf().toUpperCase());
		estadoDAO.save(estado);
		estado = new Estado();
	}

	public void carregar() throws IOException {
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.refresh(estado);
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarEstado.jsp");
	}

	public void excluir() throws IOException {
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.delete(estado);
		this.setEstadoList(estadoDAO.list());
	}

	public List<Estado> listar() throws IOException {
		EstadoDAO estadoDAO = new EstadoDAO();
		estado = new Estado();
		this.setEstadoList(estadoDAO.list());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarEstado.jsp");
		return this.getEstadoList();
	}

}
