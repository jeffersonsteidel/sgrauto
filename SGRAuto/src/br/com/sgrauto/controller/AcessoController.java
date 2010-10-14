package br.com.sgrauto.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.sgrauto.dao.PessoaDAO;
import br.com.sgrauto.encripty.Encripty;
import br.com.sgrauto.entity.Pessoa;

public class AcessoController {

	private Pessoa pessoaLogada = new Pessoa();
	private Boolean administrador;

	private String senhaAtual;
	private String novaSenha;
	private String confirmaSenha;

	public Pessoa getPessoaLogada() {
		return pessoaLogada;
	}

	public void setPessoaLogada(Pessoa pessoaLogada) {
		this.pessoaLogada = pessoaLogada;
	}

	public Boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public void logar() throws IOException, NoSuchAlgorithmException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaLogada = pessoaDAO.auteticar(pessoaLogada);
		if (pessoaLogada != null && pessoaLogada.getCodigo() != null) {
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("usuarioLogado", pessoaLogada);
			if (pessoaLogada.getIndGerente()) {
				setAdministrador(true);
			} else {
				setAdministrador(false);
			}
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					"menus.jsp");
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Login ou Senha incorretos!",
					"Login ou Senha incorretos!");
			FacesContext.getCurrentInstance().addMessage("", message);
			pessoaLogada = new Pessoa();
		}
	}

	public void abrirTrocarSenha() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"trocarSenha.jsp");
	}

	public void trocarSenha() throws IOException, NoSuchAlgorithmException {
		pessoaLogada = (Pessoa) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		String senhaAtual = Encripty.criptografaSenha(this.getSenhaAtual());
		if (!pessoaLogada.getSenha().equals(senhaAtual)) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Senha Atual incorreta!",
					"Senha Atual incorreta!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} else if (!this.getNovaSenha().equals(this.getConfirmaSenha())) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Confirmar Nova Senha diferente!",
					"Confirmar Nova Senha diferente!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} else {
			PessoaDAO pessoaDAO = new PessoaDAO();
			String novaSenha = Encripty.criptografaSenha(this.getNovaSenha());
			pessoaLogada.setSenha(novaSenha);
			pessoaDAO.save(pessoaLogada);
		}
	}

	public void fechar() throws IOException {
		pessoaLogada = new Pessoa();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("usuarioLogado", null);
		FacesContext.getCurrentInstance().getExternalContext().redirect(
				"login.jsp");
	}

	public void isAutenticado() throws IOException {
		if (pessoaLogada.getCodigo() == null) {
			FacesContext.getCurrentInstance().getExternalContext().redirect(
					"login.jsp");
		}
	}
}
