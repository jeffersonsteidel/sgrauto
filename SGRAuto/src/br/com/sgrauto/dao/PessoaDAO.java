package br.com.sgrauto.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.com.sgrauto.encripty.Enclipty;
import br.com.sgrauto.entity.Empresa;
import br.com.sgrauto.entity.Pessoa;

public class PessoaDAO {

	private Session session;

	public PessoaDAO() {
		session = HibernateUtility.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void save(Pessoa pessoa) {
		try {
			session.saveOrUpdate(pessoa);
			session.getTransaction().commit();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Item salvo com sucesso!", "Item salvo com sucesso!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "CPF ou RG já cadastrado!",
					"CPF ou RG já cadastrado!");
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

	public Pessoa refresh(Pessoa pessoa) {
		try {
			session.clear();
			session.refresh(pessoa);
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
		return pessoa;
	}

	public void delete(Pessoa pessoa) {
		try {
			session.clear();
			session.delete(pessoa);
			session.getTransaction().commit();
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
	public List<Pessoa> listByCliente() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Pessoa.class).add(
				Restrictions.like("indCliente", true)).addOrder(
				Order.asc("nome")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listByFuncionario() {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Pessoa.class).add(
				Restrictions.like("indFuncionario", true)).addOrder(
				Order.asc("nome")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listByFuncionariosAtivosByFilial(Empresa filial) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();
		return session.createCriteria(Pessoa.class).add(
				Restrictions.like("indFuncionario", true)).add(
				Restrictions.like("indDesat", false)).add(
				Restrictions.like("filial", filial)).addOrder(
				Order.asc("nome")).list();
	}

	public Pessoa auteticar(Pessoa pessoa) throws NoSuchAlgorithmException {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		String senha = Enclipty.criptografaSenha(pessoa.getSenha());
		session.beginTransaction();
		return (Pessoa) session.createCriteria(Pessoa.class).add(
				Restrictions.like("login", pessoa.getLogin().toUpperCase())).add(
				Restrictions.like("senha", senha)).add(
				Restrictions.like("indDesat", false)).uniqueResult();
	}
	
	public Pessoa buscarCliente(Pessoa pessoa) {
		Session session = HibernateUtility.getSessionFactory()
				.getCurrentSession();
		session.clear();
		session.beginTransaction();
		return (Pessoa) session.createCriteria(Pessoa.class).add(
				Restrictions.like("indCliente", true)).add(
				Restrictions.like("cpf", pessoa.getCpf()))
				.uniqueResult();
	}
}