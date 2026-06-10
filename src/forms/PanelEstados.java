
package forms;

import Clases.Estado;
import Clases.EstadoDAO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisc
 */
public class PanelEstados extends javax.swing.JPanel {

    
    public PanelEstados() {
        initComponents();
        dao = new EstadoDAO();
        cmbEstado.addItem("Estado de Resultados");
        cmbEstado.addItem("Balance General");
        configurarTabla();
        
        
    }
    
       private EstadoDAO dao;
       DefaultTableModel modelo = new DefaultTableModel();
      
    
    private void configurarTabla() {

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Código");
        modelo.addColumn("Cuenta");
        modelo.addColumn("Saldo");
        tblEstado.setModel(modelo);

        // Altura de filas
        tblEstado.setRowHeight(30);

        // Encabezado en negrita
        tblEstado.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13)
        );

        // Renderizador para filas alternadas
        tblEstado.setDefaultRenderer(Object.class,
                new javax.swing.table.DefaultTableCellRenderer() {

            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,Object value, boolean isSelected, boolean hasFocus, int row,int column) {

                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
                if (!isSelected) {

                    // Filas pares blancas
                    if (row % 2 == 0) {
                        c.setBackground(java.awt.Color.WHITE);
                    } // Filas impares azul claro
                    else {
                        c.setBackground(
                                new java.awt.Color(230, 245, 255));
                    }
                    c.setForeground(java.awt.Color.BLACK);
                }
                return c;
            }
        });
        
        tblEstado.getColumnModel().getColumn(0).setPreferredWidth(10); // Código
        tblEstado.getColumnModel().getColumn(1).setPreferredWidth(45); // Cuenta
        tblEstado.getColumnModel().getColumn(2).setPreferredWidth(15); // Saldo
    }

    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstado = new javax.swing.JTable();
        ftxtFechaInicio = new javax.swing.JFormattedTextField();
        ftxtFechaFin = new javax.swing.JFormattedTextField();
        btnProcesar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1270, 100));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.BorderLayout());

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        panelTitulo.setPreferredSize(new java.awt.Dimension(1270, 80));
        panelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Estados Financieros");
        panelTitulo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 380, 35));

        jPanel1.setBackground(new java.awt.Color(59, 116, 231));
        jPanel1.setPreferredSize(new java.awt.Dimension(270, 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelTitulo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 375, 5));

        add(panelTitulo, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });
        jPanel2.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 270, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel2.setText("Seleccione: ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        tblEstado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblEstado);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 780, 420));

        try {
            ftxtFechaInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftxtFechaInicio.setPreferredSize(new java.awt.Dimension(120, 28));
        ftxtFechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtFechaInicioActionPerformed(evt);
            }
        });
        jPanel2.add(ftxtFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 90, 30));

        try {
            ftxtFechaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftxtFechaFin.setPreferredSize(new java.awt.Dimension(120, 28));
        ftxtFechaFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtFechaFinActionPerformed(evt);
            }
        });
        jPanel2.add(ftxtFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 90, 30));

        btnProcesar.setBackground(new java.awt.Color(0, 0, 153));
        btnProcesar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnProcesar.setForeground(new java.awt.Color(255, 255, 255));
        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        jPanel2.add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 130, 30));

        jLabel4.setText("Fecha Inicio:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        jLabel3.setText("Fecha Fin:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, -1));

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    
    private void cargarEstadoResultados(
        String fechaInicio,
        String fechaFin) {

    modelo.setRowCount(0);

    List<Estado> lista =
            dao.obtenerSaldos(
                    fechaInicio,
                    fechaFin);

    BigDecimal ingresos = BigDecimal.ZERO;
    BigDecimal gastos = BigDecimal.ZERO;

    modelo.addRow(new Object[]{"", "INGRESOS", ""});

    for (Estado e : lista) {

        if ("Ingreso".equals(e.getTipo())) {

            modelo.addRow(new Object[]{
                e.getCodigo(),
                e.getNombre(),
                e.getSaldo()
            });

            ingresos = ingresos.add(e.getSaldo());
        }
    }

    modelo.addRow(new Object[]{
        "",
        "TOTAL INGRESOS",
        ingresos
    });

    modelo.addRow(new Object[]{"", "", ""});

    modelo.addRow(new Object[]{
        "",
        "GASTOS",
        ""
    });

    for (Estado e : lista) {

        if ("Gasto".equals(e.getTipo())) {

            modelo.addRow(new Object[]{
                e.getCodigo(),
                e.getNombre(),
                e.getSaldo()
            });

            gastos = gastos.add(e.getSaldo());
        }
    }

    modelo.addRow(new Object[]{
        "",
        "TOTAL GASTOS",
        gastos
    });

    BigDecimal utilidad =
            ingresos.subtract(gastos);

    modelo.addRow(new Object[]{"","",""});

    modelo.addRow(new Object[]{
        "",
        "EXCEDENTE / DÉFICIT DEL EJERCICIO",
        utilidad
    });
}
    
    
    private void cargarBalanceGeneral(
        String fechaInicio,
        String fechaFin) {

    modelo.setRowCount(0);

    List<Estado> lista =
            dao.obtenerSaldos(
                    fechaInicio,
                    fechaFin);

    BigDecimal activos = BigDecimal.ZERO;
    BigDecimal pasivos = BigDecimal.ZERO;
    BigDecimal patrimonio = BigDecimal.ZERO;

    BigDecimal ingresos = BigDecimal.ZERO;
    BigDecimal gastos = BigDecimal.ZERO;

    // ACTIVOS
    modelo.addRow(new Object[]{"", "ACTIVOS", ""});

    for (Estado e : lista) {

        if ("Activo".equals(e.getTipo())) {

            modelo.addRow(new Object[]{
                e.getCodigo(),
                e.getNombre(),
                e.getSaldo()
            });

            activos = activos.add(e.getSaldo());
        }

        // Para calcular el resultado del ejercicio
        if ("Ingreso".equals(e.getTipo())) {
            ingresos = ingresos.add(e.getSaldo());
        }

        if ("Gasto".equals(e.getTipo())) {
            gastos = gastos.add(e.getSaldo());
        }
    }

    modelo.addRow(new Object[]{
        "",
        "TOTAL ACTIVOS",
        activos
    });

    modelo.addRow(new Object[]{"", "", ""});

    // PASIVOS

    modelo.addRow(new Object[]{"", "PASIVOS", ""});

    for (Estado e : lista) {

        if ("Pasivo".equals(e.getTipo())) {

            modelo.addRow(new Object[]{
                e.getCodigo(),
                e.getNombre(),
                e.getSaldo()
            });

            pasivos = pasivos.add(e.getSaldo());
        }
    }

    modelo.addRow(new Object[]{
        "",
        "TOTAL PASIVOS",
        pasivos
    });

    modelo.addRow(new Object[]{"", "", ""});

    // PATRIMONIO
 

    modelo.addRow(new Object[]{"", "PATRIMONIO", ""});

    for (Estado e : lista) {

        if ("Patrimonio".equals(e.getTipo())) {

            modelo.addRow(new Object[]{
                e.getCodigo(),
                e.getNombre(),
                e.getSaldo()
            });

            patrimonio = patrimonio.add(e.getSaldo());
        }
    }

    // Resultado del ejercicio
    BigDecimal resultadoEjercicio =
            ingresos.subtract(gastos);

    modelo.addRow(new Object[]{
        "",
        "RESULTADO DEL EJERCICIO",
        resultadoEjercicio
    });

    patrimonio =
            patrimonio.add(resultadoEjercicio);

    modelo.addRow(new Object[]{
        "",
        "TOTAL PATRIMONIO",
        patrimonio
    });

    modelo.addRow(new Object[]{"", "", ""});

    // =====================
    // VERIFICACIÓN
    // =====================

    modelo.addRow(new Object[]{
        "",
        "PASIVO + PATRIMONIO",
        pasivos.add(patrimonio)
    });

    modelo.addRow(new Object[]{
        "",
        "DIFERENCIA",
        activos.subtract(
                pasivos.add(patrimonio))
    });
}
    private void ftxtFechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFechaInicioActionPerformed

    private void ftxtFechaFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFechaFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFechaFinActionPerformed

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
       try {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date inicio = sdf.parse(ftxtFechaInicio.getText());
        java.util.Date fin =sdf.parse(ftxtFechaFin.getText());

        SimpleDateFormat formatoSQL =new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicio =formatoSQL.format(inicio);
        String fechaFin =formatoSQL.format(fin);

            if (cmbEstado.getSelectedIndex() == 0) {
                cargarEstadoResultados(fechaInicio, fechaFin);

            } else {
                cargarBalanceGeneral(fechaInicio, fechaFin);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnProcesarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProcesar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JFormattedTextField ftxtFechaFin;
    private javax.swing.JFormattedTextField ftxtFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTable tblEstado;
    // End of variables declaration//GEN-END:variables
}
