/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import controle.ControlePedido;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.ItemPedido;
import modelo.Pedido;

/**
 *
 * @author aluno
 */
public class JDDadosPedidos extends javax.swing.JDialog {

    public boolean sucesso = false;

    protected ControlePedido cPedido;
    
    private String codPed;
    public boolean itemSucesso = false;

//    private Pedido pedido;
    private ItemPedido itemPedido;

    public void setDados(Pedido ped, String codPed, String codCli, String nomeCli, String valorPed, DefaultTableModel listaProdPed) {
        {
            txtCodPed.setText(
                    (ped == null) ? codPed : ped.getCodPed());
            txtCodCliente.setText(
                    (ped == null) ? codCli : ped.getCodCli());
            txtNomeCliente.setText(
                    (ped == null) ? nomeCli : ped.getNomeCli());
            txtTotalPed.setText(
                    (ped == null) ? valorPed : ped.getTotalPed());

            //           this.listaProdPed = listaProdPed;
            txtCodPed.setEditable(false);
            txtCodPed.setEnabled(false);
            txtCodCliente.setEditable(false);
            txtCodCliente.setEnabled(false);
            txtNomeCliente.setEditable(false);
            txtNomeCliente.setEnabled(false);
        }
    }

    public Pedido getDados() {
        return new Pedido(
                txtCodPed.getText(),
                txtCodCliente.getText(),
                txtNomeCliente.getText(),
                txtTotalPed.getText()
        );
    }

    /**
     * Creates new form JDDados
     *
     * @param parent
     * @param modal
     * @param cPedido
     * @param codPed
     */
    public JDDadosPedidos(Frame parent, boolean modal, ControlePedido cPedido, String codPed, ItemPedido itemPedido) {
        super(parent, modal);
        this.cPedido = cPedido;
        this.codPed = codPed;
        this.itemPedido = itemPedido;
        initComponents();
    }

    private void atualizarTabela() {
        jTablePedido.setModel(getDadosTabItemPedido());
    }

    protected TableModel getDadosTabItemPedido() {
        String[] titulos
                = {"CodProd", "NomeProd", "QtdCompr", "ValorPed"};
//        try {
        ArrayList<ItemPedido> lista = cPedido.obterTodosItens();
        Object[][] valores = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            valores[i][0] = lista.get(i).getCodProd();
            valores[i][1] = lista.get(i).getNomeProd(); // trocar por nome protudo
            valores[i][2] = lista.get(i).getQtdComprada();
            valores[i][3] = lista.get(i).getValorCompra();
        }
        return new DefaultTableModel(valores, titulos);
    }

    protected void persistirItemPedido(ItemPedido itemped, String codPed, String codProd, String nomeProd, String qtdCompr, String valorPed) {
        JDDadosItensPed dados = new JDDadosItensPed(null, true);
        dados.setDados(itemped, codPed, codProd, nomeProd, qtdCompr, valorPed);
        dados.setVisible(true);
                System.out.println("* JDDadosPedidos * -- cheguei em BtmIncluirItem!!!");
        // Modal -> Fica parado aqui até a janela "sumir"
        if (dados.sucesso) {
 //           cPedido.persistirItens(dados.getDados());
            itemPedido = itemped;
            itemSucesso = true;
        }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        btmConfirmar = new javax.swing.JButton();
        btmCancelar = new javax.swing.JButton();
        txtCodPed = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePedido = new javax.swing.JTable();
        btmIncluirItem = new javax.swing.JButton();
        btmAlterarItem = new javax.swing.JButton();
        btmExcluirItem = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTotalPed = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pandera Studios (Cliente)");

        jLabel1.setText("Cod Pedido");

        jLabel2.setText("Cod Cliente");

        btmConfirmar.setText("Confirmar");
        btmConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmConfirmarActionPerformed(evt);
            }
        });

        btmCancelar.setText("Cancelar");
        btmCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmCancelarActionPerformed(evt);
            }
        });

        txtCodPed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel5.setText("Entrada de Dados do Pedido");

        jTablePedido.setModel(getDadosTabItemPedido());
        jScrollPane3.setViewportView(jTablePedido);

        btmIncluirItem.setText("Incluir Item");
        btmIncluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmIncluirItemActionPerformed(evt);
            }
        });

        btmAlterarItem.setText("Alterar Item");
        btmAlterarItem.setEnabled(false);
        btmAlterarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmAlterarItemActionPerformed(evt);
            }
        });

        btmExcluirItem.setText("Excluir Item");
        btmExcluirItem.setEnabled(false);
        btmExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmExcluirItemActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome Cliente");

        jLabel4.setText("Total Pedido");

        txtTotalPed.setEditable(false);
        txtTotalPed.setEnabled(false);

        jMenu1.setText("File");

        jMenuItem1.setText("Confirmar");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem3.setText("Incluir Item");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Alterar Item");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Excluir Item");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(11, 11, 11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodPed, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(txtCodCliente))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTotalPed, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btmConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btmCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btmIncluirItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmAlterarItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmExcluirItem)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTotalPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btmIncluirItem)
                    .addComponent(btmAlterarItem)
                    .addComponent(btmExcluirItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btmConfirmar)
                    .addComponent(btmCancelar))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmConfirmarActionPerformed
        sucesso = true;
        setVisible(false);
    }//GEN-LAST:event_btmConfirmarActionPerformed

    private void btmCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmCancelarActionPerformed
        sucesso = false;
        setVisible(false);
    }//GEN-LAST:event_btmCancelarActionPerformed

    private void btmIncluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmIncluirItemActionPerformed
        // TODO add your handling code here:
        String codProd = "";
//entraCodProd(true); // recebera codigo digitado
// janela de input do CodProd
        codProd = JOptionPane.showInputDialog(this, "Codigo Produto");
        if (codProd != null) {
            if (!codProd.isEmpty()) {
                // Modal -> Fica parado aqui até a janela "sumir"
                System.out.println("* JDDadosPedidos * -- cheguei em BtmIncluirItem!!!");
                persistirItemPedido(null, codPed, codProd, "", "", "");
                atualizarTabela();
            }
        }

    }//GEN-LAST:event_btmIncluirItemActionPerformed

    private void btmAlterarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmAlterarItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmAlterarItemActionPerformed

    private void btmExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmExcluirItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmExcluirItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDDadosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDDadosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDDadosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDDadosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            JDDadosPedidos dialog = new JDDadosPedidos(new JFrame(), true, null, null, null);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmAlterarItem;
    private javax.swing.JButton btmCancelar;
    private javax.swing.JButton btmConfirmar;
    private javax.swing.JButton btmExcluirItem;
    private javax.swing.JButton btmIncluirItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTablePedido;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtCodPed;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtTotalPed;
    // End of variables declaration//GEN-END:variables

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }
}
