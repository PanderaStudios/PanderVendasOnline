package criptografia;



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatCliente2 extends JFrame {

	// atributos
	private JTextField textoParaEnviar;
	private PrintWriter escritor;
	private Socket socket;
	private String nome;
	private JTextArea textoRecebido;
	Scanner leitor;

	// class que escuta o servidor implementada para um thread
	private class EscutaServidor implements Runnable {

		public void run() {
			try {
				String texto;
				while ((texto = leitor.nextLine()) != null) {
					// parte de criptografia

					textoRecebido.append(texto + "\n");

				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro de Leitura", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// metodo construtor
	public ChatCliente2(String nome) {
		super("Chat:" + nome);
		setResizable(false);
		this.nome = nome;

		// configura��o da Janela

		// font
		Font fonte = new Font("Serif", Font.PLAIN, 26);

		textoParaEnviar = new JTextField();
		textoParaEnviar.setFont(fonte);
		JButton botao = new JButton("Enviar");
		botao.setFont(fonte);
		botao.addActionListener(new EnviarListener()); // chamanda do evento do
														// botao.

		// JPanel
		Container envio = new JPanel(); // cria um container
		envio.setLayout(new BorderLayout()); // configura um layout
		envio.add(BorderLayout.CENTER, textoParaEnviar); // adiciona ao
															// container
		envio.add(BorderLayout.EAST, botao);// adiciona ao container
		getContentPane().add(BorderLayout.SOUTH, envio);

		textoRecebido = new JTextArea();
		textoRecebido.setEditable(false);
		textoRecebido.setFont(fonte);
		JScrollPane scroll = new JScrollPane(textoRecebido); // cria um
																// scroolpane
		getContentPane().add(BorderLayout.CENTER, scroll);
		getContentPane().add(BorderLayout.SOUTH, envio);

		// chamada da fun��o configurarRede
		configurarRede();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);

	}

	// classe para evento EnviarListener
	private class EnviarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			escritor.println(nome + ":" + textoParaEnviar.getText());
			escritor.flush();// garante que a informa��o chegou
			textoParaEnviar.setText("");
			textoParaEnviar.requestFocus();
		}
	}

	// metodo configurarRede
	private void configurarRede() {

		try {
			socket = new Socket("localhost", 5000);
			escritor = new PrintWriter(socket.getOutputStream());
			leitor = new Scanner(socket.getInputStream());
			new Thread(new EscutaServidor()).start();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "ERRO DE CONEX�O", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// metodo main
	public static void main(String[] args) {

		new ChatCliente2(JOptionPane.showInputDialog("Digite o NiCK"));
		new ChatCliente2(JOptionPane.showInputDialog("Digite o NiCK"));
	}

}
