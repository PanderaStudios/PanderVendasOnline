package telas;

import comum.controle.ControleComunicacao;
import controle.ControlePedido;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;

public class JFPrincipalRemoto extends JFPrincipal {

    private static String ipServidor;
    private final int NUMPORTAS = 3;
    private String serverName;
//    protected ControlePedido cPedido
//            = new ControlePedido();

    ControleComunicacao c1c[] = new ControleComunicacao[NUMPORTAS];

    int portas[] = new int[NUMPORTAS];
    Socket s[] = new Socket[NUMPORTAS];

    /**
     *
     */
    public JFPrincipalRemoto() {
        super(new ControlePedido());

        // Prepara array com as Portas
        for (int i = 0; i < NUMPORTAS; i++) {
            portas[i] = 5050 + i;
        }

        preActions();
        serverName = "";
    }

    private void carregaTabelas() {
        ArrayList<Cliente> lista = new ArrayList<>();
        lista.addAll(obterTodos());
        System.out.println("CLIENTE - Vetor Clientes vazio? -> " + lista.isEmpty());

        ArrayList<Produto> lista1 = new ArrayList<>();
        lista1.addAll(obterTodosProdutos());
        System.out.println("CLIENTE - Vetor Produtos vazio? -> " + lista1.isEmpty());

        ArrayList<Pedido> lista2 = new ArrayList<>();
        lista2.addAll(obterTodosPedidos());
        System.out.println("CLIENTE - Vetor Pedidos vazio? -> " + lista2.isEmpty());

        ArrayList<ItemPedido> lista3 = new ArrayList<>();
        lista3.addAll(obterTodosItens());
        System.out.println("CLIENTE - Vetor Itens vazio? -> " + lista3.isEmpty());
    }

