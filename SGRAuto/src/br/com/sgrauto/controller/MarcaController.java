package br.com.sgrauto.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.sgrauto.dao.MarcaDAO;
import br.com.sgrauto.entity.Marca;

public class MarcaController {

	private Marca marca;
	private List<Marca> marcaList;

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public List<Marca> getMarcaList() {
		return marcaList;
	}

	public void setMarcaList(List<Marca> marcaList) {
		this.marcaList = marcaList;
	}

	public void abrir() throws IOException {
		marca = new Marca();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarMarca.jsp");
	}

	public void salvar() {
		MarcaDAO marcaDAO = new MarcaDAO();
		marca.setDescricao(marca.getDescricao().toUpperCase());
		marcaDAO.save(marca);
		marca = new Marca();
	}

	public void carregar() throws IOException {
		MarcaDAO marcaDAO = new MarcaDAO();
		marcaDAO.refresh(marca);
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"cadastrarMarca.jsp");
	}

	public void excluir() throws IOException {
		MarcaDAO marcaDAO = new MarcaDAO();
		marcaDAO.delete(marca);
		this.setMarcaList(marcaDAO.list());
	}

	public List<Marca> listar() throws IOException {
		MarcaDAO marcaDAO = new MarcaDAO();
		marca = new Marca();
		this.setMarcaList(marcaDAO.list());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarMarca.jsp");
		return this.getMarcaList();
	}
}
