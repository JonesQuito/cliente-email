package com.roboquito.email.cliente.view;

import com.roboquito.email.cliente.controller.DashboardController;
import com.roboquito.email.cliente.model.PacotesRepository;
import com.roboquito.email.cliente.service.Arquivo;
import com.roboquito.email.cliente.service.CriptografiaAES;
import com.roboquito.email.cliente.service.CriptografiaRSA;
import com.roboquito.email.cliente.service.Util;
import com.roboquito.email.model.Cliente;
import com.roboquito.email.model.Pacote;
import com.roboquito.email.model.ServerMethods;
import java.awt.CardLayout;
import java.net.Socket;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public final class Dashboard extends javax.swing.JFrame {

    DashboardController dashboardController;
    private static Dashboard instanceDashboard;
    private Cliente usuario = null;
    PacotesRepository pacotesRepository;
    PainelCaixaEntrada painelCaixaEntrada = new PainelCaixaEntrada();
    PainelDashboard painelDashboard = new PainelDashboard();
    DefaultTableModel dtmEmails;

    public static Dashboard getInstance(Cliente usuario) {
        if (instanceDashboard == null) {
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
        dtmEmails = (DefaultTableModel) jTableEmails.getModel();

        lblTotalEmails.setText(Integer.toString(pacotesRepository.getAllPackages().size()));

        clearTable();
        try {
            atualizarCaixaEntrada();

            // Configura a largura da segunda coluna
        } catch (Exception ex) {
            Logger.getLogger(CaixaEntradaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sincronizarServidor() {
        // Cria um pacote e define o método que o servidor deve executar ao recebê-lo
        Pacote pacote = new Pacote();
        pacote.setMetodo(ServerMethods.GET_OBJECTS_BYADDRESSEE);
        pacote.setDestinatario(this.usuario.getEmail());

        try {
            // Estabelece conexão via socket e envia o objeto pacate para o servidor
            Socket socket = new Socket("127.0.0.1", 5000);
            Util.enviarObjeto(pacote, socket.getOutputStream());

            // Ler o pocote enviado pelo servidor
            ArrayList<Pacote> pacotes = (ArrayList<Pacote>) Util.lerObjecto(socket.getInputStream());

            pacotesRepository.addAll(pacotes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possível sincronizar com servidor");
        }
    }

    public void atualizarCaixaEntrada() {

        for (Pacote p : pacotesRepository.getAllPackages()) {
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

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível obter a chave para decifrar a mensagem: ");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jpConteudo = new javax.swing.JPanel();
        jpDashboard = new javax.swing.JPanel();
        jpUsuario = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblUsuarioLogado = new javax.swing.JLabel();
        jpTotalEmail = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalEmails = new javax.swing.JLabel();
        jpCaixaEntrada = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmails = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensagem = new javax.swing.JTextArea();
        jpContatos = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jpOpcoes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblCaixaEntrada = new javax.swing.JLabel();
        lblNovaMensagem = new javax.swing.JLabel();
        lblContatos = new javax.swing.JLabel();
        lblOpcoes = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(127, 200, 237));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(128, 128, 128));

        jpConteudo.setLayout(new java.awt.CardLayout());

        jpDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jpDashboard.setAlignmentX(5.0F);
        jpDashboard.setAlignmentY(5.0F);
        jpDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jpUsuario.setBackground(new java.awt.Color(127, 200, 237));
        jpUsuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(lblUsuarioLogado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        jpDashboard.add(jpUsuario);

        jpTotalEmail.setBackground(new java.awt.Color(94, 193, 171));
        jpTotalEmail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpTotalEmail.setForeground(new java.awt.Color(255, 255, 255));
        jpTotalEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/@48.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total emails");

        lblTotalEmails.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTotalEmails.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalEmails.setText("10");

        javax.swing.GroupLayout jpTotalEmailLayout = new javax.swing.GroupLayout(jpTotalEmail);
        jpTotalEmail.setLayout(jpTotalEmailLayout);
        jpTotalEmailLayout.setHorizontalGroup(
            jpTotalEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalEmailLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGroup(jpTotalEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTotalEmailLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel6))
                    .addGroup(jpTotalEmailLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lblTotalEmails)))
                .addGap(0, 110, Short.MAX_VALUE))
        );
        jpTotalEmailLayout.setVerticalGroup(
            jpTotalEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpTotalEmailLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalEmails)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jpDashboard.add(jpTotalEmail);

        jpConteudo.add(jpDashboard, "cardDashboard");

        jpCaixaEntrada.setBackground(new java.awt.Color(255, 255, 255));

        jTableEmails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Remetente", "Assunto", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEmails.getTableHeader().setReorderingAllowed(false);
        jTableEmails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmailsMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableEmailsMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEmails);

        txtMensagem.setBackground(new java.awt.Color(240, 240, 240));
        txtMensagem.setColumns(20);
        txtMensagem.setForeground(new java.awt.Color(102, 51, 255));
        txtMensagem.setLineWrap(true);
        txtMensagem.setRows(5);
        txtMensagem.setAlignmentX(10.0F);
        txtMensagem.setAlignmentY(10.0F);
        jScrollPane2.setViewportView(txtMensagem);

        javax.swing.GroupLayout jpCaixaEntradaLayout = new javax.swing.GroupLayout(jpCaixaEntrada);
        jpCaixaEntrada.setLayout(jpCaixaEntradaLayout);
        jpCaixaEntradaLayout.setHorizontalGroup(
            jpCaixaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
            .addGroup(jpCaixaEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jpCaixaEntradaLayout.setVerticalGroup(
            jpCaixaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCaixaEntradaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpConteudo.add(jpCaixaEntrada, "cardCaixaEntrada");

        jpContatos.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nome:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Telefone:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setText("Registrar");

        jButton3.setText("Cancelar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addComponent(jTextField2)
                        .addComponent(jTextField3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Email", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jpContatosLayout = new javax.swing.GroupLayout(jpContatos);
        jpContatos.setLayout(jpContatosLayout);
        jpContatosLayout.setHorizontalGroup(
            jpContatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpContatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpContatosLayout.setVerticalGroup(
            jpContatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpConteudo.add(jpContatos, "cardContatos");

        jpOpcoes.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpOpcoesLayout = new javax.swing.GroupLayout(jpOpcoes);
        jpOpcoes.setLayout(jpOpcoesLayout);
        jpOpcoesLayout.setHorizontalGroup(
            jpOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );
        jpOpcoesLayout.setVerticalGroup(
            jpOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jpConteudo.add(jpOpcoes, "cardOpcoes");

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
        lblCaixaEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblNovaMensagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblContatos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblContatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContatosMouseClicked(evt);
            }
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
        lblOpcoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        lblDashboard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(255, 255, 255));
        lblDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/criptografia/images/dashboard.png"))); // NOI18N
        lblDashboard.setText("Dashboard");
        lblDashboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDashboard.setPreferredSize(new java.awt.Dimension(144, 52));
        lblDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDashboardMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNovaMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCaixaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(lblDashboard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(509, Short.MAX_VALUE))
                    .addComponent(jpConteudo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblCaixaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lblNovaMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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
        CardLayout cl = (CardLayout) jpConteudo.getLayout();
        cl.show(jpConteudo, "cardOpcoes");
    }//GEN-LAST:event_lblOpcoesMouseClicked

    private void lblCaixaEntradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCaixaEntradaMouseClicked
        CardLayout cl = (CardLayout) jpConteudo.getLayout();
        cl.show(jpConteudo, "cardCaixaEntrada");

    }//GEN-LAST:event_lblCaixaEntradaMouseClicked

    private void lblNovaMensagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNovaMensagemMouseClicked
        this.setEnabled(false);
        new NovaMensagem(this.usuario);
    }//GEN-LAST:event_lblNovaMensagemMouseClicked

    private void lblDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseClicked
        CardLayout cl = (CardLayout) jpConteudo.getLayout();
        cl.show(jpConteudo, "cardDashboard");


    }//GEN-LAST:event_lblDashboardMouseClicked

    private void lblDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseEntered
        lblDashboard.setSize(164, 49);
    }//GEN-LAST:event_lblDashboardMouseEntered

    private void lblDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDashboardMouseExited
        lblDashboard.setSize(160, 45);
    }//GEN-LAST:event_lblDashboardMouseExited

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void lblContatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContatosMouseClicked
        CardLayout cl = (CardLayout) jpConteudo.getLayout();
        cl.show(jpConteudo, "cardContatos");
    }//GEN-LAST:event_lblContatosMouseClicked

    private void jTableEmailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmailsMouseClicked
        if (evt.getClickCount() == 2) {

            abrirMensagem();
        }
    }//GEN-LAST:event_jTableEmailsMouseClicked

    private void jTableEmailsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmailsMouseReleased
        if (jTableEmails.getSelectedRow() != -1) {
            if (evt.isPopupTrigger()) {
                   //Implementar
            }
        }

    }//GEN-LAST:event_jTableEmailsMouseReleased

    public static void main(String args[]) {

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
        /*
         Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });*/

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableEmails;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel jpCaixaEntrada;
    private javax.swing.JPanel jpContatos;
    private javax.swing.JPanel jpConteudo;
    private javax.swing.JPanel jpDashboard;
    private javax.swing.JPanel jpOpcoes;
    private javax.swing.JPanel jpTotalEmail;
    private javax.swing.JPanel jpUsuario;
    private javax.swing.JLabel lblCaixaEntrada;
    private javax.swing.JLabel lblContatos;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblNovaMensagem;
    private javax.swing.JLabel lblOpcoes;
    private javax.swing.JLabel lblTotalEmails;
    private javax.swing.JLabel lblUsuarioLogado;
    private javax.swing.JTextArea txtMensagem;
    // End of variables declaration//GEN-END:variables
}
