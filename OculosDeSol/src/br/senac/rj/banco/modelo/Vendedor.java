package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Vendedor {
	int cpf;
	String nome;
	String comissao;
	
	
	public Vendedor() {
		this.nome = "";
		this.comissao = "";
	}
	
	Vendedor(int cpf) {
		this();
		this.cpf = cpf;
	}
	
	
	public int GetCpf() {
		return cpf;
	}
	
	public String GetNome() {
		return nome;
	}
	
	public String GetComissao() {
		return comissao;
	}
	
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setComissao(String comissao) {
		this.comissao = comissao;
	}
	
	
	public boolean cadastrarVendedor(int cpf, String nome, String comissao) {
		Connection conexao = null;
		
		try {
			conexao = Conexao.conectaBanco();
			String sql = "insert into vendedor set cpf=?, nome=?, comissao=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, cpf);
			ps.setString(2, nome);
			ps.setString(3, comissao);
			
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
	
	public boolean excluirProduto(int cpf) {
		Connection conexao = null;
		PreparedStatement st  = null;
		try {
			conexao = Conexao.conectaBanco();
			st = conexao.prepareStatement(
					"DELETE FROM vendedor WHERE cpf = ?");
			
			st.setInt(1, cpf);
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
	
	
	public boolean consultarVendedor(int cpf) {
		Connection conexao = null;
		
		try {
			conexao = Conexao.conectaBanco();
			String sql = "select * from vendedor where cpf=?";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, cpf);
			
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Vendedor não cadastrado.");
				return false;
			} else {
				while (rs.next()) {
					this.cpf = rs.getInt("cpf");
					this.nome = rs.getString("nome");
					this.comissao = rs.getString("comissao");
					}
				return true;
				}
			} catch (SQLException erro) {
				System.out.println("Erro ao consultar vendedor: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
		}
	}
	
	public boolean atualizarVendedor(int cpf, String nome, String comissao) {
		if (!consultarVendedor(cpf))
			return false;
		else {

			Connection conexao = null;
			
			try {

				conexao = Conexao.conectaBanco();
				String sql = "update vendedor set nome=?, comissao=? where cpf=?";
				
				PreparedStatement ps = conexao.prepareStatement(sql);

				ps.setString(1, nome);
				ps.setString(2, comissao);
				ps.setInt(3, cpf);
				
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