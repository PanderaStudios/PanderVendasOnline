/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controle.ControleCliente;
import controle.ControleProduto;
import controle.ControlePedido;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author pande_000
 */
public class ThServidor extends Thread {

    /**
     * @param args the command line arguments
     */
    private ServerSocket[] s0 = new ServerSocket[ControleCliente.getNUMPORTAS()];

    private Socket[] sA = new Socket[ControleCliente.getNUMPORTAS()];

    private DefaultListModel<String> clienteON;
    private DefaultListModel<String> clienteOFF;

    // Controle de textos na janela
    private JTextField status;
    private JTextField txtNumClientes;

    private int numCliente;
    private String ipCliente;
    private String serverName;

    public ThServidor(DefaultListModel clienteON, DefaultListModel clienteOFF, JTextField status, JTextField txtNumClientes, String serverName) {
        this.status = status;
        this.clienteON = clienteON;
        this.clienteOFF = clienteOFF;
        this.txtNumClientes = txtNumClientes;
        this.serverName = serverName;
        for (int i = 0; i < ControleCliente.getNUMPORTAS(); i++) {
            try {
                s0[i] = new ServerSocket();
                sA[i] = new Socket();
            } catch (IOException ex) {
                Logger.getLogger(ThServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        preActions();
    }

    public void pararServ() {
        //      this.thcliente.interrupt();
        for (int i = 0; i < ControleCliente.getNUMPORTAS(); i++) {
            try {
                s0[i].close();
            } catch (IOException ex) {
                Logger.getLogger(ThServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void preActions() {

        numCliente = 0;
        ipCliente = "255.255.255.1";

        for (int i = 0; i < ControleCliente.getNUMPORTAS(); i++) {
            try {
                s0[i] = new ServerSocket(ControleCliente.getPorta(i));
            } catch (IOException ex) {
                Logger.getLogger(ThServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        // TODO code application logic here
        System.out.println("Classe Servidor Play - run()");
        status.setText("ONLINE");
        txtNumClientes.setText("" + numCliente);

        try {
            ControleCliente.carregar();
            status.setText("ONLINE");
            txtNumClientes.setText("" + numCliente);

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível Ler o Arquivo Cliente !!!",
                    "Servidor", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(ThServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ControleProduto.carregar();
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível Ler o Arquivo Produto !!!",
                    "Servidor", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(ThServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ControlePedido.carregar();
            ControlePedido.carregarItens();

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível Ler o Arquivo Pedido !!!",
                    "Servidor", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(ThServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {

            String ipClienteOld = null;

            try {
                for (int i = 0; i < ControleCliente.getNUMPORTAS(); i++) {
                    sA[i] = s0[i].accept();
                }

            ipClienteOld = ipCliente;

            new ThCliente(sA[0], serverName, ipCliente).start();
                new ThProduto(sA[1]).start();
                new ThPedido(sA[2]).start();


            for (int i = 0; i < ControleCliente.getNUMPORTAS(); i++) {
                    System.out.println("Cliente Conectado Porta <" + ControleCliente.getPorta(i) + "> " + sA[i].isConnected());
                }

            } catch (IOException ex) {
                Logger.getLogger(ThServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            if (!ipClienteOld.equals(ipCliente)) {
                clienteOFF.insertElementAt(numCliente + " - IP: " + sA[0].getInetAddress(), 0);

            }

            numCliente++;
            txtNumClientes.setText("" + numCliente);
            clienteON.insertElementAt(numCliente + " - IP: " + sA[0].getInetAddress(), 0);

            System.out.println("ip>" + sA[0].getInetAddress());
        }
    }
}
