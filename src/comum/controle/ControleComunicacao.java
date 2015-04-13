package comum.controle;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ControleComunicacao {

    private ObjectInputStream entrada;
    private ObjectOutputStream  saida;
    
    public ControleComunicacao(InputStream in, OutputStream out) throws IOException {
        saida = new ObjectOutputStream(out);
        entrada = new ObjectInputStream(in);
    }
    
    public ControleComunicacao(Socket s) throws IOException{
        this(s.getInputStream(),s.getOutputStream());
    }
    
    public void enviarObjeto(Object o) throws IOException{
        saida.writeObject(o);
        saida.flush();
    }
    
    public Object receberObjeto() throws IOException, ClassNotFoundException{
        return entrada.readObject();
    }
    
    public void enviarTexto(String s) throws IOException{
        enviarObjeto(s);
    }
    
    public String receberTexto() throws IOException, ClassNotFoundException {
        return (String) receberObjeto();
    }
    
    public void enviarInteiro(int i) throws IOException{
        enviarObjeto(new Integer(i));
    }
    
    public int receberInteiro() throws IOException, ClassNotFoundException{
        return ((Integer)receberObjeto()).intValue();
    }
    
    public void enviarFracionario(double d) throws IOException{
        enviarObjeto(new Double(d));
    }
    
    public double receberFracionario() throws IOException, ClassNotFoundException{
        return ((Double)receberObjeto()).doubleValue();
    }
    
}
