package br.pucpcaldas.pedidos.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.pucpcaldas.pedidos.controle.DepositoDeProdutos;
import br.pucpcaldas.pedidos.dominio.Produto;

public class DepositoDeProdutosEmBancoDeDados implements DepositoDeProdutos {
	private Connection _conexao;

	public DepositoDeProdutosEmBancoDeDados(Connection conexao) {
		this._conexao = conexao;
	}

	public DepositoDeProdutosEmBancoDeDados() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			this._conexao = DriverManager.getConnection("jdbc:hsqldb:file:data/pedidos", "SA", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Produto produto) {
		String sql = "INSERT INTO PRODUTOS" + " (codigo, nome, preco)" + " VALUES(?, ?, ?)";

		try {
			PreparedStatement ins = _conexao.prepareStatement(sql);
			ins.setInt(1, produto.getCodigo());
			ins.setString(2, produto.getNome());
			ins.setDouble(3, produto.getPreco());
			ins.execute();
			ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void atualiza(Produto produto) {
		String sql = "UPDATE produtos" + " SET nome 	= ? " + "	,preco	 	= ? " + " WHERE codigo 	= ?";

		try {
			PreparedStatement upd = _conexao.prepareStatement(sql);
			upd.setString(1, produto.getNome());
			upd.setDouble(2, produto.getPreco());
			upd.setInt(3, produto.getCodigo());
			upd.execute();
			upd.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Produto produto) {
		String sql = "DELETE FROM produtos" + " WHERE codigo = ?";
		try {
			PreparedStatement del = _conexao.prepareStatement(sql);
			del.setInt(1, produto.getCodigo());
			del.execute();
			del.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Produto listaPor(int codigo) {
		String sql = "SELECT p.codigo, p.nome, p.preco" + " FROM produtos p " + " WHERE p.codigo = ?";

		Produto umProduto = Produto.Nulo;

		try {
			PreparedStatement sel = _conexao.prepareStatement(sql);
			sel.setInt(1, codigo);
			ResultSet rs = sel.executeQuery();
			while (rs.next()) {
				umProduto = new Produto(rs.getInt("codigo"), rs.getString("nome"), rs.getDouble("preco"));
			}
			sel.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return umProduto;
	}

	public void fecha() {
		try {
			Statement comando = _conexao.createStatement();
			comando.execute("SHUTDOWN");
			_conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
