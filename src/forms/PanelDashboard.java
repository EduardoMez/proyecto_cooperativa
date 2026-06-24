
package forms;

import Clases.Sesion;
import java.awt.Image;
import javax.swing.ImageIcon;

public class PanelDashboard extends javax.swing.JPanel {

    public PanelDashboard() {
        initComponents();
        lblUsuario.setText(
                Sesion.getUsuarioActual().getNombre() + "!"
        );

        ImageIcon icono = new ImageIcon(getClass().getResource("/Iconos/Logo.jpeg"));
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(500, 350, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(imagenEscalada));
        
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblBienvenida1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(0, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setText(".");
        add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 480, 430));

        lblBienvenida1.setFont(new java.awt.Font("Segoe UI", 1, 50)); // NOI18N
        lblBienvenida1.setText("¡Bienvenido,");
        add(lblBienvenida1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 350, 50));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(243, 141, 22));
        add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 570, 50));

        jPanel1.setBackground(new java.awt.Color(59, 116, 231));
        jPanel1.setPreferredSize(new java.awt.Dimension(270, 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 205, 5));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nos alegra tenerte de vuelta");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 290, 30));

        jPanel2.setBackground(new java.awt.Color(230, 236, 247));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("de formar rapida y segura.");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 290, 30));

        jLabel2.setForeground(new java.awt.Color(55, 71, 79));
        jLabel2.setText("Cooperativa - Sistema de Gestión Contable");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 240, 30));

        jLabel7.setForeground(new java.awt.Color(55, 71, 79));
        jLabel7.setText("La información de tu cooperativa esta segura con nosotros");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 460, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proteger.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/simbolo-de-copyright.png"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, -1, -1));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 680, 1040, 33));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Desde aquí puedes gestionar la información de tu cooperativa");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 450, 30));

        jPanel3.setBackground(new java.awt.Color(230, 236, 247));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(55, 71, 79));
        jLabel8.setText("de nuestra comunidad!");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 240, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(55, 71, 79));
        jLabel9.setText("¡Trabajamos juntos por el bienestar");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 240, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/apreton-de-manos.png"))); // NOI18N
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 360, 80));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("de formar rapida y segura.");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 290, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel lblBienvenida1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
