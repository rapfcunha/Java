package br.senac.rj.banco.teste;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Cliente;
import br.senac.rj.banco.modelo.EstoqueAtivo;
import br.senac.rj.banco.modelo.Vendedor;

public class OculosDeSol {
	
	public static void apresentarMenu() {
		// Define a janela
		JFrame janelaPrincipal = new JFrame("Cadastro de conta"); // Janela Normal
		janelaPrincipal.setTitle("Óculos de Sol");
		janelaPrincipal.setResizable(true); // A janela n�o poder� ter o tamanho ajustado
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300); // Define tamanho da janela
		// Cria uma barra de menu para a janela principal
		JMenuBar menuBar = new JMenuBar();
		// Adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		// Define e adiciona menu na barra de menu
		JMenu menuAtualizar = new JMenu("Atualizar");
		menuBar.add(menuAtualizar);
		// Cria e adiciona um item simples para o menu
		JMenuItem menuEstoque = new JMenuItem("Estoque");
		JMenuItem menuVendedor = new JMenuItem("Vendedor");
		JMenuItem menuCliente = new JMenuItem("Cliente");
		menuAtualizar.add(menuEstoque);
		menuAtualizar.add(menuVendedor);
		menuAtualizar.add(menuCliente);
		// Criar a janela de atualiza��o da Estoque
		JFrame janelaEstoque = criarJanelaEstoque();
		JFrame janelaVendedor = criarJanelaVendedor();
		JFrame janelaCliente = criarJanelaCliente();
		// Adiciona a��o para o item do menu
		menuEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaEstoque.setVisible(true);
			}
		});
		
		menuVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaVendedor.setVisible(true);
			}
		});
		menuCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaCliente.setVisible(true);
			}
		});
		
		janelaPrincipal.setVisible(true);
	}

	
	// ESTOQUE
	private static JFrame criarJanelaEstoque() {
		// Define a janela
		JFrame janelaEstoque = new JFrame("Atualizacaoo do Estoque"); // Janela Normal
		janelaEstoque.setResizable(true); // A janela n�o poder� ter o tamanho ajustado
		janelaEstoque.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaEstoque.setSize(600, 400); // Define tamanho da janela	
		// Define o layout da janela
		Container caixa = janelaEstoque.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelCod = new JLabel("Cod: ");
		JLabel labelMarca = new JLabel("Marca: ");
		JLabel labelModelo = new JLabel("Modelo: ");		
		JLabel labelValor = new JLabel("Valor: ");
		JLabel labelQtd = new JLabel("Quantidade: ");
		
		// Posiciona os labels na janela
		labelCod.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelMarca.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelModelo.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		labelValor.setBounds(50, 160, 100, 20); // coluna, linha, largura, tamanho
		labelQtd.setBounds(50, 200, 100, 20); // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextCod = new JTextField();
		JTextField jTextMarca = new JTextField();
		JTextField jTextModelo = new JTextField();
		JTextField jTextValor = new JTextField();
		JTextField jTextQtd = new JTextField();
		
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextCod.setEnabled(true);
		jTextMarca.setEnabled(false);
		jTextModelo.setEnabled(false);
		jTextValor.setEnabled(false);
		jTextQtd.setEnabled(false);
		
		// Posiciona os input box
		jTextCod.setBounds(180, 40, 50, 20);
		jTextMarca.setBounds(180, 80, 150, 20);
		jTextModelo.setBounds(180, 120, 150, 20);
		jTextValor.setBounds(180, 160, 50, 20);
		jTextQtd.setBounds(180, 200, 50, 20);
		
		// Adiciona os r�tulos e os input box na janela
		janelaEstoque.add(labelCod);
		janelaEstoque.add(labelMarca);
		janelaEstoque.add(labelModelo);
		janelaEstoque.add(labelValor);
		janelaEstoque.add(labelQtd);
		
		janelaEstoque.add(jTextCod);
		janelaEstoque.add(jTextMarca);
		janelaEstoque.add(jTextModelo);
		janelaEstoque.add(jTextValor);
		janelaEstoque.add(jTextQtd);
	
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janelaEstoque.add(botaoConsultar);
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(160, 250, 100, 20);
		janelaEstoque.add(botaoDeletar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 250, 100, 20);
		botaoGravar.setEnabled(false);
		janelaEstoque.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(270, 250, 100, 20);
		janelaEstoque.add(botaoLimpar);
		
		// Define objeto conta para pesquisar no banco de dados
		EstoqueAtivo estoque = new EstoqueAtivo();
		
		botaoConsultar.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				try {
					int cod = Integer.parseInt(jTextCod.getText());
					double valor = (int) estoque.getValor();
					int quantidade = estoque.getQuantidade();
					String marca;
					String modelo;
					botaoGravar.setEnabled(true);
					if (!estoque.consultarEstoque(cod)) {
						marca = "";
						modelo = "";
					}
						
					else
						marca = estoque.getMarca();
						modelo = estoque.getModelo();
						valor = estoque.getValor();
						quantidade = estoque.getQuantidade();
						
					
					jTextCod.setEnabled(false);
					jTextMarca.setText(marca);
					jTextModelo.setText(modelo);
					jTextValor.setText(Double.toString(valor));
					jTextQtd.setText(Integer.toString(quantidade));
					
					jTextQtd.setEnabled(true);
					botaoConsultar.setEnabled(false);
					jTextMarca.setEnabled(true);
					jTextModelo.setEnabled(true);
					jTextValor.setEnabled(true);
					jTextValor.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaEstoque,
							"Preencha o campo corretamente!!");
				}
			}
		});
		
		
		botaoLimpar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            jTextCod.setText("");
	            jTextMarca.setText("");
	            jTextModelo.setText("");
	            jTextValor.setText("0");
	            jTextQtd.setText("0");
	            
	            estoque.setModelo(null);
	            estoque.setValor(0);
	            estoque.setQuantidade(0);

	            jTextCod.setEnabled(true);
	            jTextMarca.setEnabled(false);
	            jTextModelo.setEnabled(false);
	            jTextValor.setEnabled(false);
	            jTextQtd.setEnabled(false);

	            botaoConsultar.setEnabled(true);
	            botaoGravar.setEnabled(false);
	            jTextCod.requestFocus();
	        }
	    });
		
		
		botaoDeletar.addActionListener(new ActionListener() {		
		public void actionPerformed(ActionEvent e) {

			try {
				int resposta = JOptionPane.showConfirmDialog(janelaEstoque, "Deseja Remover?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int cod = Integer.parseInt(jTextCod.getText());
					estoque.excluirProduto(cod);
					jTextMarca.setText("");
					jTextModelo.setText("");
					jTextCod.setText("");
					jTextCod.setEnabled(false);
					jTextMarca.setEnabled(false);
					jTextModelo.setEnabled(false);
					jTextValor.setEnabled(false);
					jTextQtd.setEnabled(false);
					JOptionPane.showMessageDialog(janelaEstoque, "Funcionário removido!");
					
				}
			}
			catch(Exception erro) {
				JOptionPane.showMessageDialog(null, "Impossível de remover funcionário não cadastrado.");
			}

		}});
		
		
		botaoGravar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int resposta = JOptionPane.showConfirmDialog(janelaEstoque, "Deseja atualizar?", "Confirmação",
	                    JOptionPane.YES_NO_OPTION);
	            if (resposta == JOptionPane.YES_OPTION) {

	                int cod = Integer.parseInt(jTextCod.getText());
	                String marca = jTextMarca.getText();
	                String modelo = jTextModelo.getText();
	                double valor = Double.parseDouble(jTextValor.getText());
	                int qtd = Integer.parseInt(jTextQtd.getText());


	                if (marca.length() == 0) {
	                    JOptionPane.showMessageDialog(janelaEstoque, "Preencha o campo marca");
	                    jTextMarca.requestFocus();
	                } else {
	                    if (!estoque.consultarEstoque(cod)) {
	                        if (!estoque.cadastrarEstoque(cod, marca, modelo, valor, qtd))
	                            JOptionPane.showMessageDialog(janelaEstoque, "Erro na inclusão da marca!");
	                        else
	                            JOptionPane.showMessageDialog(janelaEstoque, "Inclusão realizada!");
	                    } else {
	                        if (!estoque.atualizarEstoque(cod, marca, modelo, valor, qtd))
	                            JOptionPane.showMessageDialog(janelaEstoque, "Erro na atualização da marca!");
	                        else
	                            JOptionPane.showMessageDialog(janelaEstoque, "Alteração realizada!");
	                    }

	                }

	            }
	        }
	    });
		
		
		return janelaEstoque;
