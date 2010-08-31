package br.com.sgrauto.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.com.sgrauto.entity.Cidade;
import br.com.sgrauto.entity.Estado;

public class CidadeDAO {

	private Session session;

	public CidadeDAO() {
		session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void save(Cidade cidade) {
		try {
			session.saveOrUpdate(cidade);
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

	public Cidade refresh(Cidade cidade) {
		try {
			session.clear();
			session.refresh(cidade);
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
		return cidade;
	}

	public void delete(Cidade cidade) {
		try {
			session.clear();
			session.refresh(cidade);
			session.delete(cidade);
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
	public List<Cidade> list() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Cidade.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> listByEstado(Estado estado) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Cidade.class).add(
				Restrictions.like("estado", estado)).addOrder(
				Order.asc("descricao")).list();
	}
}