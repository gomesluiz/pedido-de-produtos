package br.pucpcaldas.pedidos.persistencia;

import org.hibernate.Session;

import br.pucpcaldas.pedidos.controle.DepositoDePedidos;
import br.pucpcaldas.pedidos.dominio.Pedido;

public class DepositoDePedidosComHibernate implements DepositoDePedidos {
	private Session session;

	public DepositoDePedidosComHibernate(Session session) {
		this.session = session;
	}

	public DepositoDePedidosComHibernate() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void adiciona(Pedido pedido) {
		session.beginTransaction();
		session.save(pedido);
		session.getTransaction().commit();
	}

	public void atualiza(Pedido pedido) {
		session.beginTransaction();
		session.update(pedido);
		session.getTransaction().commit();
	}

	public void remove(Pedido pedido) {
		session.beginTransaction();
		session.delete(pedido);
		session.getTransaction().commit();
	}

	public Pedido listaPor(int codigo) {
		Pedido pedido = session.find(Pedido.class, codigo);
		//if (pedido == null) pedido = Pedido.Nulo;
		return pedido;
	}

	public void fecha() {
		session.close();
	}
}
