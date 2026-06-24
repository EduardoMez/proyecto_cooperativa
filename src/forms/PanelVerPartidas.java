
package forms;
import Clases.Asiento;
import Clases.AsientoDAO;
import Clases.Partida;
import Clases.PartidaDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PanelVerPartidas extends javax.swing.JPanel {

    private final AsientoDAO asientoDAO;
    private final PartidaDAO partidaDAO;
    private List<Asiento> listaAsientosActuales;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public PanelVerPartidas() {
        initComponents();
        asientoDAO = new AsientoDAO();
        partidaDAO = new PartidaDAO();
        configurarUIInicial();
    }
    
 private void configurarUIInicial() {
        // Bloqueamos la edición de campos que solo son de lectura en este panel
        txtNumeroAsiento.setEditable(false);
        ftxtFecha.setEditable(false);
        txtConcepto.setEditable(false);
        txtTipoActual.setEditable(false);
        txtEstadoActual.setEditable(false);
        
        // Inicializar tablas con sus columnas correspondientes
        limpiarFormulario();
        
        // Asignar el evento de selección a la tabla de Asientos de forma manual
        tblAsientos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAsientosMouseClicked(evt);
            }
        });
        
        // Asignar evento al botón buscar
        btnBuscarAsientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAsientosActionPerformed(evt);
            }
        });
    }

    private void limpiarFormulario() {
        txtNumeroAsiento.setText("");
        ftxtFecha.setValue(null);
        txtConcepto.setText("");
        txtTipoActual.setText("");
        txtEstadoActual.setText("");
        cmbEstado.setSelectedIndex(0);
        
        // Estructuras base limpias
        DefaultTableModel modelAsientos = new DefaultTableModel(
            new Object[][]{}, 
            new String[]{"N# Asiento", "Fecha", "Concepto", "Tipo", "Estado"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblAsientos.setModel(modelAsientos);

        DefaultTableModel modelCuentas = new DefaultTableModel(
            new Object[][]{}, 
            new String[]{"Código Cuenta", "Nombre Cuenta", "Debe ($)", "Haber ($)"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblCuentas.setModel(modelCuentas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelInputs = new javax.swing.JPanel();
        lblNumeroAsiento = new javax.swing.JLabel();
        txtNumeroAsiento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ftxtFecha = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtConcepto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAsientos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTipoActual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEstadoActual = new javax.swing.JTextField();
        ftxtFechaInicial = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ftxtFechaFinal = new javax.swing.JFormattedTextField();
        btnBuscarAsientos = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1083, 756));
        setMinimumSize(new java.awt.Dimension(1083, 756));

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        panelTitulo.setPreferredSize(new java.awt.Dimension(1270, 80));
        panelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Ver Partidas Diarias");
        panelTitulo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 320, 35));

        jPanel1.setBackground(new java.awt.Color(59, 116, 231));
        jPanel1.setPreferredSize(new java.awt.Dimension(270, 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelTitulo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 270, 5));

        panelInputs.setBackground(new java.awt.Color(255, 255, 255));
        panelInputs.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 50, 10, 40));
        panelInputs.setPreferredSize(new java.awt.Dimension(1200, 150));
        panelInputs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNumeroAsiento.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblNumeroAsiento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumeroAsiento.setText("N# Asiento");
        panelInputs.add(lblNumeroAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtNumeroAsiento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumeroAsiento.setPreferredSize(new java.awt.Dimension(100, 28));
        txtNumeroAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroAsientoActionPerformed(evt);
            }
        });
        panelInputs.add(txtNumeroAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 70, -1));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha");
        panelInputs.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        try {
            ftxtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftxtFecha.setPreferredSize(new java.awt.Dimension(120, 28));
        ftxtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtFechaActionPerformed(evt);
            }
        });
        panelInputs.add(ftxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 110, 30));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Estado Actual");
        panelInputs.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Cambiar Estado");
        panelInputs.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, -1, -1));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Registrado", "Aprobado", "Anulado" }));
        panelInputs.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 80, 150, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Concepto:");
        panelInputs.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        panelInputs.add(txtConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 510, -1));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblCuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCuentas);

        panelInputs.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 950, 120));

        btnActualizar.setBackground(new java.awt.Color(0, 102, 102));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        panelInputs.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 600, 120, 30));

        btnLimpiar.setBackground(new java.awt.Color(0, 0, 153));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        panelInputs.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 600, 80, 30));

        tblAsientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblAsientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAsientosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAsientos);

        panelInputs.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 950, 130));

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Tabla donde se muestran todos los asientos.");
        panelInputs.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, -1, -1));

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Tabla donde se muestran las cuentas que conforman el asiento seleccionado.");
        panelInputs.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, -1, -1));
        panelInputs.add(txtTipoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 120, 30));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Tipo Actual");
        panelInputs.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));
        panelInputs.add(txtEstadoActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 150, 30));

        try {
            ftxtFechaInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftxtFechaInicial.setPreferredSize(new java.awt.Dimension(120, 28));
        ftxtFechaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtFechaInicialActionPerformed(evt);
            }
        });
        panelInputs.add(ftxtFechaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 110, 30));

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Hasta");
        panelInputs.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        jLabel10.setBackground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("Desde:");
        panelInputs.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        try {
            ftxtFechaFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftxtFechaFinal.setPreferredSize(new java.awt.Dimension(120, 28));
        ftxtFechaFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftxtFechaFinalActionPerformed(evt);
            }
        });
        panelInputs.add(ftxtFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 110, 30));

        btnBuscarAsientos.setText("Buscar");
        btnBuscarAsientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAsientosActionPerformed(evt);
            }
        });
        panelInputs.add(btnBuscarAsientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 163, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelInputs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInputs, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
limpiarFormulario();
        ftxtFechaInicial.setValue(null);
        ftxtFechaFinal.setValue(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
String numAsiento = txtNumeroAsiento.getText().trim();
        if (numAsiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione primero un asiento de la lista.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nuevoEstado = cmbEstado.getSelectedItem().toString();
        String estadoActual = txtEstadoActual.getText();
        
        if (nuevoEstado.equals(estadoActual)) {
            JOptionPane.showMessageDialog(this, "El estado seleccionado es igual al estado actual del asiento.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Confirmación formal de control de auditoría
        int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de cambiar el estado del asiento N# " + numAsiento + " de '" + estadoActual + "' a '" + nuevoEstado + "'?\n"
                + "Recuerde que si lo cambia a 'Aprobado' o 'Anulado', el registro se congelará de forma permanente.",
                "Confirmación de Cambio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            boolean exito = asientoDAO.actualizarEstado(numAsiento, nuevoEstado);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Asiento actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // Forzar refrescado del buscador para ver el cambio reflejado inmediatamente
                btnBuscarAsientosActionPerformed(null);
                txtEstadoActual.setText(nuevoEstado);
                cmbEstado.setEnabled(false);
                btnActualizar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Error al intentar actualizar el estado en el servidor de base de datos.", "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void ftxtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFechaActionPerformed

    private void txtNumeroAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroAsientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroAsientoActionPerformed

    private void ftxtFechaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFechaInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFechaInicialActionPerformed

    private void ftxtFechaFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFechaFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFechaFinalActionPerformed

    private void tblAsientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAsientosMouseClicked
        int filaSeleccionada = tblAsientos.getSelectedRow();
        if (filaSeleccionada == -1 || listaAsientosActuales == null) return;
        
        // Obtener el objeto seleccionado de la lista en memoria
        Asiento asientoSel = listaAsientosActuales.get(filaSeleccionada);
        
        // Rellenar las cajas de texto superiores de información
        txtNumeroAsiento.setText(asientoSel.getNumero());
        ftxtFecha.setText(sdf.format(asientoSel.getFecha()));
        txtConcepto.setText(asientoSel.getConcepto());
        txtTipoActual.setText(asientoSel.getTipo());
        txtEstadoActual.setText(asientoSel.getEstado());
        
        // Sincronizar el ComboBox con el estado actual
        cmbEstado.setSelectedItem(asientoSel.getEstado());
        
        // REGLA DE NEGOCIO: Si el asiento ya está aprobado o anulado, prevenimos modificaciones accidentales
        if (asientoSel.getEstado().equalsIgnoreCase("Aprobado") || asientoSel.getEstado().equalsIgnoreCase("Anulado")) {
            cmbEstado.setEnabled(false);
            btnActualizar.setEnabled(false);
        } else {
            cmbEstado.setEnabled(true);
            btnActualizar.setEnabled(true);
        }
        
        // CARGAR EL DETALLE (Tabla 2) - Usamos el campo id que guardamos previamente en periodo_id
        int idAsientoPK = asientoSel.getPeriodo_id();
        List<Partida> partidas = partidaDAO.obtenerPartidasPorAsientoId(idAsientoPK);
        
        DefaultTableModel modeloDetalle = (DefaultTableModel) tblCuentas.getModel();
        modeloDetalle.setRowCount(0);
        
        double totalDebe = 0.0;
        double totalHaber = 0.0;
        
        for (Partida p : partidas) {
            modeloDetalle.addRow(new Object[]{
                p.getCodigoCuentaTemporal(),
                p.getNombreCuentaTemporal(),
                p.getDebe() > 0 ? p.getDebe() : "-",
                p.getHaber() > 0 ? p.getHaber() : "-"
            });
            totalDebe += p.getDebe();
            totalHaber += p.getHaber();
        }
        
        // Fila final explicativa de sumas iguales (Aporte contable)
        modeloDetalle.addRow(new Object[]{
            "---",
            "TOTALES CUADRADOS DE LA PARTIDA",
            totalDebe,
            totalHaber
        });
    }//GEN-LAST:event_tblAsientosMouseClicked

    
    private void btnBuscarAsientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAsientosActionPerformed
        try {
            String fechaIniStr = ftxtFechaInicial.getText().trim();
            String fechaFinStr = ftxtFechaFinal.getText().trim();
            
            if (fechaIniStr.equals("//") || fechaFinStr.equals("//")) {
                JOptionPane.showMessageDialog(this, "Por favor, complete el rango de fechas (Desde / Hasta).", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Date fechaInicio = sdf.parse(fechaIniStr);
            Date fechaFin = sdf.parse(fechaFinStr);
            
            if (fechaInicio.after(fechaFin)) {
                JOptionPane.showMessageDialog(this, "La fecha inicial no puede ser posterior a la fecha final.", "Error de Fechas", java.awt.Color.RED.getRGB());
                return;
            }
            
            // Consultar datos
            listaAsientosActuales = asientoDAO.buscarPorFechas(fechaInicio, fechaFin);
            
            // Llenar Tabla Maestro
            DefaultTableModel modelo = (DefaultTableModel) tblAsientos.getModel();
            modelo.setRowCount(0); // Resetear registros anteriores
            
            for (Asiento a : listaAsientosActuales) {
                modelo.addRow(new Object[]{
                    a.getNumero(),
                    sdf.format(a.getFecha()),
                    a.getConcepto(),
                    a.getTipo(),
                    a.getEstado()
                });
            }
            
            if (listaAsientosActuales.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron asientos contables en el rango estipulado.", "Sin registros", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Utilice el formato: DD/MM/AAAA", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarAsientosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarAsientos;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JFormattedTextField ftxtFecha;
    private javax.swing.JFormattedTextField ftxtFechaFinal;
    private javax.swing.JFormattedTextField ftxtFechaInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNumeroAsiento;
    private javax.swing.JPanel panelInputs;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTable tblAsientos;
    private javax.swing.JTable tblCuentas;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtEstadoActual;
    private javax.swing.JTextField txtNumeroAsiento;
    private javax.swing.JTextField txtTipoActual;
    // End of variables declaration//GEN-END:variables
}
