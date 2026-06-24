package forms;
import Clases.Sesion;
import javax.swing.JPanel;
public class Principal extends javax.swing.JFrame {
    private boolean menuAbierto = true;
    private final int SIDEBAR_ABIERTO = 240;
    private final int SIDEBAR_CERRADO = 60;
    private JPanel panelActivo = null; // nueva variable de instancia

    public Principal() {
        initComponents();
        mostrarPanel(new PanelDashboard());
        this.setSize(1366, 768);
        setLocationRelativeTo(null);
       
        // Cursor de manita al pasar sobre el label
       lblMenu.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
     
    }

    private void toggleSidebar() {
        menuAbierto = !menuAbierto;
        int anchoMenu = menuAbierto ? SIDEBAR_ABIERTO : SIDEBAR_CERRADO;
        String texto = menuAbierto ? "Dashboard" : "";

        // 1. Cambiamos el tamaño preferido del panel (BorderLayout lo respetará)
        panelMenu.setPreferredSize(new java.awt.Dimension(anchoMenu, panelMenu.getHeight()));

        // 2. Actualizamos los textos de los botones
       
        btnCuentas.setText(menuAbierto ? "Cuentas" : "");
        btnDiario.setText(menuAbierto ? "Partidas Diarias" : "");
        btnMayor.setText(menuAbierto ? "Libro Mayor" : "");
        btnBalanza.setText(menuAbierto ? "Bal.Comprobacion" : "");
        btnEstados.setText(menuAbierto ? "Estados financieros" : "");
        btnCierre.setText(menuAbierto ? "Cierre" : "");

        // 3. ¡LA MAGIA! Refrescamos el layout del JFrame
        // Esto hace que el PanelContenido (CENTER) se estire automáticamente
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void mostrarPanel(JPanel p) {
        panelActivo = p;
        PanelContenido.removeAll();
        p.setVisible(true);
        // En BorderLayout, el componente en CENTER se estira automáticamente
        PanelContenido.add(p, java.awt.BorderLayout.CENTER);

        PanelContenido.revalidate();
        PanelContenido.repaint();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();
        btnMayor = new javax.swing.JButton();
        btnDiario = new javax.swing.JButton();
        btnCierre = new javax.swing.JButton();
        btnBalanza = new javax.swing.JButton();
        btnEstados = new javax.swing.JButton();
        btnCuentas = new javax.swing.JButton();
        lblMenu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PanelContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);

        panelMenu.setBackground(new java.awt.Color(59, 116, 231));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMayor.setBackground(new java.awt.Color(0, 204, 102));
        btnMayor.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnMayor.setForeground(new java.awt.Color(255, 255, 255));
        btnMayor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/libro-abierto.png"))); // NOI18N
        btnMayor.setText("Libro Mayor");
        btnMayor.setBorderPainted(false);
        btnMayor.setContentAreaFilled(false);
        btnMayor.setFocusPainted(false);
        btnMayor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMayor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMayor.setIconTextGap(15);
        btnMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMayorActionPerformed(evt);
            }
        });
        panelMenu.add(btnMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 220, 45));

        btnDiario.setBackground(new java.awt.Color(0, 204, 102));
        btnDiario.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnDiario.setForeground(new java.awt.Color(255, 255, 255));
        btnDiario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/notas (1).png"))); // NOI18N
        btnDiario.setText("Partidas Diarias");
        btnDiario.setBorderPainted(false);
        btnDiario.setContentAreaFilled(false);
        btnDiario.setFocusPainted(false);
        btnDiario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDiario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDiario.setIconTextGap(15);
        btnDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiarioActionPerformed(evt);
            }
        });
        panelMenu.add(btnDiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 260, 220, 45));

        btnCierre.setBackground(new java.awt.Color(0, 204, 102));
        btnCierre.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnCierre.setForeground(new java.awt.Color(255, 255, 255));
        btnCierre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-sesion.png"))); // NOI18N
        btnCierre.setText("Cierre");
        btnCierre.setBorderPainted(false);
        btnCierre.setContentAreaFilled(false);
        btnCierre.setFocusPainted(false);
        btnCierre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCierre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCierre.setIconTextGap(15);
        btnCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCierreActionPerformed(evt);
            }
        });
        panelMenu.add(btnCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 500, 200, 45));

        btnBalanza.setBackground(new java.awt.Color(0, 204, 102));
        btnBalanza.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnBalanza.setForeground(new java.awt.Color(255, 255, 255));
        btnBalanza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/balanza.png"))); // NOI18N
        btnBalanza.setText("Bal.Comprobacion");
        btnBalanza.setBorderPainted(false);
        btnBalanza.setContentAreaFilled(false);
        btnBalanza.setFocusPainted(false);
        btnBalanza.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBalanza.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBalanza.setIconTextGap(15);
        btnBalanza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBalanzaActionPerformed(evt);
            }
        });
        panelMenu.add(btnBalanza, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 220, 45));

        btnEstados.setBackground(new java.awt.Color(0, 204, 102));
        btnEstados.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnEstados.setForeground(new java.awt.Color(255, 255, 255));
        btnEstados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/barra-grafica.png"))); // NOI18N
        btnEstados.setText("Estados financieros ");
        btnEstados.setToolTipText("");
        btnEstados.setBorderPainted(false);
        btnEstados.setContentAreaFilled(false);
        btnEstados.setFocusPainted(false);
        btnEstados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEstados.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEstados.setIconTextGap(15);
        btnEstados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadosActionPerformed(evt);
            }
        });
        panelMenu.add(btnEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 220, 45));

        btnCuentas.setBackground(new java.awt.Color(0, 204, 102));
        btnCuentas.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnCuentas.setForeground(new java.awt.Color(255, 255, 255));
        btnCuentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/banco.png"))); // NOI18N
        btnCuentas.setText("Cuentas");
        btnCuentas.setContentAreaFilled(false);
        btnCuentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCuentas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCuentas.setIconTextGap(15);
        btnCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentasActionPerformed(evt);
            }
        });
        panelMenu.add(btnCuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 220, 45));

        lblMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/lista.png"))); // NOI18N
        lblMenu.setText("    Menu");
        lblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuMouseClicked(evt);
            }
        });
        panelMenu.add(lblMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 140, 90, 40));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cooperativa");
        panelMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        getContentPane().add(panelMenu, java.awt.BorderLayout.WEST);

        PanelContenido.setLayout(new java.awt.BorderLayout());
        getContentPane().add(PanelContenido, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentasActionPerformed
      mostrarPanel(new PanelCuentas());       
    }//GEN-LAST:event_btnCuentasActionPerformed

    private void btnMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMayorActionPerformed
       mostrarPanel(new PanelLibroMayor());
    }//GEN-LAST:event_btnMayorActionPerformed

    private void btnDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiarioActionPerformed
      mostrarPanel(new PanelPartidas());
    }//GEN-LAST:event_btnDiarioActionPerformed

    private void btnCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCierreActionPerformed
       // mostrarPanel(new panelCierre());
    }//GEN-LAST:event_btnCierreActionPerformed

    private void btnBalanzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBalanzaActionPerformed
       mostrarPanel(new PanelBalanza());
    }//GEN-LAST:event_btnBalanzaActionPerformed

    private void lblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuMouseClicked
      toggleSidebar();
    }//GEN-LAST:event_lblMenuMouseClicked

    private void btnEstadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadosActionPerformed
         mostrarPanel(new PanelEstados());
    }//GEN-LAST:event_btnEstadosActionPerformed

 
    
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelContenido;
    private javax.swing.JButton btnBalanza;
    private javax.swing.JButton btnCierre;
    private javax.swing.JButton btnCuentas;
    private javax.swing.JButton btnDiario;
    private javax.swing.JButton btnEstados;
    private javax.swing.JButton btnMayor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
