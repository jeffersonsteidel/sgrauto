package br.com.sgrauto.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.com.sgrauto.entity.Marca;
import br.com.sgrauto.entity.Modelo;

public class ModeloDAO {

	private Session session;

	public ModeloDAO() {
		session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void save(Modelo modelo) {
		try {
			session.saveOrUpdate(modelo);
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

	public Modelo refresh(Modelo modelo) {
		try {
			session.clear();
			session.refresh(modelo);
		} catch (Exception e) {
			session.getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao comunicar com o servidor!",
					"Erro ao comunicar com o servidor!");
			FacesContext.getCurrentInstance().addMessage("erroMarker", message);
		} finally {
			session.close();
		}
		return modelo;
	}

	public void delete(Modelo modelo) {
		try {
			session.clear();
			session.refresh(modelo);
			session.delete(modelo);
			session.getTransaction().commit();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Item excluido com sucesso!", "Item excluido com sucesso!");
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
	public List<Modelo> list() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Modelo.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Modelo> listByMarca(Marca marca) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Modelo.class).add(
				Restrictions.like("marca", marca)).addOrder(
				Order.asc("descricao")).list();
	}
}