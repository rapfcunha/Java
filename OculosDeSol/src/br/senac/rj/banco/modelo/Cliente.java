package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Cliente {

	private int id;
	private String nome;
	private String sobrenome;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public boolean cadastrarCliente(int id, String nome, String sobrenome) {
		Connection conexao = null;
		
		try {
			conexao = Conexao.conectaBanco();
			String sql = "insert into cliente set id=?, nome=?, sobrenome=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setString(2, nome);
			ps.setString(3, sobrenome);
			
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito nenhum cadastro.");
				return false;
			}
			System.out.println("Cadastro realizado com sucesso.");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar vendedor: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	public boolean excluirCliente(int id) {
		Connection conexao = null;
		PreparedStatement st  = null;
		try {
			conexao = Conexao.conectaBanco();
			st = conexao.prepareStatement(
					"DELETE FROM cliente WHERE id = ?");
			
			st.setInt(1, id);
			int totalRegistrosAfetados = st.executeUpdate();
			if (totalRegistrosAfetados == 0)
				System.out.println("Não foi possivel deletar!");
			else
				System.out.println("Deletado com sucesso!");
			return true;
			
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a produto: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
		
	}
	
	
	public boolean consultarCliente(int id) {
		Connection conexao = null;
		
		try {
			conexao = Conexao.conectaBanco();
			String sql = "select * from cliente where id=?";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Cliente não cadastrado.");
				return false;
			} else {
				while (rs.next()) {
					this.id = rs.getInt("id");
					this.nome = rs.getString("nome");
					this.sobrenome = rs.getString("sobrenome");
					}
				return true;
				}
			} catch (SQLException erro) {
				System.out.println("Erro ao consultar cliente: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
		}
	}
	
	public boolean atualizarVendedor(int id, String nome, String sobrenome) {
		if (!consultarCliente(id))
			return false;
		else {

			Connection conexao = null;
			
			try {

				conexao = Conexao.conectaBanco();
				String sql = "update cliente set nome=?, sobrenome=? where id=?";
				
				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setString(1, nome);
				ps.setString(2, sobrenome);
				ps.setInt(3, id);
				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Nao foi feita atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar a conta do vendedor: " + erro.toString());
					return false;
				} finally {
					Conexao.fechaConexao(conexao);
				}
			}
		}
	}
	