//		
		
	}
	
	//VENDEDOR
	
	private static JFrame criarJanelaVendedor() {

		JFrame janelaVendedor = new JFrame("Atualização do Vendedor"); // Janela Normal
		janelaVendedor.setResizable(true); // A janela n�o poder� ter o tamanho ajustado
		janelaVendedor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaVendedor.setSize(600, 400); // Define tamanho da janela	
		// Define o layout da janela
		Container caixa = janelaVendedor.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelCpf = new JLabel("CPF: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelComissao = new JLabel("Comissão: ");		

		
		// Posiciona os labels na janela
		labelCpf.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelComissao.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextCpf = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextComissao = new JTextField();


		jTextCpf.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextComissao.setEnabled(false);

		jTextCpf.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextComissao.setBounds(180, 120, 150, 20);

		janelaVendedor.add(labelCpf);
		janelaVendedor.add(labelNome);
		janelaVendedor.add(labelComissao);

		janelaVendedor.add(jTextCpf);
		janelaVendedor.add(jTextNome);
		janelaVendedor.add(jTextComissao);


		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janelaVendedor.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 250, 100, 20);
		botaoGravar.setEnabled(false);
		janelaVendedor.add(botaoGravar);
		
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(160, 250, 100, 20);
		janelaVendedor.add(botaoDeletar);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 250, 100, 20);
		janelaVendedor.add(botaoLimpar);
		

		var vendedor = new Vendedor();
		
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cpf = Integer.parseInt(jTextCpf.getText());

					botaoGravar.setEnabled(true);
					String nome;
					String comissao;
					if (!vendedor.consultarVendedor(cpf)) {
						nome = "";
						comissao = "";
					}
						
					else
					nome = vendedor.GetNome();
					comissao = vendedor.GetComissao();	
						
					jTextNome.setText(nome);
					jTextComissao.setText(comissao);
					
					botaoConsultar.setEnabled(true);
					jTextComissao.setEnabled(true);
					jTextNome.setEnabled(true);
					jTextNome.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaVendedor,
							"Preencha os campos corretamente!!");
				}
			}
		});
		
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextCpf.setText("");
				jTextNome.setText("");
				jTextComissao.setText("");
				
				jTextCpf.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextComissao.setEnabled(false);
				
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextCpf.requestFocus();				
			}
		});
		
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaVendedor, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					
					int cpf = Integer.parseInt(jTextCpf.getText());
					String nome = jTextNome.getText();
					String comissao = jTextComissao.getText();
					
					
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaVendedor, "Preencha o campo marca");
						jTextNome.requestFocus();
					} else {
						if (!vendedor.consultarVendedor(cpf)) {
							if (!vendedor.cadastrarVendedor(cpf, nome, comissao))
								JOptionPane.showMessageDialog(janelaVendedor, "Erro na inclusão da marca!");
							else
								JOptionPane.showMessageDialog(janelaVendedor, "Inclusão realizada!");
						} else {
							if (!vendedor.atualizarVendedor(cpf, nome, comissao))
								JOptionPane.showMessageDialog(janelaVendedor, "Erro na atualização da marca!");
							else
								JOptionPane.showMessageDialog(janelaVendedor, "Alteração realizada!");
						}

					}

				}
			}
		});

		
		botaoDeletar.addActionListener(new ActionListener() {		
		public void actionPerformed(ActionEvent e) {
	
			try {
				int resposta = JOptionPane.showConfirmDialog(janelaVendedor, "Deseja Remover?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(jTextCpf.getText());
					jTextNome.setText("");
					jTextComissao.setText("");
					vendedor.excluirProduto(id);
					
					jTextCpf.setEnabled(false);
					jTextNome.setEnabled(false);
					jTextComissao.setEnabled(false);
					JOptionPane.showMessageDialog(janelaVendedor, "Funcionário removido!");
					
				}
			}
			catch(Exception erro) {
				JOptionPane.showMessageDialog(null, "Impossível de remover funcionário não cadastrado.");
			}
	
		}});
		
		return janelaVendedor;
		
	}
	
	
	// CLIENTE
	private static JFrame criarJanelaCliente() {

		JFrame janelaCliente = new JFrame("Atualização do Vendedor"); // Janela Normal
		janelaCliente.setResizable(true); // A janela n�o poder� ter o tamanho ajustado
		janelaCliente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaCliente.setSize(600, 400); // Define tamanho da janela	
		// Define o layout da janela
		Container caixa = janelaCliente.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelSobrenome = new JLabel("Sobrenome: ");		

		
		// Posiciona os labels na janela
		labelId.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelSobrenome.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextSobrenome = new JTextField();


		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextSobrenome.setEnabled(false);

		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextSobrenome.setBounds(180, 120, 150, 20);

		janelaCliente.add(labelId);
		janelaCliente.add(labelNome);
		janelaCliente.add(labelSobrenome);

		janelaCliente.add(jTextId);
		janelaCliente.add(jTextNome);
		janelaCliente.add(jTextSobrenome);


		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janelaCliente.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 250, 100, 20);
		botaoGravar.setEnabled(false);
		janelaCliente.add(botaoGravar);
		
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(160, 250, 100, 20);
		janelaCliente.add(botaoDeletar);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 250, 100, 20);
		janelaCliente.add(botaoLimpar);
		

		var cliente = new Cliente();
		
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = Integer.parseInt(jTextId.getText());

					botaoGravar.setEnabled(true);
					String nome;
					String sobrenome;
					if (!cliente.consultarCliente(id)) {
						nome = "";
						sobrenome = "";
					}
						
					else
					nome = cliente.getNome();
					sobrenome = cliente.getSobrenome();
						
					jTextNome.setText(nome);
					jTextSobrenome.setText(sobrenome);
					
					botaoConsultar.setEnabled(true);
					jTextSobrenome.setEnabled(true);
					jTextNome.setEnabled(true);
					jTextNome.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaCliente,
							"Preencha os campos corretamente!!");
				}
			}
		});
		
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextId.setText("");
				jTextNome.setText("");
				jTextSobrenome.setText("");
				
				jTextId.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextSobrenome.setEnabled(false);
				
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextId.requestFocus();				
			}
		});
		
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaCliente, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					
					int cpf = Integer.parseInt(jTextId.getText());
					String nome = jTextNome.getText();
					String sobrenome = jTextSobrenome.getText();
					
					
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaCliente, "Preencha o campo marca");
						jTextNome.requestFocus();
					} else {
						if (!cliente.consultarCliente(cpf)) {
							if (!cliente.cadastrarCliente(cpf, nome, sobrenome))
								JOptionPane.showMessageDialog(janelaCliente, "Erro na inclusão da marca!");
							else
								JOptionPane.showMessageDialog(janelaCliente, "Inclusão realizada!");
						} else {
							if (!cliente.atualizarVendedor(cpf, nome, sobrenome))
								JOptionPane.showMessageDialog(janelaCliente, "Erro na atualização da marca!");
							else
								JOptionPane.showMessageDialog(janelaCliente, "Alteração realizada!");
						}

					}

				}
			}
		});

		
		botaoDeletar.addActionListener(new ActionListener() {		
		public void actionPerformed(ActionEvent e) {
	
			try {
				int resposta = JOptionPane.showConfirmDialog(janelaCliente, "Deseja Remover?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int id = Integer.parseInt(jTextId.getText());
					jTextNome.setText("");
					jTextSobrenome.setText("");
					cliente.excluirCliente(id);
					
					jTextId.setEnabled(false);
					jTextNome.setEnabled(false);
					jTextSobrenome.setEnabled(false);
					JOptionPane.showMessageDialog(janelaCliente, "Funcionário removido!");
					
				}
			}
			catch(Exception erro) {
				JOptionPane.showMessageDialog(null, "Impossível de remover funcionário não cadastrado.");
			}
	
		}});
		
		return janelaCliente;
		
	}
	



public static void main(String[] args) {
	apresentarMenu();
}
}