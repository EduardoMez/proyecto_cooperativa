package forms;
/**
 *
 * @author luisc
 */
import Clases.Cuenta;
import Clases.CuentaDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelCuentas extends javax.swing.JPanel {

    DefaultTableModel modeloCuentas;
    private CuentaDAO dao = new CuentaDAO();

    public PanelCuentas() {
        initComponents();
        this.cargarTabla(dao.listarCuentas());
    }

    public void cargarTabla(List<Cuenta> lista) {

        String[] columnas = {"Id", "Código", "Nombre", "Tipo","Naturaleza", "Clasificación", "Nivel"};

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        for (Cuenta c : lista) {
            Object[] fila = {
                c.getId(),
                c.getCodigo(),
                c.getNombre(),
                c.getTipo(),
                c.getNaturaleza(),
                c.getClasificacion(),
                c.getNivel()
            };

            modelo.addRow(fila);
        }

        tablaCuentas.setModel(modelo);

        // ALTURA FILAS
        tablaCuentas.setRowHeight(35);

        // FUENTE
        tablaCuentas.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // ENCABEZADO
        tablaCuentas.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13));
        tablaCuentas.getTableHeader().setBackground(
                new Color(220, 220, 220));
        tablaCuentas.getTableHeader().setForeground(Color.BLACK);

        // COLORES ALTERNADOS
        tablaCuentas.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected,
                        hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(230, 245, 255));
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                }
                return c;
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gtupo = new javax.swing.ButtonGroup();
        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelContenidoCuentas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCuentas = new javax.swing.JTable();
        controles = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnGasto = new javax.swing.JRadioButton();
        rbtnActivo = new javax.swing.JRadioButton();
        rbtnPasivo = new javax.swing.JRadioButton();
        rbtnPatrimonio = new javax.swing.JRadioButton();
        rbtnIngreso = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();

        setLayout(new java.awt.BorderLayout());

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        panelTitulo.setPreferredSize(new java.awt.Dimension(1270, 80));
        panelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Catálogo de cuentas");
        panelTitulo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 35));

        jPanel1.setBackground(new java.awt.Color(59, 116, 231));
        jPanel1.setPreferredSize(new java.awt.Dimension(270, 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelTitulo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 280, 5));

        add(panelTitulo, java.awt.BorderLayout.NORTH);

        panelContenidoCuentas.setBackground(new java.awt.Color(255, 255, 255));
        panelContenidoCuentas.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(35, 30, 25, 30));

        tablaCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaCuentas);

        panelContenidoCuentas.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        controles.setBackground(new java.awt.Color(255, 255, 255));
        controles.setPreferredSize(new java.awt.Dimension(469, 34));
        controles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Buscar:");
        controles.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(64, 22));
        controles.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 260, -1));

        gtupo.add(btnGasto);
        btnGasto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnGasto.setText("Gasto");
        btnGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGastoActionPerformed(evt);
            }
        });
        controles.add(btnGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, -1, -1));

        gtupo.add(rbtnActivo);
        rbtnActivo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbtnActivo.setText("Activo");
        rbtnActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnActivoActionPerformed(evt);
            }
        });
        controles.add(rbtnActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        gtupo.add(rbtnPasivo);
        rbtnPasivo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbtnPasivo.setText("Pasivo");
        rbtnPasivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPasivoActionPerformed(evt);
            }
        });
        controles.add(rbtnPasivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, -1));

        gtupo.add(rbtnPatrimonio);
        rbtnPatrimonio.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbtnPatrimonio.setText("Patrimonio");
        rbtnPatrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPatrimonioActionPerformed(evt);
            }
        });
        controles.add(rbtnPatrimonio, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, -1));

        gtupo.add(rbtnIngreso);
        rbtnIngreso.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        rbtnIngreso.setText("Ingreso");
        rbtnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnIngresoActionPerformed(evt);
            }
        });
        controles.add(rbtnIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, -1, -1));

        gtupo.add(jRadioButton1);
        jRadioButton1.setText("Todas");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        controles.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, -1, -1));

        panelContenidoCuentas.add(controles, java.awt.BorderLayout.PAGE_START);

        add(panelContenidoCuentas, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnActivoActionPerformed
        cargarTabla(dao.listarCuentasPorTipo("Activo"));
    }//GEN-LAST:event_rbtnActivoActionPerformed

    private void rbtnPasivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPasivoActionPerformed
       cargarTabla(dao.listarCuentasPorTipo("Pasivo"));
    }//GEN-LAST:event_rbtnPasivoActionPerformed

    private void rbtnPatrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPatrimonioActionPerformed
     cargarTabla(dao.listarCuentasPorTipo("Patrimonio"));
    }//GEN-LAST:event_rbtnPatrimonioActionPerformed

    private void rbtnIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnIngresoActionPerformed
      cargarTabla(dao.listarCuentasPorTipo("Ingreso"));
    }//GEN-LAST:event_rbtnIngresoActionPerformed

    private void btnGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGastoActionPerformed
        cargarTabla(dao.listarCuentasPorTipo("Gasto"));
    }//GEN-LAST:event_btnGastoActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
       cargarTabla(dao.listarCuentas());
    }//GEN-LAST:event_jRadioButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnGasto;
    private javax.swing.JPanel controles;
    private javax.swing.ButtonGroup gtupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelContenidoCuentas;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JRadioButton rbtnActivo;
    private javax.swing.JRadioButton rbtnIngreso;
    private javax.swing.JRadioButton rbtnPasivo;
    private javax.swing.JRadioButton rbtnPatrimonio;
    private javax.swing.JTable tablaCuentas;
    // End of variables declaration//GEN-END:variables
}
