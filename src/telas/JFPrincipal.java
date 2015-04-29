/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import controle.ControleCliente;
import controle.ControleProduto;
import controle.ControlePedido;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Produto;
import modelo.Pedido;

/**
 *
 * @author aluno
 */
public class JFPrincipal extends javax.swing.JFrame {

//    private String servidorNome;
    protected ControleCliente cCliente
            = new ControleCliente();

    protected ControleProduto cProduto
            = new ControleProduto();

    protected ControlePedido cPedido;
//            = new ControlePedido();

    protected ArrayList<Cliente> obterTodos() {
        return cCliente.obterTodos();
    }

    protected ArrayList<Produto> obterTodosProdutos() {
        return cProduto.obterTodosProdutos();
    }

    protected ArrayList<Pedido> obterTodosPedidos() {
        return cPedido.obterTodosPedidos();
    }

    protected ArrayList<ItemPedido> obterTodosItens() {
        return cPedido.obterTodosItens();
    }

    protected TableModel getDadosTabela() {
        ArrayList<Cliente> lista = obterTodos();
        String[] titulos
                = {"CPF", "Nome", "Endereco", "Telefone"};
        Object[][] valores0 = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            valores0[i][0] = lista.get(i).getCpf();
            valores0[i][1] = lista.get(i).getNome();
            valores0[i][2] = lista.get(i).getEndereco();
            valores0[i][3] = lista.get(i).getTelefone();
        }
        return new DefaultTableModel(valores0, titulos);
    }

    protected TableModel getDadosTabelaProduto() {
        ArrayList<Produto> lista1 = obterTodosProdutos();
        String[] titulos
                = {"COD", "Nome", "Quantidade", "Valor"};
        Object[][] valores1 = new Object[lista1.size()][4];
        for (int i = 0; i < lista1.size(); i++) {
            valores1[i][0] = lista1.get(i).getCod();
            valores1[i][1] = lista1.get(i).getNome();
            valores1[i][2] = lista1.get(i).getQuantidade();
            valores1[i][3] = lista1.get(i).getValor();
        }
        return new DefaultTableModel(valores1, titulos);
    }

    protected TableModel getDadosTabelaPedido() {
        ArrayList<Pedido> lista = obterTodosPedidos();
        String[] titulos
                = {"CodProd", "CodCli", "NomeCli", "ValorPed"};
        Object[][] valores2 = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            valores2[i][0] = lista.get(i).getCodPed();
            valores2[i][1] = lista.get(i).getCodCli();
            valores2[i][2] = lista.get(i).getNomeCli();
            valores2[i][3] = lista.get(i).getTotalPed();
        }
        return new DefaultTableModel(valores2, titulos);
    }

    protected TableModel getDadosTabelaItens() {
        ArrayList<ItemPedido> lista = obterTodosItens();
        String[] titulos
                = {"CodProd", "NomeProd", "QrtCompr", "Total"};
        Object[][] valores = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            valores[i][0] = lista.get(i).getCodPed();
            valores[i][1] = lista.get(i).getCodProd();
            valores[i][2] = lista.get(i).getQtdComprada();
            valores[i][3] = lista.get(i).getValorCompra();
        }
        return new DefaultTableModel(valores, titulos);
    }

    private void atualizarTabela() {
        jTableCliente.setModel(getDadosTabela());
        jTableProduto.setModel(getDadosTabelaProduto());
        jTablePedido.setModel(getDadosTabelaPedido());
        helloServer();
    }

    protected void helloServer() {
    }

    public void setServidorNome(String servidorNome) {
        txtServerName.setText(servidorNome);
        System.out.println("Cliente Principal - ServerName --> " + servidorNome);
    }

    protected void persistir(Cliente c, String cpf) {
        JDDadosCliente dados = new JDDadosCliente(this, true);
        dados.setDados(c, cpf);
        dados.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dados.sucesso) {
            cCliente.persistir(dados.getDados());
        }
    }

    protected void persistirProduto(Produto p, String cod) {
        JDDadosProduto dados1 = new JDDadosProduto(this, true);
        dados1.setDados(p, cod);
        dados1.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dados1.sucesso) {
            cProduto.persistir(dados1.getDados());
        }
    }

    protected void persistirPedido(Pedido ped, String codPed, String codCli, String nomeCli, String valorPed, DefaultTableModel itensPed) {
        JDDadosPedidos dados = new JDDadosPedidos(this, true,cPedido ,itensPed, cPedido.obter(codCli));
        dados.setDados(ped, codPed, codCli, nomeCli, valorPed, itensPed);
        dados.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dados.sucesso) {
            cPedido.persistir(dados.getDados());
        }
    }

    protected void persistirItemPedido(ItemPedido itemped, String codProd, String nomeProd, String qtdCompr, String valorPed) {
        JDDadosItensPed dados = new JDDadosItensPed(this, true);
        dados.setDados(itemped, codProd, nomeProd, qtdCompr, valorPed);
        dados.setVisible(true);
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dados.sucesso) {
            cPedido.persistirItens(dados.getDados());
        }
    }

    protected void remover(String cpf) {
        cCliente.remover(cpf);
    }

    protected void removerProduto(String cpf) {
        cProduto.remover(cpf);
    }

    protected void removerPedido(String cod) {
        cPedido.remover(cod);
    }

    protected void removerItemPedido(String cod) {
        cPedido.removerItem(cod);
    }

    protected Cliente obter(String cpf) {
        return cCliente.obter(cpf);
    }

    protected Produto obterProduto(String cpf) {
        return cProduto.obter(cpf);
    }

    protected Pedido obterPedido(String cod) {
        return cPedido.obter(cod);
    }

    protected ItemPedido obterItemPedido(String cod) {
        return cPedido.obterItem(cod);
    }

    protected void preActions() {
        //    txtServerName.setText(JFPrincipalRemota.getServerName());

    }

    /**
     * Creates new form JFPrincipal
     * @param cPedido
     */
    public JFPrincipal(ControlePedido cPedido) {
//        preActions();
        this.cPedido = cPedido;
        initComponents();
        txtServerName.setText("OFFLINE");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        btmSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePedido = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtServerName = new javax.swing.JTextField();
        btmConectarServer = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jRadioConectar = new javax.swing.JRadioButtonMenuItem();
        jRadioDesconectar = new javax.swing.JRadioButtonMenuItem();
        mnuArmazenar = new javax.swing.JMenuItem();
        mnuRecuperar = new javax.swing.JMenuItem();
        mnuAtualizarCliente = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuSair = new javax.swing.JMenuItem();
        jMenuPedidos = new javax.swing.JMenu();
        mnuIncluirPedidos = new javax.swing.JMenuItem();
        mnuAlterarPedidos = new javax.swing.JMenuItem();
        mnuExcluirPedidos = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mnuIncluirItemPedido = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuIncluirCliente = new javax.swing.JMenuItem();
        mnuAlterarCliente = new javax.swing.JMenuItem();
        mnuExcluirCliente = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuProdutos = new javax.swing.JMenu();
        mnuIncluirProduto = new javax.swing.JMenuItem();
        mnuAlterarProduto = new javax.swing.JMenuItem();
        mnuExcluirProduto = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pandera Studios (Cliente)");

        jTableCliente.setModel(getDadosTabela());
        jScrollPane1.setViewportView(jTableCliente);

        jTableProduto.setModel(getDadosTabelaProduto());
        jScrollPane2.setViewportView(jTableProduto);

        btmSair.setText("SAIR");
        btmSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSairActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("CLIENTES");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PRODUTOS");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CONTROLE DE VENDAS");

        jTablePedido.setModel(getDadosTabelaPedido());
        jScrollPane3.setViewportView(jTablePedido);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("PEDIDOS");

        jLabel5.setText("Server -->");

        txtServerName.setEditable(false);
        txtServerName.setText("OffLine");
        txtServerName.setEnabled(false);

        btmConectarServer.setText("Conectar Servidor");
        btmConectarServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmConectarServerActionPerformed(evt);
            }
        });

        jMenu2.setText("Conectar");

        jRadioConectar.setSelected(true);
        jRadioConectar.setText("Conectar Servidor");
        jRadioConectar.setEnabled(false);
        jMenu2.add(jRadioConectar);

        jRadioDesconectar.setSelected(true);
        jRadioDesconectar.setText("Desconectar Servidor");
        jRadioDesconectar.setEnabled(false);
        jRadioDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDesconectarActionPerformed(evt);
            }
        });
        jMenu2.add(jRadioDesconectar);

        mnuArmazenar.setText("Armazenar Dados");
        mnuArmazenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuArmazenarActionPerformed(evt);
            }
        });
        jMenu2.add(mnuArmazenar);

        mnuRecuperar.setText("Recuperar Dados");
        mnuRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRecuperarActionPerformed(evt);
            }
        });
        jMenu2.add(mnuRecuperar);

        mnuAtualizarCliente.setText("Atualizar Tela");
        mnuAtualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAtualizarClienteActionPerformed(evt);
            }
        });
        jMenu2.add(mnuAtualizarCliente);
        jMenu2.add(jSeparator2);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        jMenu2.add(mnuSair);

        jMenuBar1.add(jMenu2);

        jMenuPedidos.setText("Pedidos");
        jMenuPedidos.setActionCommand("");

        mnuIncluirPedidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        mnuIncluirPedidos.setText("Incluir");
        mnuIncluirPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIncluirPedidosActionPerformed(evt);
            }
        });
        jMenuPedidos.add(mnuIncluirPedidos);

        mnuAlterarPedidos.setText("Alterar");
        mnuAlterarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAlterarPedidosActionPerformed(evt);
            }
        });
        jMenuPedidos.add(mnuAlterarPedidos);

        mnuExcluirPedidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        mnuExcluirPedidos.setText("Excluir");
        mnuExcluirPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExcluirPedidosActionPerformed(evt);
            }
        });
        jMenuPedidos.add(mnuExcluirPedidos);
        jMenuPedidos.add(jSeparator4);

        mnuIncluirItemPedido.setText("Incluir Item Pedido");
        mnuIncluirItemPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIncluirItemPedidoActionPerformed(evt);
            }
        });
        jMenuPedidos.add(mnuIncluirItemPedido);

        jMenuBar1.add(jMenuPedidos);

        jMenu1.setText("Clientes");

        mnuIncluirCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        mnuIncluirCliente.setText("Incluir");
        mnuIncluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIncluirClienteActionPerformed(evt);
            }
        });
        jMenu1.add(mnuIncluirCliente);

        mnuAlterarCliente.setText("Alterar");
        mnuAlterarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAlterarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(mnuAlterarCliente);

        mnuExcluirCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        mnuExcluirCliente.setText("Excluir");
        mnuExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExcluirClienteActionPerformed(evt);
            }
        });
        jMenu1.add(mnuExcluirCliente);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        jMenuProdutos.setText("Produtos");

        mnuIncluirProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        mnuIncluirProduto.setText("Incluir");
        mnuIncluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIncluirProdutoActionPerformed(evt);
            }
        });
        jMenuProdutos.add(mnuIncluirProduto);

        mnuAlterarProduto.setText("Alterar");
        mnuAlterarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAlterarProdutoActionPerformed(evt);
            }
        });
        jMenuProdutos.add(mnuAlterarProduto);

        mnuExcluirProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        mnuExcluirProduto.setText("Excluir");
        mnuExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExcluirProdutoActionPerformed(evt);
            }
        });
        jMenuProdutos.add(mnuExcluirProduto);
        jMenuProdutos.add(jSeparator3);

        jMenuBar1.add(jMenuProdutos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btmConectarServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(544, 544, 544)
                                .addComponent(btmSair))
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btmConectarServer))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btmSair)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuAtualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAtualizarClienteActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_mnuAtualizarClienteActionPerformed

    private void mnuIncluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIncluirClienteActionPerformed
        String cpf = entraCpfCli(true); // recebera codigo digitado
        if (cpf != null) {
            if (!cpf.isEmpty()) {
                persistir(null, cpf);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuIncluirClienteActionPerformed

    private void mnuAlterarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAlterarClienteActionPerformed
        String cpf = entraCpfCli(false); // recebera codigo digitado
        if (cpf != null) {
            if (!cpf.isEmpty()) {
                persistir(obter(cpf), cpf);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuAlterarClienteActionPerformed

    private void mnuExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExcluirClienteActionPerformed
        String cpf = entraCpfCli(false); // recebera codigo digitado
        if (cpf != null) {
            if (!cpf.isEmpty()) {
                remover(cpf);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuExcluirClienteActionPerformed

    private void mnuArmazenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuArmazenarActionPerformed

        try {
            ControlePedido.armazenar();
            ControleCliente.armazenar();
            ControleProduto.armazenar();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        atualizarTabela();
//        atualizarTabelaProduto();
    }//GEN-LAST:event_mnuArmazenarActionPerformed

    private void mnuRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRecuperarActionPerformed

        try {
            ControlePedido.carregar();
            ControleCliente.carregar();
            ControleProduto.carregar();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JFPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        atualizarTabela();
//        atualizarTabelaProduto();
    }//GEN-LAST:event_mnuRecuperarActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        // TODO add your handling code here:

        sairPgm();
    }//GEN-LAST:event_mnuSairActionPerformed

    private void mnuIncluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIncluirProdutoActionPerformed
        // TODO add your handling code here:
        String cod = entraCodProd(true); // recebera codigo digitado
        if (cod != null) {
            if (!cod.isEmpty()) {
                // Modal -> Fica parado aqui até a janela "sumir"
                persistirProduto(null, cod);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuIncluirProdutoActionPerformed

    private void mnuAlterarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAlterarProdutoActionPerformed
        // TODO add your handling code here:
        String cpf = entraCodProd(false); // recebera codigo digitado
        if (cpf != null) {
            if (!cpf.isEmpty()) {
                persistirProduto(obterProduto(cpf), cpf);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuAlterarProdutoActionPerformed

    private void mnuExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExcluirProdutoActionPerformed
        // TODO add your handling code here:
        String cpf = entraCodProd(false); // recebera codigo digitado
        if (cpf != null) {
            if (!cpf.isEmpty()) {
                removerProduto(cpf);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuExcluirProdutoActionPerformed

    private void btmSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSairActionPerformed
        // TODO add your handling code here:

        int sair;
        sair = JOptionPane.showConfirmDialog(null,
                "Confirma Sair do Programa?", "Cliente",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (sair == 0) {
            jTableCliente.removeAll();
            jTableProduto.removeAll();
        sairPgm();
            System.exit(0);
        }

    }//GEN-LAST:event_btmSairActionPerformed

    private void jRadioDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDesconectarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioDesconectarActionPerformed

    private void mnuIncluirPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIncluirPedidosActionPerformed
// entra com codigo Pedido nao existente
        String codPed = entraCodPed(true); // recebera codigo digitado
        if (codPed != null) {
            if (!codPed.isEmpty()) {
// entra com codigo cliente existente
                String cpf = entraCpfCli(false); // recebera codigo digitado
                if (cpf != null) {
                    if (!cpf.isEmpty()) {
                        // Modal -> Fica parado aqui até a janela "sumir"
                        persistirPedido(null, codPed, cpf, obter(cpf).getCpf(), "0,00", (DefaultTableModel) getDadosTabelaItens());
                        atualizarTabela();
                    }
                }
            }
        }

    }//GEN-LAST:event_mnuIncluirPedidosActionPerformed

    private void mnuAlterarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAlterarPedidosActionPerformed
        // TODO add your handling code here:
        String cod = entraCodPed(false); // recebera codigo digitado
        if (cod != null) {
            if (!cod.isEmpty()) {
                persistirPedido(obterPedido(cod), cod, "", "", "", null);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuAlterarPedidosActionPerformed

    private void mnuExcluirPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExcluirPedidosActionPerformed
        // TODO add your handling code here:
        String cod = entraCodPed(false); // recebera codigo digitado
        if (cod != null) {
            if (!cod.isEmpty()) {
                removerPedido(cod);
                atualizarTabela();
            }
        }
    }//GEN-LAST:event_mnuExcluirPedidosActionPerformed

    private void mnuIncluirItemPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIncluirItemPedidoActionPerformed
        // TODO add your handling code here:
        String codPed = entraCodPed(false); // recebera codigo digitado
        if (codPed != null) {
            if (!codPed.isEmpty()) {
// entra com codigo cliente existente
                
//                String cpf =  cCliente.obter(codPed).getCpf() ;//entraCpfCli(false); // recebera codigo digitado
//                if (cpf != null) {
//                    if (!cpf.isEmpty()) {
                        // Modal -> Fica parado aqui até a janela "sumir"
                        persistirItemPedido(obterItemPedido(codPed), codPed, obter(codPed).getCpf(), obter(codPed).getNome(),
                                "0,00");
                        atualizarTabela();
//                    }
//               }
            }
        }

    }//GEN-LAST:event_mnuIncluirItemPedidoActionPerformed

    private void btmConectarServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmConectarServerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmConectarServerActionPerformed

    protected void sairPgm() {
         int sair;
        sair = JOptionPane.showConfirmDialog(null,
                "Confirma Sair do Programa?", "Cliente",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (sair == 0) {
            jTableCliente.removeAll();
            jTableProduto.removeAll();
            System.exit(0);
        }
    }


    private String entraCpfCli(boolean isIncluir) {
        // TODO add your handling code here:
        String cpf = "";
        boolean isCadastro = false;

        ArrayList<Cliente> cpfCod = (obterTodos());

        // loop enquanto teclar vazio ou not fim
        while (cpf.isEmpty() || true) {
            // janela de input do CPF
            cpf = JOptionPane.showInputDialog(this, "CPF");
            if (cpf == null) {
                return null;
            } else if (cpf.isEmpty()) {
                // SE DER <ENTER> JOGA MENSAGEM DE ERRO e VOLTA AO LOOP.
                JOptionPane.showMessageDialog(null, "Por Favor, Digite Algo!",
                        "Msg do Servidor", JOptionPane.INFORMATION_MESSAGE);
            } else {

                for (Cliente cpfCod1 : cpfCod) {
                    if (cpf.equals(cpfCod1.getCpf())) {
                        if (isIncluir) {
                            // ENQUANTO NAO ENCONTRA JOGA MENSAGEM DE ERRO e
                            // VOLTA AO LOOP.
                            JOptionPane.showMessageDialog(null,
                                    "Cliente Ja Cadastrado!!!",
                                    "Msg do Servidor",
                                    JOptionPane.INFORMATION_MESSAGE);
                            isCadastro = true;
                            break;
                        } else {
                            return cpf;
                        }
                    }
                    isCadastro = false;
                }

                if (!isIncluir) {
                    // ENQUANTO NAO ENCONTRA JOGA MENSAGEM DE ERRO e VOLTA AO
                    // LOOP.
                    JOptionPane.showMessageDialog(null,
                            "Cliente nao encontrado!!!", "Msg do Servidor",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (!isCadastro) {
                        return cpf;
                    }
                }
            }

        }
        return cpf;
    }

    private String entraCodProd(boolean isIncluir) {
        // TODO add your handling code here:
        String cod = "";
        boolean isCadastro = false;

        ArrayList<Produto> codProd = (obterTodosProdutos());

        // loop enquanto teclar vazio ou not fim
        while (cod.isEmpty() || true) {
            // janela de input do CPF
            cod = JOptionPane.showInputDialog(this, "Cod");
            if (cod == null) {
                return null;
            } else if (cod.isEmpty()) {
                // SE DER <ENTER> JOGA MENSAGEM DE ERRO e VOLTA AO LOOP.
                JOptionPane.showMessageDialog(null, "Por Favor, Digite Algo!",
                        "Msg do Servidor", JOptionPane.INFORMATION_MESSAGE);
            } else {

                for (Produto codProd1 : codProd) {
                    if (cod.equals(codProd1.getCod())) {
                        if (isIncluir) {
                            // ENQUANTO NAO ENCONTRA JOGA MENSAGEM DE ERRO e
                            // VOLTA AO LOOP.
                            JOptionPane.showMessageDialog(null,
                                    "Produto Ja Cadastrado!!!",
                                    "Msg do Servidor",
                                    JOptionPane.INFORMATION_MESSAGE);
                            isCadastro = true;
                            break;
                        } else {
                            return cod;
                        }
                    }
                    isCadastro = false;
                }

                if (!isIncluir) {
                    // ENQUANTO NAO ENCONTRA JOGA MENSAGEM DE ERRO e VOLTA AO
                    // LOOP.
                    JOptionPane.showMessageDialog(null,
                            "Produto nao encontrado!!!", "Msg do Servidor",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (!isCadastro) {
                        return cod;
                    }
                }
            }

        }
        return cod;
    }

    private String entraCodPed(boolean isIncluir) {
        // TODO add your handling code here:
        String cod = "";
        boolean isCadastro = false;

        ArrayList<Pedido> codPed = (obterTodosPedidos());

        // loop enquanto teclar vazio ou not fim
        while (cod.isEmpty() || true) {
            // janela de input do CPF
            cod = JOptionPane.showInputDialog(this, "Cod");
            if (cod == null) {
                return null;
            } else if (cod.isEmpty()) {
                // SE DER <ENTER> JOGA MENSAGEM DE ERRO e VOLTA AO LOOP.
                JOptionPane.showMessageDialog(null, "Por Favor, Digite Algo!",
                        "Msg do Servidor", JOptionPane.INFORMATION_MESSAGE);
            } else {

                for (Pedido codPed1 : codPed) {
                    if (cod.equals(codPed1.getCodPed())) {
                        if (isIncluir) {
                            // ENQUANTO NAO ENCONTRA JOGA MENSAGEM DE ERRO e
                            // VOLTA AO LOOP.
                            JOptionPane.showMessageDialog(null,
                                    "Pedido Ja Cadastrado!!!",
                                    "Msg do Servidor",
                                    JOptionPane.INFORMATION_MESSAGE);
                            isCadastro = true;
                            break;
                        } else {
                            return cod;
                        }
                    }
                    isCadastro = false;
                }

                if (!isIncluir) {
                    // ENQUANTO NAO ENCONTRA JOGA MENSAGEM DE ERRO e VOLTA AO
                    // LOOP.
                    JOptionPane.showMessageDialog(null,
                            "Pedido nao encontrado!!!", "Msg do Servidor",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (!isCadastro) {
                        return cod;
                    }
                }
            }

        }
        return cod;
    }

/*    private String getNomeCli(String cpf) {

        ArrayList<Cliente> cpfCod = (obterTodos());

        for (Cliente cpfCod1 : cpfCod) {
            if (cpf.equals(cpfCod1.getCpf())) {
                return cpfCod1.getNome();
            }
        }
        return "";
    }
*/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new JFPrincipal(null).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmConectarServer;
    private javax.swing.JButton btmSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuPedidos;
    private javax.swing.JMenu jMenuProdutos;
    private javax.swing.JRadioButtonMenuItem jRadioConectar;
    private javax.swing.JRadioButtonMenuItem jRadioDesconectar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTable jTablePedido;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JMenuItem mnuAlterarCliente;
    private javax.swing.JMenuItem mnuAlterarPedidos;
    private javax.swing.JMenuItem mnuAlterarProduto;
    private javax.swing.JMenuItem mnuArmazenar;
    private javax.swing.JMenuItem mnuAtualizarCliente;
    private javax.swing.JMenuItem mnuExcluirCliente;
    private javax.swing.JMenuItem mnuExcluirPedidos;
    private javax.swing.JMenuItem mnuExcluirProduto;
    private javax.swing.JMenuItem mnuIncluirCliente;
    private javax.swing.JMenuItem mnuIncluirItemPedido;
    private javax.swing.JMenuItem mnuIncluirPedidos;
    private javax.swing.JMenuItem mnuIncluirProduto;
    private javax.swing.JMenuItem mnuRecuperar;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JTextField txtServerName;
    // End of variables declaration//GEN-END:variables

}
