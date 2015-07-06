package criptografia;



import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

public class ChatServer2 {

	// classe servidor

	// criando uma lista de escritores
	List<PrintWriter> escritores = new ArrayList<>();

	// metodo construtor
	public ChatServer2() {
		ServerSocket server;

		try {
			server = new ServerSocket(5000);
			while (true) {
				Socket socket = server.accept();
				new Thread(new EscutaCliente(socket)).start();
				PrintWriter p = new PrintWriter(socket.getOutputStream());
				escritores.add(p);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "ERRO DE CONEX�O", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// metodo para enviarparatodos
	private void encaminharParaTodos(String texto) {
		for (PrintWriter w : escritores) {
			try {
				w.println(texto);
				w.flush();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "ERRO DE ENVIO DE DADOS",
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// classse escutaCliente
	private class EscutaCliente implements Runnable {
		Scanner leitor;

		public EscutaCliente(Socket socket) throws IOException {
			try {
				leitor = new Scanner(socket.getInputStream());
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null,
						"ERRO DE ESCUTA DO CLIENTE", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		public void run() {
			try {
				String texto;
				while ((texto = leitor.nextLine()) != null) {

					// parte de criptografia

					byte[] message = texto.getBytes();
					KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
					SecretKey desKey = keygenerator.generateKey();
					Cipher desCipher = Cipher
							.getInstance("DES/ECB/PKCS5Padding");

					// iniciando a criptografia
					desCipher.init(Cipher.ENCRYPT_MODE, desKey);

					// encriptado a mensagem
					byte[] encryptedMessage = desCipher.doFinal(message);
					// System.out.println( "Critografado :" + new String(
					// encryptedMessage)); // testa se esta criptografando

					// codificando
					String encode64 = Base64.getEncoder().encodeToString(
							encryptedMessage);
					// System.out.println(" Codificado base 64 :  "+ encode64);
					// // testa se esta codificando

					// decodificando
					byte[] dec64 = Base64.getDecoder().decode(encode64);
					// System.out.println("descodificado  base 64 : " + new
					// String(dec64)); // testa se esta decodificando

					// descriptografando
					desCipher.init(Cipher.DECRYPT_MODE, desKey);
					byte[] msgOriginal = desCipher.doFinal(encryptedMessage);
					// System.out.println( "Descriptografado :" + new String(
					// msgOriginal)); // testa se esta descriptografando

					// enviado para o servidor
					// textoRecebido.append(new String( msgOriginal) + "\n");

					System.out.println((new String(encryptedMessage))); // testa
																		// se
																		// esta
																		// criptografando
					System.out.println((encode64)); // testa se esta codificando
					System.out.println((new String(dec64))); // testa se esta
																// decodificando
					System.out.println((new String(msgOriginal))); // testa se
																	// esta
																	// descriptografando

					encaminharParaTodos(new String(encryptedMessage));
					encaminharParaTodos(encode64);
					encaminharParaTodos(new String(dec64));
					encaminharParaTodos(new String(msgOriginal));

					// System.out.println(new String( msgOriginal));
					// encaminharParaTodos(new String( msgOriginal));
				}
			} catch (Exception ex) {

				JOptionPane.showMessageDialog(null, "ERRO DE LEITURA", "ERRO",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		new ChatServer2();
	}
}