package br.com.sgrauto.dao;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import br.com.sgrauto.entity.Estado;

public class EstadoDAO {

	private Session session;

	public EstadoDAO() {
		session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void save(Estado estado) {
		try {
			session.saveOrUpdate(estado);
			session.getTransaction().commit();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Item salvo com sucesso!", "Item salvo com sucesso!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Item já cadastrado!",
					"Item já cadastrado!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} catch (Exception e) {
			session.getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao comunicar com o servidor!",
					"Erro ao comunicar com o servidor!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} finally {
			session.close();
		}
	}

	public Estado refresh(Estado estado) {
		try {
			session.clear();
			session.refresh(estado);
		} catch (Exception e) {
			session.getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao comunicar com o servidor!",
					"Erro ao comunicar com o servidor!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} finally {
			session.close();
		}
		return estado;
	}

	public void delete(Estado estado) throws IOException {
		try {
			session.clear();
			session.delete(estado);
			session.getTransaction().commit();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Item excluido com sucesso!",
					"Item excluido com sucesso!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Item com dependências!",
					"Item com dependências!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} catch (Exception e) {
			session.getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao comunicar com o servidor!",
					"Erro ao comunicar com o servidor!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Estado> list() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Estado.class).list();
	}

}