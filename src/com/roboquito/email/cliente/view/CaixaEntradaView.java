package com.roboquito.email.cliente.view;

import com.roboquito.email.cliente.controller.CaixaEntradaController;
import com.roboquito.email.cliente.model.PacotesRepository;
import com.roboquito.email.cliente.service.Arquivo;
import com.roboquito.email.cliente.service.CriptografiaAES;
import com.roboquito.email.cliente.service.CriptografiaRSA;
import com.roboquito.email.model.Cliente;
import com.roboquito.email.model.Pacote;
import java.awt.Color;
import java.awt.Font;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class CaixaEntradaView extends javax.swing.JFrame {

    private CaixaEntradaController caixaEntradaController;
    private Dashboard dashboard;
    private Cliente usuario = null;
    DefaultTableModel dtmEmails;
    private PacotesRepository pacotesRepository;

    public CaixaEntradaView(Cliente usuario) {
        this.usuario = usuario;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setResizable(false);
        setSize(870, 590);
        dashboard = Dashboard.getInstance(this.usuario);
        setLocationRelativeTo(dashboard);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        caixaEntradaController = new CaixaEntradaController(this);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        jTableEmails.getColumnModel().getColumn(1).setPreferredWidth(200);
        dtmEmails = (DefaultTableModel) jTableEmails.getModel();
        pacotesRepository = PacotesRepository.getInstance();
        lblUsuarioLogado.setText(usuario.getNome());
        clearTable();
        try {
            atualizarCaixaEntrada();

            // Configura a largura da segunda coluna
        } catch (Exception ex) {
            Logger.getLogger(CaixaEntradaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void atualizarCaixaEntrada() {

        //PrivateKey privatekey = (PrivateKey) Arquivo.lerObject("C:/keys/private.key");
        for (Pacote p : pacotesRepository.getAllPackages()) {
            //byte[] skey = CriptografiaRSA.decriptografa(p.getChaveSimetrica(), privatekey);
            // Instancia uma SecretKeySpec apartir da chave simétrica recebida do servidor
            //SecretKeySpec skeyspec = new SecretKeySpec(skey, "AES");
            //p.setMensagem(CriptografiaAES.decriptografar(skeyspec, p.getMensagem()).getBytes());

            adicionarEmailTabela(p); // Preencher a tabela de emails na caixa de entrada

        }
    }

    public void clearTable() {
        while (dtmEmails.getRowCount() > 0) {
            for (int i = 0; i < dtmEmails.getRowCount(); i++) {
                dtmEmails.removeRow(i);
            }
        }
    }

    public void adicionarEmailTabela(Pacote pacote) {
        Object dados[] = {pacote.getRemetente(), pacote.getAssunto(), pacote.getDataCriacao()};
        dtmEmails.addRow(dados);
    }

    public void preencherTable(ArrayList<Pacote> emails) {
        for (Pacote email : emails) {
            adicionarEmailTabela(email);
        }
    }

    public void abrirMensagem() {
        Date dataCriacao = (Date) jTableEmails.getValueAt(jTableEmails.getSelectedRow(), 2);
        Pacote pacote = pacotesRepository.getPacotesByDate(dataCriacao);

        try {
            PrivateKey privatekey = (PrivateKey) Arquivo.lerObject("C:/keys/private.key");
            byte[] skey = CriptografiaRSA.decriptografa(pacote.getChaveSimetrica(), privatekey);
            //Instancia uma SecretKeySpec apartir da chave simétrica recebida do servidor
            SecretKeySpec skeyspec = new SecretKeySpec(skey, "AES");

            txtMensagem.setText("");
            txtMensagem.append("ASSUNTO: " + pacote.getAssunto());
            txtMensagem.append("\nREMETENTE: " + pacote.getRemetente());
            txtMensagem.append("\nDATA: " + pacote.getDataCriacao());
            txtMensagem.append("\n\nMENSAGEM:\n" + new String(CriptografiaAES.decriptografar(skeyspec, pacote.getMensagem()).getBytes()));
            /*
                JOptionPane.showMessageDialog(this, "Remetente: " + pacote.getRemetente() + "\n"
                        + "Mensagem: " + pacote.getMensagem() + "\n"
                        + "Mensagem clara: " + new String(CriptografiaAES.decriptografar(skeyspec, pacote.getMensagem()).getBytes()));
             */
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível obter a chave para decifrar a mensagem: ");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonAbrirNaHome = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblUsuarioLogado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmails = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnEncaminhar = new javax.swing.JButton();
        btnResponder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setBackground(new java.awt.Color(128, 128, 128));

        jButtonAbrirNaHome.setText("Abrir Mensagem");
        jButtonAbrirNaHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirNaHomeActionPerformed(evt);
            }
        });

        jButton3.setText("Apagar Selecionada");

        jButton4.setText("Apagar Todas");

        jButton5.setText("SAIR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(127, 200, 237));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Usuário: ");

        lblUsuarioLogado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUsuarioLogado.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioLogado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Caixa de Entrada");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(114, 114, 114)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTableEmails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Remetente", "Assunto", "Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEmails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEmails);
        if (jTableEmails.getColumnModel().getColumnCount() > 0) {
            jTableEmails.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableEmails.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableEmails.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        txtMensagem.setColumns(20);
        txtMensagem.setRows(5);
        jScrollPane2.setViewportView(txtMensagem);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "fernandadhy@hotmail.com", "monicadhy@hotmail.com" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Encaminhar para: ");

        btnEncaminhar.setText("Encaminhar");

        btnResponder.setText("Responder");
        btnResponder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResponderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAbrirNaHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEncaminhar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResponder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAbrirNaHome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEncaminhar)
                        .addGap(18, 18, 18)
                        .addComponent(btnResponder)
                        .addGap(41, 41, 41)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dashboard.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtonAbrirNaHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirNaHomeActionPerformed

        int linhaSelecionada = jTableEmails.getSelectedRow();
        if (linhaSelecionada != -1) {
            abrirMensagem();

        } else {
            JOptionPane.showMessageDialog(null, "Você não selecionou uma mensagem!!");
        }


    }//GEN-LAST:event_jButtonAbrirNaHomeActionPerformed

    private void jTableEmailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmailsMouseClicked
        if (evt.getClickCount() == 2) {

            abrirMensagem();
        }
    }//GEN-LAST:event_jTableEmailsMouseClicked

    private void btnResponderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResponderActionPerformed

    }//GEN-LAST:event_btnResponderActionPerformed

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
            java.util.logging.Logger.getLogger(CaixaEntradaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CaixaEntradaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CaixaEntradaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CaixaEntradaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new CaixaEntradaView();
            }
        });
         */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncaminhar;
    private javax.swing.JButton btnResponder;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAbrirNaHome;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableEmails;
    private javax.swing.JLabel lblUsuarioLogado;
    private javax.swing.JTextArea txtMensagem;
    // End of variables declaration//GEN-END:variables
}
