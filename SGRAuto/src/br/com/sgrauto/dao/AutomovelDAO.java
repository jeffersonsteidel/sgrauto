package br.com.sgrauto.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.com.sgrauto.entity.Automovel;
import br.com.sgrauto.entity.AutomovelFotos;

public class AutomovelDAO {

	private Session session;

	public AutomovelDAO() {
		session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void save(Automovel automovel) {
		try {
			session.saveOrUpdate(automovel);
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

	public Automovel refresh(Automovel automovel) {
		try {
			session.clear();
			session.refresh(automovel);
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
		return automovel;
	}

	public void delete(Automovel automovel) {
		try {
			session.clear();
			session.refresh(automovel);
			session.delete(automovel);
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
	public List<Automovel> list() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Automovel.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Automovel> listByEstoque() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Automovel.class).add(
				Restrictions.like("indVendido", false)).list();
	}

	@SuppressWarnings("unchecked")
	public List<Automovel> listByVendidos() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Automovel.class).add(
				Restrictions.like("indVendido", true)).list();
	}

	public void saveFotos(AutomovelFotos automovelFotos) {
		try {
			session.save(automovelFotos);
			session.getTransaction().commit();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Foto salva com sucesso!", "Foto salva com sucesso!");
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

	@SuppressWarnings("unchecked")
	public List<AutomovelFotos> carregarFotos(Automovel automovel) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(AutomovelFotos.class).add(
				Restrictions.like("automovel", automovel)).addOrder(
				Order.asc("codigo")).list();
	}

	public void deleteFotos(AutomovelFotos automovelFotos) {
		try {
			session.clear();
			session.delete(automovelFotos);
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

	public Automovel automovelByEstoque(Automovel automovel) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.clear();
		session.beginTransaction();
		return (Automovel) session.createCriteria(Automovel.class).add(
				Restrictions.like("indVendido", false)).add(
				Restrictions.like("placa", automovel.getPlaca()))
				.uniqueResult();
	}
	
	public Automovel automovelByVendido(Automovel automovel) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.clear();
		session.beginTransaction();
		return (Automovel) session.createCriteria(Automovel.class).add(
				Restrictions.like("indVendido", true)).add(
				Restrictions.like("placa", automovel.getPlaca()))
				.uniqueResult();
	}

	public void concluirVenda(Automovel automovel) {
		try {
			Session session = HibernateUtility.getSessionFactory()
					.getCurrentSession();
			session.clear();
			session.beginTransaction();
			automovel.setDataSaida(new Date());
			automovel.setIndVendido(true);
			session.update(automovel);
			session.getTransaction().commit();
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
	public List<Automovel> listByFilter(Automovel automovel,Integer anoInicial,
			Integer anoFinal, BigDecimal precoVendaInicial, BigDecimal precoVendaFinal,
			Date dataEntradaInicial, Date dataEntradaFinal) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		Criteria crit;
		crit = session.createCriteria(Automovel.class);
		crit.add(Restrictions.like("indVendido", automovel.getIndVendido()));
		if(automovel.getFilial().getCodigo() != null){
			crit.add(Restrictions.like("filial", automovel.getFilial()));
		}
		if(anoInicial != null && anoFinal != null){
			crit.add(Restrictions.between("ano", anoInicial, anoFinal));
		}
		if(precoVendaInicial != null && precoVendaFinal != null){
			crit.add(Restrictions.between("valorDeVenda", precoVendaInicial, precoVendaFinal));
		}
		if(dataEntradaInicial != null && dataEntradaFinal != null){
			Date dataInicial = dataEntradaInicial; 
			Date dataFinal = dataEntradaFinal;
			crit.add(Restrictions.between("dataEntrada", dataInicial, dataFinal));
		}
		return crit.list();
	}
}