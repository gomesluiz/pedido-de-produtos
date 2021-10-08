package br.pucpcaldas.pedidos.persistencia;

import org.hibernate.Session;

import br.pucpcaldas.pedidos.controle.DepositoDeProdutos;
import br.pucpcaldas.pedidos.dominio.Produto;

public class DepositoDeProdutosComHibernate implements DepositoDeProdutos {
	private Session session;

	public DepositoDeProdutosComHibernate(Session session) {
		this.session = session;
	}

	public DepositoDeProdutosComHibernate() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void adiciona(Produto produto) {
		session.beginTransaction();
		session.save(produto);
		session.getTransaction().commit();
	}

	public void atualiza(Produto produto) {
		session.beginTransaction();
		session.update(produto);
		session.getTransaction().commit();
	}

	public void remove(Produto produto) {
		session.beginTransaction();
		session.delete(produto);
		session.getTransaction().commit();
	}

	public Produto listaPor(int codigo) {
		Produto produto = session.find(Produto.class, codigo);
		if (produto == null) produto = Produto.Nulo;
		return produto;
	}

	public void fecha() {
		HibernateUtil.shutdown();
	}
}
