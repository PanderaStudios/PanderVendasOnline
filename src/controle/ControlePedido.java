package controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.ItemPedido;
import modelo.Pedido;

public class ControlePedido {

    private static HashMap<String, Pedido> bancoPedidos = new HashMap<>();
    private static HashMap<String, ItemPedido> bancoItensPedido = new HashMap<>();

    public ControlePedido() { //throws IOException, ClassNotFoundException{
        // ControleCliente.carregar();

    }
    /**
     * @Author Heraldo variavel dir criada apenas uma vez para ser reutilizada
     * em toda a classe
     *
     */
    private final static String PEDIDOS = "Pedidos";
    private final static String pasta = ":\\TrabalhoJava\\";
    private final static String arquivo = ".bin";
    private static String drive = "c";

    private final static String ITENSPEDIDO = "ItensPedido";
 
    public static void carregar() throws IOException, ClassNotFoundException {
        //heraldo feito a validacao do arquivo
        File file = verificaArquivo(PEDIDOS);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String linha = buffer.readLine();
        if (linha != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(drive + pasta + PEDIDOS + arquivo))) {
                bancoPedidos = (HashMap<String, Pedido>) ois.readObject();
            }
        }
    }

    public static void carregarItens() throws IOException, ClassNotFoundException {
        //heraldo feito a validacao do arquivo
        File file = verificaArquivo(ITENSPEDIDO);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String linha = buffer.readLine();
        if (linha != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(drive + pasta + ITENSPEDIDO + arquivo))) {
                bancoItensPedido = (HashMap<String, ItemPedido>) ois.readObject();
            }
        }
    }

    public static void armazenar() throws IOException, ClassNotFoundException {
        //heraldo feito a validacao do arquivo
        File file = verificaArquivo(PEDIDOS);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String linha = buffer.readLine();
        if (linha != null || !bancoPedidos.isEmpty()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(drive + pasta + PEDIDOS + arquivo))) {
                oos.writeObject(bancoPedidos);
                oos.flush();
            }
        }
    }

    public static void armazenarItens() throws IOException, ClassNotFoundException {
        //heraldo feito a validacao do arquivo
        File file = verificaArquivo(ITENSPEDIDO);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String linha = buffer.readLine();
        if (linha != null || !bancoItensPedido.isEmpty()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(drive + pasta + ITENSPEDIDO + arquivo))) {
                oos.writeObject(bancoItensPedido);
                oos.flush();
            }
        }
    }

    /**
     * @return @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @
     * @param nomeArq
     * @Author Heraldo
     * @metodo criado para verificar se o arquivo existe caso nao exista ele
     * cria o arquivo
     *
     */
    public static File verificaArquivo(String nomeArq) throws IOException, ClassNotFoundException {

//        boolean retorno = false;
        File file = new File(drive + pasta + nomeArq + arquivo);
        if (!file.exists()) {
            drive = "";
            while (drive.isEmpty() || (!drive.equalsIgnoreCase("c") && !drive.equalsIgnoreCase("d"))) {
                if ((drive = JOptionPane.showInputDialog(null, "Informe o Disco do arquivo " + nomeArq + " (Ex. C ou D).",
                        "MSG Servidor", JOptionPane.INFORMATION_MESSAGE)) == null) {
                    drive = "d";
                }
            }

            file.renameTo(new File(drive + pasta + nomeArq + arquivo));

            if (!file.exists()) {
                file = new File(drive + pasta);
                file.mkdir();
                file = new File(drive + pasta + nomeArq + arquivo);
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "Arquivo " + nomeArq + " criado com sucesso em "
                        + drive + pasta + nomeArq + arquivo, "MSG do Servidor",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return file;
    }

    public void persistir(Pedido pedido) {
        System.out.println("* Controle Pedido * -- cheguei em persistirPedidos!!!");
        bancoPedidos.put(pedido.getCodPed(), pedido);
    }

    public void persistirItens(ItemPedido itemPedido) {
        System.out.println("* Controle Pedido * -- cheguei em persistirItens!!!");
        bancoItensPedido.put(itemPedido.getCodPed(), itemPedido);
    }

    public void remover(String cod) {
        bancoPedidos.remove(cod);
    }

    public void removerItem(String cod) {
        bancoItensPedido.remove(cod);
    }

    public Pedido obter(String cod) {
        if (bancoPedidos.containsKey(cod)) {
            return bancoPedidos.get(cod);
        } else {
            return null;
        }
    }

    public ItemPedido obterItem(String cod) {
        if (bancoItensPedido.containsKey(cod)) {
        System.out.println("* Controle Pedido * -- cheguei em obterItem!!!");
            return bancoItensPedido.get(cod);
        } else {
            return null;
        }
    }

    public ArrayList<Pedido> obterTodosPedidos() {
        ArrayList<Pedido> lista = new ArrayList<>();
        lista.addAll(bancoPedidos.values());
        Collections.sort(lista, (Pedido t1, Pedido t2)
                -> (t1.getCodCli() == null)
                        ? (t2.getCodCli() == null) ? 0 : -1
                        : t1.getCodCli().compareTo(t2.getCodCli()));
        return lista;
    }

    public ArrayList<ItemPedido> obterTodosItens() {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        lista.addAll(bancoItensPedido.values());
        Collections.sort(lista, (ItemPedido t1, ItemPedido t2)
                -> (t1.getCodPed()== null)
                        ? (t2.getCodPed() == null) ? 0 : -1
                        : t1.getCodPed().compareTo(t2.getCodPed()));
        System.out.println("* Controle Pedido * -- cheguei em obterTodosItens!!!");
        return lista;           
   }

}
