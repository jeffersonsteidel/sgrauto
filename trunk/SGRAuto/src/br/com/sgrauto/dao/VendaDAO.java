package br.com.sgrauto.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.com.sgrauto.entity.Venda;

public class VendaDAO {

	private Session session;

	public VendaDAO() {
		session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void save(Venda venda) {
		try {
			session.clear();
			session.saveOrUpdate(venda);
			session.getTransaction().commit();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Item salvo com sucesso!", "Item salvo com sucesso!");
			FacesContext.getCurrentInstance().addMessage("", message);
			AutomovelDAO automovelDAO = new AutomovelDAO();
			automovelDAO.concluirVenda(venda.getAutomovel());
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

	public Venda refresh(Venda venda) {
		try {
			session.clear();
			session.refresh(venda);
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
		return venda;
	}

	public void delete(Venda venda) throws IOException {
		try {
			session.clear();
			session.delete(venda);
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
	public List<Venda> list() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Venda.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listByFilter(Venda venda, Date dataInicial, Date dataFinal) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		Criteria crit;
		crit = session.createCriteria(Venda.class);
		if(venda.getFilial().getCodigo() != null){
			crit.add(Restrictions.like("filial", venda.getFilial()));
		}
		if(venda.getFuncionario().getCodigo() != null){
			crit.add(Restrictions.like("funcionario", venda.getFuncionario()));
		}
		if(dataInicial != null && dataFinal != null){
			crit.add(Restrictions.between("data", dataInicial, dataFinal));
		}
		if(venda.getFinanceira().getCodigo() != null){
			crit.add(Restrictions.like("financeira", venda.getFinanceira()));
		}
		if(venda.getVendaVista() != null){
			crit.add(Restrictions.like("vendaVista", venda.getVendaVista()));
		}
		if(venda.getVendaPrazo() != null){
			crit.add(Restrictions.like("vendaPrazo", venda.getVendaPrazo()));
		}
		
		return crit.list();
	}
}