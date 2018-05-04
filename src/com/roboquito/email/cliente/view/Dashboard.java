
package com.roboquito.email.cliente.view;

import com.roboquito.email.cliente.controller.DashboardController;
import com.roboquito.email.cliente.model.PacotesRepository;
import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.model.Cliente;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public final class Dashboard extends javax.swing.JFrame {

    DashboardController dashboardController;
    private static Dashboard instanceDashboard;
    private Cliente usuario = null;
    PacotesRepository pacotesRepository;
    
    
    public static Dashboard getInstance(Cliente usuario) {
        if(instanceDashboard == null){
            instanceDashboard = new Dashboard(usuario);
        }
        return instanceDashboard;        
    }

    
    private Dashboard(Cliente usuario) {
        initComponents();
        this.usuario = usuario;
        setLocationRelativeTo(this);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardController = new DashboardController(this);
        lblUsuarioLogado.setText(usuario.getNome());
        pacotesRepository = PacotesRepository.getInstance();
        pacotesRepository.clean();
        sincronizarServidor();
        lblTotalEmails.setText(Integer.toString(pacotesRepository.getAllPackages().size()));
    }
    
    
    public void sincronizarServidor() {
        // Cria um pacote e define o método que o servidor deve executar ao recebê-lo
        Pacote pacote = new Pacote();
        pacote.setMetodo(ServerMethods.GET_OBJECTS_BYADDRESSEE);
        pacote.setDestinatario(this.usuario.getEmail());

        try{
        // Estabelece conexão via socket e envia o objeto pacate para o servidor
        Socket socket = new Socket("127.0.0.1", 5000);
        Util.enviarObjeto(pacote, socket.getOutputStream());

        // Ler o pocote enviado pelo servidor
        ArrayList<Pacote> pacotes = (ArrayList<Pacote>) Util.lerObjecto(socket.getInputStream());
        

        pacotesRepository.addAll(pacotes);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Não foi possível sincronizar com servidor");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jpDashboard = new javax.swing.JPanel();
        jpUsuario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblUsuarioLogado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalEmails = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCaixaEntrada = new javax.swing.JLabel();
        lblNovaMensagem = new javax.swing.JLabel();
        lblContatos = new javax.swing.JLabel();
        lblOpcoes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(127, 200, 237));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(128, 128, 128));

        jpDashboard.setBackground(new java.awt.Color(255, 255, 255));

        jpUsuario.setBackground(new java.awt.Color(127, 200, 237));
        jpUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jpUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/contatos.png"))); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(45, 45));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Usuário Logado:");

        lblUsuarioLogado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUsuarioLogado.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioLogado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpUsuarioLayout = new javax.swing.GroupLayout(jpUsuario);
        jpUsuario.setLayout(jpUsuarioLayout);
        jpUsuarioLayout.setHorizontalGroup(
            jpUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsuarioLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUsuarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(lblUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jpUsuarioLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jpUsuarioLayout.setVerticalGroup(
            jpUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpUsuarioLayout.createSequentialGroup()
                .addGroup(jpUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        jPanel3.setBackground(new java.awt.Color(94, 193, 171));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/@48.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total emails");

        lblTotalEmails.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTotalEmails.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalEmails.setText("10");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lblTotalEmails)))
                .addGap(0, 101, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalEmails)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpDashboardLayout = new javax.swing.GroupLayout(jpDashboard);
        jpDashboard.setLayout(jpDashboardLayout);
        jpDashboardLayout.setHorizontalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jpUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jpDashboardLayout.setVerticalGroup(
            jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDashboardLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jpDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dashboard");

        lblCaixaEntrada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCaixaEntrada.setForeground(new java.awt.Color(255, 255, 255));
        lblCaixaEntrada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCaixaEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/caixa-entrada-48.png"))); // NOI18N
        lblCaixaEntrada.setText("Caixa entrada");
        lblCaixaEntrada.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblCaixaEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCaixaEntradaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCaixaEntradaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCaixaEntradaMouseExited(evt);
            }
        });

        lblNovaMensagem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNovaMensagem.setForeground(new java.awt.Color(255, 255, 255));
        lblNovaMensagem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNovaMensagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/new-message.png"))); // NOI18N
        lblNovaMensagem.setText("Nova mensagem");
        lblNovaMensagem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblNovaMensagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNovaMensagemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseMove(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblNovaMensagemMouseExited(evt);
            }
        });

        lblContatos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblContatos.setForeground(new java.awt.Color(255, 255, 255));
        lblContatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblContatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/contatos.png"))); // NOI18N
        lblContatos.setText("Contatos");
        lblContatos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblContatosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblContatosMouseExited(evt);
            }
        });

        lblOpcoes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblOpcoes.setForeground(new java.awt.Color(255, 255, 255));
        lblOpcoes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOpcoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/opcoes.png"))); // NOI18N
        lblOpcoes.setText("Opções");
        lblOpcoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblOpcoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpcoesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblOpcoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblOpcoesMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNovaMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblContatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCaixaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lblCaixaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lblNovaMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 168, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jpDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mouseMove(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseMove
        lblNovaMensagem.setSize(164, 49);
    }//GEN-LAST:event_mouseMove

    private void lblNovaMensagemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNovaMensagemMouseExited
        lblNovaMensagem.setSize(160, 45);
    }//GEN-LAST:event_lblNovaMensagemMouseExited

    private void lblCaixaEntradaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCaixaEntradaMouseEntered
        lblCaixaEntrada.setSize(164, 49);
    }//GEN-LAST:event_lblCaixaEntradaMouseEntered

    private void lblCaixaEntradaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCaixaEntradaMouseExited
        lblCaixaEntrada.setSize(160, 45);
    }//GEN-LAST:event_lblCaixaEntradaMouseExited

    private void lblContatosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContatosMouseEntered
        lblContatos.setSize(164, 49);
    }//GEN-LAST:event_lblContatosMouseEntered

    private void lblContatosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContatosMouseExited
        lblContatos.setSize(160, 45);
    }//GEN-LAST:event_lblContatosMouseExited

    private void lblOpcoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpcoesMouseEntered
        lblOpcoes.setSize(164, 49);
    }//GEN-LAST:event_lblOpcoesMouseEntered

    private void lblOpcoesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpcoesMouseExited
        lblOpcoes.setSize(160, 45);
    }//GEN-LAST:event_lblOpcoesMouseExited

    private void lblOpcoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpcoesMouseClicked
        
    }//GEN-LAST:event_lblOpcoesMouseClicked

    private void lblCaixaEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCaixaEntradaMouseClicked
        this.setEnabled(false);
        new CaixaEntradaView(this.usuario);
    }//GEN-LAST:event_lblCaixaEntradaMouseClicked

    private void lblNovaMensagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNovaMensagemMouseClicked
        this.setEnabled(false);
        new NovaMensagem(this.usuario);
    }//GEN-LAST:event_lblNovaMensagemMouseClicked

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jpDashboard;
    private javax.swing.JPanel jpUsuario;
    private javax.swing.JLabel lblCaixaEntrada;
    private javax.swing.JLabel lblContatos;
    private javax.swing.JLabel lblNovaMensagem;
    private javax.swing.JLabel lblOpcoes;
    private javax.swing.JLabel lblTotalEmails;
    private javax.swing.JLabel lblUsuarioLogado;
    // End of variables declaration//GEN-END:variables
}
