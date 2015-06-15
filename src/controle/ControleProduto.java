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
import java.util.Comparator;
import java.util.HashMap;
import javax.swing.JOptionPane;
import modelo.Produto;

public class ControleProduto {

    private final static String PRODUTOS = "Produtos";
    private final static String pasta = ":\\TrabalhoJava\\";
    private final static String arquivo = ".bin";
    private static String dir = "d";

    private static HashMap<String, Produto> bancoProdutos = new HashMap<String, Produto>();

    public ControleProduto() { //throws IOException, ClassNotFoundException{
        // ControleCliente.carregarDados();

    }

    public static void carregar() throws IOException, ClassNotFoundException {
        File file = ControleProduto.verificaArquivo(PRODUTOS);

        BufferedReader buffer = new BufferedReader(new FileReader(file));

        String linha = buffer.readLine();

        if (linha != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dir + pasta + PRODUTOS + arquivo))) {
                bancoProdutos = (HashMap<String, Produto>) ois.readObject();
            }

        }
    }

    public static void armazenar() throws IOException, ClassNotFoundException {
        //heraldo feito a validacao do arquivo
        File file = ControleProduto.verificaArquivo(PRODUTOS);

        BufferedReader buffer = new BufferedReader(new FileReader(file));

        String linha = buffer.readLine();

        if (linha != null || !bancoProdutos.isEmpty()) {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(dir + pasta + PRODUTOS + arquivo));
            oos.writeObject(bancoProdutos);
            oos.flush();
            oos.close();
        }

    }

    public static File verificaArquivo(String nomeArq) throws IOException, ClassNotFoundException {

        File file = new File(dir + pasta + PRODUTOS + arquivo);
        if (!file.exists()) {
            dir = "";
            while (dir.isEmpty() || (!dir.equalsIgnoreCase("c") && !dir.equalsIgnoreCase("d"))) {
                if ((dir = JOptionPane.showInputDialog(null, "Informe o Disco do arquivo Produto (Ex. C ou D).",
                        "MSG Servidor", JOptionPane.INFORMATION_MESSAGE)) == null) {
                    dir = "d";
                }
            }

            file.renameTo(new File(dir + pasta + PRODUTOS + arquivo));

            if (!file.exists()) {
                file = new File(dir + pasta);
                file.mkdir();
                file = new File(dir + pasta + PRODUTOS + arquivo);
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "Arquivo Produto criado com sucesso em "
                        + dir + pasta + PRODUTOS + arquivo, "MSG Servidor",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return file;
    }

    public void persistir(Produto produto) {
        bancoProdutos.put(produto.getCod(), produto);
    }

    public void remover(String cpf) {
        bancoProdutos.remove(cpf);
    }

    public Produto obter(String cpf) {
        if (bancoProdutos.containsKey(cpf)) {
            return bancoProdutos.get(cpf);
        } else {
            return null;
        }
    }

    public ArrayList<Produto> obterTodosProdutos() {
        ArrayList<Produto> lista = new ArrayList<>();
        lista.addAll(bancoProdutos.values());
        Collections.sort(lista, new Comparator<Produto>() {
            public int compare(Produto t1, Produto t2) {
                return (t1.getNome() == null)
                        ? (t2.getNome() == null) ? 0 : -1
                        : t1.getNome().compareTo(t2.getNome());
            }
        });
        return lista;
    }

}
