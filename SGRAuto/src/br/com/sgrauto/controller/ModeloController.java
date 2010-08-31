package br.com.sgrauto.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sgrauto.dao.MarcaDAO;
import br.com.sgrauto.dao.ModeloDAO;
import br.com.sgrauto.entity.Marca;
import br.com.sgrauto.entity.Modelo;

public class ModeloController{
	
	private Modelo modelo;
	private List<SelectItem> marcas; 
	private List<Modelo> modeloList;
	
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public List<SelectItem> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<SelectItem> marcas) {
		this.marcas = marcas;
	}
	
	public List<Modelo> getModeloList() {
		return modeloList;
	}

	public void setModeloList(List<Modelo> modeloList) {
		this.modeloList = modeloList;
	}

	public void abrir() throws IOException{
		modelo = new Modelo();
		modelo.setMarca(new Marca());
		marcas = new ArrayList<SelectItem>();
		listarMarcas();
		FacesContext.getCurrentInstance().getExternalContext().redirect("cadastrarModelo.jsp");
	}
	
	public void salvar() {
		ModeloDAO modeloDAO = new ModeloDAO();
		modelo.setDescricao(modelo.getDescricao().toUpperCase());
		modeloDAO.save(modelo);
		modelo = new Modelo();
		modelo.setMarca(new Marca());
	}
	

	public void carregar() throws IOException {
		ModeloDAO modeloDAO = new ModeloDAO();
		modeloDAO.refresh(modelo);
		listarMarcas();
		FacesContext.getCurrentInstance().getExternalContext().redirect(
			"cadastrarModelo.jsp");
	}

	public void excluir() throws IOException {
		ModeloDAO modeloDAO = new ModeloDAO();
		modeloDAO.delete(modelo);
		this.setModeloList(modeloDAO.list());
	}

	public List<Modelo> listar() throws IOException {
		modelo = new Modelo();
		modelo.setMarca(new Marca());
		marcas = new ArrayList<SelectItem>();
		ModeloDAO modeloDAO = new ModeloDAO();
		this.setModeloList(modeloDAO.list());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"listarModelo.jsp");
		return this.getModeloList();
	}
	

	public  List<SelectItem> listarMarcas() throws IOException {
		MarcaDAO marcaDAO = new MarcaDAO();
		List<Marca> marcaList = new ArrayList<Marca>();
		marcaList = marcaDAO.list();
		for(Marca marca : marcaList){  
				marcas.add(new SelectItem(marca.getCodigo(), marca.getDescricao()));  
		    }  
		    return marcas;  
		}  
	
}