    @Override
    protected void helloServer() {
        try {
            c1c[0].enviarTexto("Hello");
            System.out.println(c1c[0].receberTexto());
            System.out.println("Cliente - HELLO SERVER!!!");
            serverName = c1c[0].receberTexto();
            super.setServidorNome(serverName);
            System.out.println("Cliente PRemota - ServerName Recebido --> " + serverName);

        } catch (NullPointerException | IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void remover(String cpf) {
        try {
            c1c[0].enviarTexto("R");
            System.out.println(c1c[0].receberTexto());
            System.out.println("Cliente - remover clientes");
            c1c[0].enviarTexto(cpf);
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void removerProduto(String cpf) {
        try {
            c1c[1].enviarTexto("R");
            System.out.println(c1c[1].receberTexto());
            System.out.println("Cliente - remover produtos");
            c1c[1].enviarTexto(cpf);
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void removerPedido(String cpf) {
        try {
            c1c[2].enviarTexto("R");
            System.out.println(c1c[2].receberTexto());
            System.out.println("Cliente - remover pedidos");
            c1c[2].enviarTexto(cpf);
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void removerItemPedido(String cpf) {
        try {
            c1c[2].enviarTexto("iR");
            System.out.println(c1c[2].receberTexto());
            System.out.println("Cliente - remover item pedidos");
            c1c[2].enviarTexto(cpf);
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void persistir(Cliente c, String cpf) {
        JDDadosCliente dadosC = new JDDadosCliente(this, true);
        dadosC.setDados(c, cpf);
        dadosC.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dadosC.sucesso) {
            try {
                c1c[0].enviarTexto("P");
                System.out.println(c1c[0].receberTexto());
                System.out.println("Cliente - persistir clientes");
                c1c[0].enviarObjeto(dadosC.getDados());
            } catch (NullPointerException | IOException | ClassNotFoundException ex) {
                Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void persistirProduto(Produto p, String cpf) {
        JDDadosProduto dadosP = new JDDadosProduto(this, true);
        dadosP.setDados(p, cpf);
        dadosP.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dadosP.sucesso) {
            try {
                c1c[1].enviarTexto("P");
                System.out.println(c1c[1].receberTexto());
                System.out.println("Cliente - persistir produtos");
                c1c[1].enviarObjeto(dadosP.getDados());
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void persistirPedido(Pedido ped, String codPed, String codCli, String nomeCli, String valorCli, DefaultTableModel itensPed) {
        JDDadosPedidos dadosPed = new JDDadosPedidos(this, true, cPedido, itensPed, obterPedido(codCli));
        dadosPed.setDados(ped, codPed, codCli, nomeCli, valorCli, itensPed);
        dadosPed.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dadosPed.sucesso) {
            try {
                c1c[2].enviarTexto("P");
                System.out.println(c1c[2].receberTexto());
                System.out.println("Cliente - persistir pedidos");
                c1c[2].enviarObjeto(dadosPed.getDados());
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void persistirItemPedido(ItemPedido itemped, String codProd, String nomeProd, String qtdCompr, String valorProd) {
        JDDadosItensPed dadosItemPed = new JDDadosItensPed(this, true);
        dadosItemPed.setDados(itemped, codProd, nomeProd, qtdCompr, valorProd);
        dadosItemPed.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dadosItemPed.sucesso) {
            try {
                c1c[2].enviarTexto("iP");
                System.out.println(c1c[2].receberTexto());
                System.out.println("Cliente - persistir itens pedidos");
                c1c[2].enviarObjeto(dadosItemPed.getDados());
            } catch (IOException ex) {
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected Cliente obter(String cpf) {
        try {
            c1c[0].enviarTexto("O");
            System.out.println(c1c[0].receberTexto());
            System.out.println("Cliente - obter cliente");
            c1c[0].enviarTexto(cpf);
            return (Cliente) c1c[0].receberObjeto();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    protected Produto obterProduto(String cpf) {
        try {
            c1c[1].enviarTexto("O");
            System.out.println(c1c[1].receberTexto());
            System.out.println("Cliente - obter produto");
            c1c[1].enviarTexto(cpf);
            return (Produto) c1c[1].receberObjeto();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    protected Pedido obterPedido(String cod) {
        try {
            c1c[2].enviarTexto("O");
            System.out.println(c1c[2].receberTexto());
            System.out.println("Cliente - obter pedido");
            c1c[2].enviarTexto(cod);
            return (Pedido) c1c[2].receberObjeto();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    protected ItemPedido obterItemPedido(String cod) {
        try {
            c1c[2].enviarTexto("iO");
            System.out.println(c1c[2].receberTexto());
            System.out.println("Cliente - obter item pedido");
            c1c[2].enviarTexto(cod);
            return (ItemPedido) c1c[2].receberObjeto();
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    protected ArrayList<Cliente> obterTodos() {
        try {
            c1c[0].enviarTexto("T");
            System.out.println(c1c[0].receberTexto());
            System.out.println("Cliente - obter todos clientes");
            return (ArrayList<Cliente>) c1c[0].receberObjeto();
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    protected ArrayList<Produto> obterTodosProdutos() {
        try {
            c1c[1].enviarTexto("T");
            System.out.println(c1c[1].receberTexto());
            System.out.println("Cliente - obter todos produtos");
            return (ArrayList<Produto>) c1c[1].receberObjeto();
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    protected ArrayList<Pedido> obterTodosPedidos() {
        try {
            c1c[2].enviarTexto("T");
            System.out.println(c1c[2].receberTexto());
            System.out.println("Cliente - obter todos pedidos");
            return (ArrayList<Pedido>) c1c[2].receberObjeto();
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    protected ArrayList<ItemPedido> obterTodosItens() {
        try {
            c1c[2].enviarTexto("iT");
            System.out.println(c1c[2].receberTexto());
            System.out.println("Cliente - obter todos itens pedidos");
            return (ArrayList<ItemPedido>) c1c[2].receberObjeto();
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    protected void armazenar() {
        try {
            c1c[0].enviarTexto("G");
            c1c[1].enviarTexto("G");
            c1c[2].enviarTexto("G");
            c1c[2].enviarTexto("iG");
        } catch (IOException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void carregar() {
        try {
            c1c[0].enviarTexto("C");
            c1c[1].enviarTexto("C");
            c1c[2].enviarTexto("C");
            c1c[2].enviarTexto("iC");
        } catch (IOException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void sairPgm() {
        try {
            c1c[0].enviarTexto("Bye");
            System.out.println(c1c[0].receberTexto());
            System.out.println("Cliente - Bye Bye fecha cliente...");
            c1c[0].enviarTexto("" + s[0].getInetAddress());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipalRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void preActions() {
        try {
            System.out.println("Metodo local (Cliente) preActions");

            for (int i = 0; i < NUMPORTAS; i++) {
                s[i] = new Socket(ipServidor, portas[i]);
                System.out.println("Conectado Porta <" + portas[i] + "> = " + s[i].isConnected());
            }
            for (int i = 0; i < NUMPORTAS; i++) {
                c1c[i] = new ControleComunicacao(s[i]);
            }
            //         carregaTabelas();
        } catch (Exception ex) {
        }
    }

    public static void main(String args[]) {

        if (args.length == 0) {
            ipServidor = "127.0.0.1";
        } else {
            ipServidor = Arrays.toString(args);
            ipServidor = ipServidor.substring(1, ipServidor.length()-1);
        }

        System.out.println("Ip Selecionado: " + ipServidor);
       
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JFPrincipalRemoto().setVisible(true);
        });
    }
}
