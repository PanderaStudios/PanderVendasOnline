package servidor;

import comum.controle.ControleComunicacao;
import controle.ControlePedido;
import java.io.IOException;
import java.net.Socket;
import modelo.ItemPedido;
import modelo.Pedido;

public class ThPedido extends Thread {

    private Socket ped;

    public ThPedido(Socket ped) {
        this.ped = ped;
    }

    @Override
    public void run() {
        try {
            ControleComunicacao c1 = new ControleComunicacao(ped);

            ControlePedido cPedido = new ControlePedido();

            while (true) {
                String comando = c1.receberTexto();

                if ("P".equals(comando)) {
                    System.out.println("Servidor ThPedido - persistir");
                    c1.enviarTexto("Servidor ThPedido - persistir");
                    // envia p/ cliente e grava arquivo Pedido
                    cPedido.persistir((Pedido) c1.receberObjeto());
                    ControlePedido.armazenar();
                }

                if ("iP".equals(comando)) {
                    System.out.println("Servidor ThPedido - persistir itens");
                    c1.enviarTexto("Servidor ThPedido - persistir itens");
                    // envia p/ cliente e grava arquivo ItemPedido
                    cPedido.persistirItens((ItemPedido) c1.receberObjeto());
                    ControlePedido.armazenarItens();
                }

                if ("R".equals(comando)) {
                    System.out.println("Servidor ThPedido - remover");
                    c1.enviarTexto("Servidor ThPedido - remover");
                    // envia p/ cliente e grava arquivo Pedido
                    cPedido.remover(c1.receberTexto());
                    ControlePedido.armazenar();
                }

                if ("iR".equals(comando)) {
                    System.out.println("Servidor ThPedido - remover itens");
                    c1.enviarTexto("Servidor ThPedido - remover itens");
                    // envia p/ cliente e grava arquivo ItemPedido
                    cPedido.removerItem(c1.receberTexto());
                    ControlePedido.armazenarItens();
                }

                if ("O".equals(comando)) {
                    System.out.println("Servidor ThPedido - obter");
                    c1.enviarTexto("Servidor ThPedido - obter");
                    String cod = c1.receberTexto();
                    // envia p/ cliente Cod Pedido
                    c1.enviarObjeto(cPedido.obter(cod));
                }

                if ("iO".equals(comando)) {
                    System.out.println("Servidor ThPedido - obter itens");
                    c1.enviarTexto("Servidor ThPedido - obter itens");
                    String cod = c1.receberTexto();
                    // envia p/ cliente Cod ItemPedido
                    c1.enviarObjeto(cPedido.obterItem(cod));
                }

                if ("G".equals(comando)) {
                    // grava arquivo Pedido
                    ControlePedido.armazenar();
                }

                if ("iG".equals(comando)) {
                    // grava arquivo ItemPedido
                    ControlePedido.armazenarItens();
                }

                if ("C".equals(comando)) {
                    // le arquivo Pedido
                    ControlePedido.carregar();
                }

                if ("iC".equals(comando)) {
                    // le arquivo ItemPedido
                    ControlePedido.carregarItens();
                }

                if ("T".equals(comando)) {
                    System.out.println("Servidor ThPedido - obter todos");
                    c1.enviarTexto("Servidor ThPedido - obter todos");
                    // envia p/ cliente Todos Pedido
                    c1.enviarObjeto(cPedido.obterTodosPedidos());
                }

                if ("iT".equals(comando)) {
                    System.out.println("Servidor ThPedido - obter todos itens");
                    c1.enviarTexto("Servidor ThPedido - obter todos itens");
                    // envia p/ cliente Todos ItensPedido
                    c1.enviarObjeto(cPedido.obterTodosItens());
                }

//                c1.enviarObjeto(cPedido.obterTodosPedidos());

            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

}
