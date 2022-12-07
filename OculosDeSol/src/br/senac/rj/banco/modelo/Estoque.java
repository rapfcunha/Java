package br.senac.rj.banco.modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

	public abstract class Estoque {
		private int cod;
		private String marca;
		private String modelo;
		private double valor;
		private int quantidade;
		

		public int getCod() {
			return cod;
		}

		public void setCod(int cod) {
			this.cod = cod;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public double getValor() {
			return valor;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		public int getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}

		public boolean cadastrarEstoque(int cod, String marca, String modelo, double valor, int quantidade) {
			// Define a conex�o
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "insert into estoque set cod=?, marca=?, modelo=?, valor=?, quantidade=?;";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da consulta
				ps.setInt(1, cod); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
				ps.setString(2, marca);
				ps.setString(3, modelo); // Substitui o terceiro par�metro da consulta pelo titular informado
				ps.setDouble(4, valor);
				ps.setInt(5, quantidade);
				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0) {
					System.out.println("Nao foi feito o cadastro!!");
					return false;
				}
				System.out.println("Cadastro realizado!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao cadastrar estoque: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
		
		public boolean consultarEstoque(int cod) {
			// Define a conex�o
			Connection conexao = null;
			try {
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "select * from estoque where cod=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da consulta
				ps.setInt(1, cod); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
				
				// Executa a consulta, resultando em um objeto da classe ResultSet
				ResultSet rs = ps.executeQuery();
				if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
					System.out.println("Conta nao cadastrada!");
					return false; // Conta n�o cadastrada
				} else {
					// Efetua a leitura do registro da tabela
					while (rs.next()) {
						this.cod = rs.getInt("cod");
						this.marca = rs.getString("marca");
						this.modelo = rs.getString("modelo");
						this.valor = rs.getDouble("valor");
						this.quantidade = rs.getInt("quantidade");
					}
					return true;
				}
			} catch (SQLException erro) {
				System.out.println("Erro ao consultar a conta: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
		
				
		
		public boolean atualizarEstoque(int numCod, String marca, String modelo, double valor, int quantidade) {
			if (!consultarEstoque(numCod))
				return false;
			else {
				// Define a conex�o
				Connection conexao = null;
				try {
					// Define a conex�o
					conexao = Conexao.conectaBanco();
					// Define a consulta
					String sql = "update estoque set valor=?, quantidade=? where cod=?";
					// Prepara a consulta
					PreparedStatement ps = conexao.prepareStatement(sql);
					// Define os par�metros da atualiza��o
					ps.setDouble(1, valor);
					ps.setInt(2,quantidade);
					ps.setInt(3, numCod); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
					int totalRegistrosAfetados = ps.executeUpdate();
					if (totalRegistrosAfetados == 0)
						System.out.println("Nao foi feita a atualiza��o!");
					else
						System.out.println("Atualizacao realizada!");
					return true;
				} catch (SQLException erro) {
					System.out.println("Erro ao atualizar a conta: " + erro.toString());
					return false;
				} finally {
					Conexao.fechaConexao(conexao);
				}
			}
		}
		
		
		public boolean excluirProduto(int id) {
			Connection conexao = null;
			PreparedStatement st  = null;
			try {
				conexao = Conexao.conectaBanco();
				st = conexao.prepareStatement(
						"DELETE FROM estoque WHERE cod = ?");
				
				st.setInt(1, cod);
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
		
	}

	
	
	
	
	
	
	
	
	
	
	
//	botaoConsultar.addActionListener(new ActionListener() {		
//		public void actionPerformed(ActionEvent e) {
//			try {
//				int cod = Integer.parseInt(jTextCod.getText());
//				double valor = (int) estoque.getValor();
//				int quantidade = estoque.getQuantidade();
//				String marca;
//				String modelo;
//				botaoGravar.setEnabled(true);
//				if (!estoque.consultarEstoque(cod)) {
//					marca = "";
//					modelo = "";
//				}
//					
//				else
//					marca = estoque.getMarca();
//					modelo = estoque.getModelo();
//					valor = estoque.getValor();
//					quantidade = estoque.getQuantidade();
//					
//				
//				jTextCod.setEnabled(false);
//				jTextMarca.setText(marca);
//				jTextModelo.setText(modelo);
//				jTextValor.setText(Integer.toString(quantidade));
//				jTextQtd.setText(Integer.toString(quantidade));
//				
//				jTextQtd.setEnabled(true);
//				botaoConsultar.setEnabled(false);
//				jTextMarca.setEnabled(true);
//				jTextModelo.setEnabled(true);
//				jTextValor.setEnabled(true);
//				jTextValor.requestFocus();
//			} catch (Exception erro) {
//				JOptionPane.showMessageDialog(janelaEstoque,
//						"Preencha o campo corretamente!!");
//			}
//		}
//	});
//	
//	
//	botaoLimpar.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            jTextCod.setText("");
//            jTextMarca.setText("");
//            jTextModelo.setText("");
//            jTextValor.setText("0");
//            jTextQtd.setText("0");
//            
//            estoque.setModelo(null);
//            estoque.setValor(0);
//            estoque.setQuantidade(0);
//
//            jTextCod.setEnabled(true);
//            jTextMarca.setEnabled(false);
//            jTextModelo.setEnabled(false);
//            jTextValor.setEnabled(false);
//            jTextQtd.setEnabled(false);
//
//            botaoConsultar.setEnabled(true);
//            botaoGravar.setEnabled(false);
//            jTextCod.requestFocus();
//        }
//    });
//	
//	
//	botaoDeletar.addActionListener(new ActionListener() {		
//	public void actionPerformed(ActionEvent e) {
//
//		try {
//			int resposta = JOptionPane.showConfirmDialog(janelaEstoque, "Deseja Remover?", "Confirmação",
//					JOptionPane.YES_NO_OPTION);
//			if (resposta == JOptionPane.YES_OPTION) {
//				int cod = Integer.parseInt(jTextCod.getText());
//				estoque.excluirProduto(cod);
//				jTextMarca.setText("");
//				jTextModelo.setText("");
//				jTextCod.setText("");
//				jTextCod.setEnabled(false);
//				jTextMarca.setEnabled(false);
//				jTextModelo.setEnabled(false);
//				jTextValor.setEnabled(false);
//				jTextQtd.setEnabled(false);
//				JOptionPane.showMessageDialog(janelaEstoque, "Funcionário removido!");
//				
//			}
//		}
//		catch(Exception erro) {
//			JOptionPane.showMessageDialog(null, "Impossível de remover funcionário não cadastrado.");
//		}
//
//	}});
//	
//	
//	botaoGravar.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            int resposta = JOptionPane.showConfirmDialog(janelaEstoque, "Deseja atualizar?", "Confirmação",
//                    JOptionPane.YES_NO_OPTION);
//            if (resposta == JOptionPane.YES_OPTION) {
//
//                int cod = Integer.parseInt(jTextCod.getText());
//                String marca = jTextMarca.getText();
//                String modelo = jTextModelo.getText();
//                double valor = Double.parseDouble(jTextValor.getText());
//                int qtd = Integer.parseInt(jTextQtd.getText());
//
//
//                if (marca.length() == 0) {
//                    JOptionPane.showMessageDialog(janelaEstoque, "Preencha o campo marca");
//                    jTextMarca.requestFocus();
//                } else {
//                    if (!estoque.consultarEstoque(cod)) {
//                        if (!estoque.cadastrarEstoque(cod, marca, modelo, valor, qtd))
//                            JOptionPane.showMessageDialog(janelaEstoque, "Erro na inclusão da marca!");
//                        else
//                            JOptionPane.showMessageDialog(janelaEstoque, "Inclusão realizada!");
//                    } else {
//                        if (!estoque.atualizarEstoque(cod, marca, modelo, valor, qtd))
//                            JOptionPane.showMessageDialog(janelaEstoque, "Erro na atualização da marca!");
//                        else
//                            JOptionPane.showMessageDialog(janelaEstoque, "Alteração realizada!");
//                    }
//
//                }
//
//            }
//        }
//    });
//	
//	
//	return janelaEstoque;
//	
//	
//	
//	
