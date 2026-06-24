/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package forms;

import Clases.Asiento;
import Clases.AsientoDAO;
import Clases.Cuenta;
import Clases.CuentaDAO;
import Clases.Partida;
import Clases.PartidaDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;

/**
 *
 * @author luisc
 */
public class PanelPartidas extends javax.swing.JPanel {

    LocalDate hoy = LocalDate.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String fechaFormateada = hoy.format(formato);
    Date fecha = Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant());
    DefaultTableModel modelo = new DefaultTableModel();
    List<Partida> listaTemporalPartidas = new ArrayList<>();

    public PanelPartidas() {
        initComponents();
        ftxtFecha.setText(fechaFormateada);
        this.cargarCuentas();
        this.llenarTabla();
        estiloTabla();
        this.recalcularTotales();
        txtNumeroAsiento.setEditable(false);
        this.generarNumeroAsientoAutomatico();
    }

    private void generarNumeroAsientoAutomatico() {
// Mostramos "PENDIENTE" porque el número real e idéntico al ID 
        // lo asignará la base de datos de forma segura al presionar Guardar.
        txtNumeroAsiento.setText("AUTOGENERABLE");
    }
    
    private void cargarCuentas() {
        CuentaDAO dao = new CuentaDAO();
        cmb_Cuentas.removeAllItems();
        for (Cuenta c : dao.listarCuentasParaCMB()) {
            ((JComboBox) cmb_Cuentas).addItem(c);
        }
    }

    private void llenarTabla() {
        modelo = new DefaultTableModel() { // Usamos la variable global
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 || column == 3; // solo cantidad
            }
        };

        modelo.addColumn("Código");
        modelo.addColumn("Cuenta");
        modelo.addColumn("Debe");
        modelo.addColumn("Haber");

        tblCuentas.setModel(modelo); // Nombre correcto del componente
        
        modelo.addTableModelListener(new javax.swing.event.TableModelListener() {
        @Override
        public void tableChanged(javax.swing.event.TableModelEvent e) {
            // Solo actuamos si el cambio fue una actualización en las celdas
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int fila = e.getFirstRow();
                int columna = e.getColumn();
                
                // Si la columna editada es el Debe (2) o el Haber (3)
                if (columna == 2 || columna == 3) {
                    try {
                        Object valor = modelo.getValueAt(fila, columna);
                        double nuevoMonto = 0.0;
                        
                        if (valor != null && !valor.toString().trim().isEmpty()) {
                            nuevoMonto = Double.parseDouble(valor.toString().trim());
                        }
                        
                        // 1. Sincronizar con la lista interna en memoria
                        if (fila < listaTemporalPartidas.size()) {
                            Partida p = listaTemporalPartidas.get(fila);
                            if (columna == 2) {
                                p.setDebe(nuevoMonto);
                            } else {
                                p.setHaber(nuevoMonto);
                            }
                        }
                        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, 
                            "Por favor, ingrese un número válido.", 
                            "Error de formato", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    // 2. Recalcular las etiquetas lblDebe y lblHaber de inmediato
                    recalcularTotales();
                }
            }
        }
    });
        
        tblCuentas.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblCuentas.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblCuentas.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblCuentas.getColumnModel().getColumn(3).setPreferredWidth(70);

        tblCuentas.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        
             tblCuentas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    eliminarFilaSeleccionada();
                }
            }
        });
    }

    private void eliminarFilaSeleccionada() {
        int fila = tblCuentas.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila);
            recalcularTotales(); // importante recalcular
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
        }
        listaTemporalPartidas.clear();
    }

    public void actualizarTabla() {
        modelo.setRowCount(0); // Limpia la tabla visual
        for (Partida partida : listaTemporalPartidas) {
            // Obtenemos los valores. Nota: Asegúrate de que en tu clase Partida 
            // tengas acceso temporal a estos campos o pasa el objeto Cuenta.
            modelo.addRow(new Object[]{
                partida.getCodigoCuentaTemporal(), // Un String que setearemos en el botón
                partida.getNombreCuentaTemporal(), // Un String que setearemos en el botón
                partida.getDebe(),
                partida.getHaber()
            });
        }
    }

    private void recalcularTotales() {
        
        DefaultTableModel modeloCuentas = (DefaultTableModel) tblCuentas.getModel();
        
        double totalDebe = 0;
        double totalHaber = 0.0;
        
        for (int i = 0; i < modeloCuentas.getRowCount(); i++) {
            totalDebe += Double.parseDouble(modeloCuentas.getValueAt(i, 2).toString());
        }
        lblDebe.setText(String.format("%.2f", totalDebe));

        for (int i = 0; i < modeloCuentas.getRowCount(); i++) {
            totalHaber += Double.parseDouble(modeloCuentas.getValueAt(i, 3).toString());
        }
        lblHaber.setText(String.format("%.2f", totalHaber));

        // Opcional: Si quieres validar visualmente que el asiento cuadre (Partida Doble)
        if (totalDebe == totalHaber && totalDebe > 0) {
            lblDebe.setForeground(new java.awt.Color(46, 125, 50));  // Verde si cuadra
            lblHaber.setForeground(new java.awt.Color(46, 125, 50));
        } else {
            lblDebe.setForeground(new java.awt.Color(198, 40, 40));   // Rojo si no cuadra
            lblHaber.setForeground(new java.awt.Color(198, 40, 40));
        }

    }

    /*private void recalcularTotales() {
        double totalDebe = 0.0;
        double totalHaber = 0.0;

        // Recorremos la lista temporal sumando los montos
        for (Partida partida : listaTemporalPartidas) {
            totalDebe += partida.getDebe();
            totalHaber += partida.getHaber();
        }

        // Mostramos los totales formateados en las etiquetas correspondientes
        // %.2f sirve para asegurar que siempre muestre 2 decimales (ej: 100.00)
        lblDebe.setText(String.format("$ %.2f", totalDebe));
        lblHaber.setText(String.format("$ %.2f", totalHaber));

        // Opcional: Si quieres validar visualmente que el asiento cuadre (Partida Doble)
        if (totalDebe == totalHaber && totalDebe > 0) {
            lblDebe.setForeground(new java.awt.Color(46, 125, 50));  // Verde si cuadra
            lblHaber.setForeground(new java.awt.Color(46, 125, 50));
        } else {
            lblDebe.setForeground(new java.awt.Color(198, 40, 40));   // Rojo si no cuadra
            lblHaber.setForeground(new java.awt.Color(198, 40, 40));
        }
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panelInputs = new javax.swing.JPanel();
        lblNumeroAsiento = new javax.swing.JLabel();
        txtNumeroAsiento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ftxtFecha = new javax.swing.JFormattedTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        cmb_Cuentas = new javax.swing.JComboBox<>();
        btnDebe = new javax.swing.JButton();
        btnHaber = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtConcepto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCuentas = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        lblHaber = new javax.swing.JLabel();
        lblTotalHaber = new javax.swing.JLabel();
        lblDebe = new javax.swing.JLabel();
        lblTotalDebe = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

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
        jLabel1.setText("Partidas Diarias");
        panelTitulo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 210, 35));

        jPanel1.setBackground(new java.awt.Color(59, 116, 231));
        jPanel1.setPreferredSize(new java.awt.Dimension(270, 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelTitulo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 270, 5));

        add(panelTitulo, java.awt.BorderLayout.NORTH);

        panelInputs.setBackground(new java.awt.Color(255, 255, 255));
        panelInputs.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 50, 10, 40));
        panelInputs.setPreferredSize(new java.awt.Dimension(1200, 150));
        panelInputs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNumeroAsiento.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblNumeroAsiento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumeroAsiento.setText("N# Asiento");
        panelInputs.add(lblNumeroAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        txtNumeroAsiento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumeroAsiento.setPreferredSize(new java.awt.Dimension(100, 28));
        txtNumeroAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroAsientoActionPerformed(evt);
            }
        });
        panelInputs.add(txtNumeroAsiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 160, -1));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha");
        panelInputs.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));

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
        panelInputs.add(ftxtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 90, 30));

        lblCantidad.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCantidad.setText("Cantidad: $");
        panelInputs.add(lblCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, -1, -1));

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad.setPreferredSize(new java.awt.Dimension(100, 28));
        panelInputs.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 70, 30));

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tipo");
        panelInputs.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, -1, -1));

        cmbTipo.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Apertura", "Normal", "Cierre" }));
        cmbTipo.setMinimumSize(new java.awt.Dimension(150, 28));
        panelInputs.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 150, 28));

        cmb_Cuentas.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        cmb_Cuentas.setPreferredSize(new java.awt.Dimension(250, 28));
        cmb_Cuentas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_CuentasItemStateChanged(evt);
            }
        });
        cmb_Cuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_CuentasMouseClicked(evt);
            }
        });
        cmb_Cuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_CuentasActionPerformed(evt);
            }
        });
        panelInputs.add(cmb_Cuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 270, 24));

        btnDebe.setBackground(new java.awt.Color(0, 0, 153));
        btnDebe.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnDebe.setForeground(new java.awt.Color(255, 255, 255));
        btnDebe.setText("Debe");
        btnDebe.setBorderPainted(false);
        btnDebe.setPreferredSize(new java.awt.Dimension(100, 30));
        btnDebe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDebeActionPerformed(evt);
            }
        });
        panelInputs.add(btnDebe, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, 80, 30));

        btnHaber.setBackground(new java.awt.Color(0, 0, 153));
        btnHaber.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        btnHaber.setForeground(new java.awt.Color(255, 255, 255));
        btnHaber.setText("Haber");
        btnHaber.setBorderPainted(false);
        btnHaber.setPreferredSize(new java.awt.Dimension(100, 30));
        btnHaber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHaberActionPerformed(evt);
            }
        });
        panelInputs.add(btnHaber, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 80, 30));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Cuenta");
        panelInputs.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setText("Estado");
        panelInputs.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 80, -1, -1));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Registrado", "Aprobado", "Anulado" }));
        panelInputs.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 150, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setText("Concepto:");
        panelInputs.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));
        panelInputs.add(txtConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 610, -1));

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

        panelInputs.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 850, 390));

        btnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelInputs.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 600, 120, 30));

        lblHaber.setText("HABER");
        panelInputs.add(lblHaber, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 600, -1, -1));

        lblTotalHaber.setText("Total haber:");
        panelInputs.add(lblTotalHaber, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 600, -1, -1));

        lblDebe.setText("DEBE");
        panelInputs.add(lblDebe, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 600, -1, -1));

        lblTotalDebe.setText("Total debe:");
        panelInputs.add(lblTotalDebe, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, -1, 20));

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

        add(panelInputs, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroAsientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroAsientoActionPerformed

    private void ftxtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftxtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftxtFechaActionPerformed

    private void cmb_CuentasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_CuentasItemStateChanged
        // TODO add your handling code here:
        //    this.traersubCuentas();
    }//GEN-LAST:event_cmb_CuentasItemStateChanged

    private void cmb_CuentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_CuentasMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_CuentasMouseClicked

    private void cmb_CuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_CuentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_CuentasActionPerformed

    private void btnDebeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDebeActionPerformed
        try {
            // 1. Validar que el campo cantidad no esté vacío
            if (txtCantidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Recuperar el objeto Cuenta seleccionado directamente del ComboBox
            Object seleccionado = cmb_Cuentas.getSelectedItem();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una cuenta contable.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Cuenta cuentaSeleccionada = (Cuenta) seleccionado;

            // 3. Crear la partida y asignarle los valores correspondientes
            Partida partida = new Partida();
            Double debe = Double.parseDouble(txtCantidad.getText().trim());

            // Asignamos el ID numérico real de la BD que ahora sí viene lleno desde el combo
            partida.setCuenta_id(cuentaSeleccionada.getId());
            partida.setDebe(debe);
            partida.setHaber(0.0);

            // Seteamos las variables de texto temporales (Son las que leerá el Modelo de tu JTable)
            partida.setCodigoCuentaTemporal(cuentaSeleccionada.getCodigo());
            partida.setNombreCuentaTemporal(cuentaSeleccionada.getNombre());

            // 4. Guardar en la lista en memoria y actualizar la interfaz gráfica
            listaTemporalPartidas.add(partida);

            actualizarTabla();        // Refresca las filas del JTable (utilizando los campos temporales)
            recalcularTotales();      // Ejecuta la lógica de sumas al Debe y Haber en la parte inferior
            txtCantidad.setText("");  // Limpia el campo para una nueva entrada

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido en la cantidad.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar partida al Debe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDebeActionPerformed

    private void btnHaberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHaberActionPerformed
        try {
            if (txtCantidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad.");
                return;
            }

            Partida partida = new Partida();
            Double haber = Double.parseDouble(txtCantidad.getText());
            AsientoDAO idAsiento = new AsientoDAO();

            Cuenta cuentaSeleccionada = (Cuenta) cmb_Cuentas.getSelectedItem();

            if (cuentaSeleccionada != null) {
                partida.setAsiento_id(idAsiento.obtenerIdAsiento());
                partida.setCuenta_id(cuentaSeleccionada.getId());
                partida.setDebe(0.0);
                partida.setHaber(haber);

                partida.setCodigoCuentaTemporal(cuentaSeleccionada.getCodigo());
                partida.setNombreCuentaTemporal(cuentaSeleccionada.getNombre());

                listaTemporalPartidas.add(partida);

                //     this.recalcularTotales();
                actualizarTabla();
                txtCantidad.setText("");
                recalcularTotales();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar partida: " + e.getMessage());
        }
    }//GEN-LAST:event_btnHaberActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
// 1. Validaciones previas de negocio
        if (listaTemporalPartidas.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "La lista de partidas está vacía.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtConcepto.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor complete el Concepto del asiento.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- VALIDACIÓN DE PERIODO CONTABLE ---
        Clases.PeriodoDAO periodoDAO = new Clases.PeriodoDAO();
        Clases.Periodo periodoActivo = periodoDAO.obtenerPeriodoActivo();

        if (periodoActivo == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No se puede guardar el asiento porque no existe ningún Periodo Contable 'Abierto' en el sistema.\nPor favor, configure o abra un periodo primero.",
                    "Error de Configuración Contable",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return; // Detiene la ejecución para no insertar datos huérfanos sin periodo_id
        }
        // --------------------------------------

        // 2. Validar principio de partida doble (cuadrante)
        double totalDebe = 0.0;
        double totalHaber = 0.0;
        for (Partida p : listaTemporalPartidas) {
            totalDebe += p.getDebe();
            totalHaber += p.getHaber();
        }

        // Usamos una pequeña tolerancia por cuestiones de precisión decimal de punto flotante
        if (Math.abs(totalDebe - totalHaber) > 0.001) {
            javax.swing.JOptionPane.showMessageDialog(this, "El asiento no está cuadrado. El total del DEBE debe ser igual al HABER.", "Error Contable", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Proceso de Guardado Transaccional
        java.sql.Connection con = null;
        try {
            con = clases.Conexion.conectar();
            con.setAutoCommit(false); // DETENEMOS EL AUTO-COMMIT PARA CONTROLAR LA TRANSACCIÓN

            // Instanciamos los DAO pasando la conexión compartida
            AsientoDAO asientoDAO = new AsientoDAO();
            PartidaDAO partidaDAO = new PartidaDAO();

            // Creamos el objeto Asiento con los datos de los inputs
            Asiento asiento = new Asiento();
            
            // Colocamos un valor transitorio, ya que abajo se sobreescribirá con el ID real de la BD
            asiento.setNumero("PENDIENTE"); 
            asiento.setFecha(fecha); // Variable 'fecha' de tipo Date definida arriba de tu panel
            asiento.setConcepto(txtConcepto.getText().trim());
            asiento.setTipo(cmbTipo.getSelectedItem().toString());
            asiento.setEstado(cmbEstado.getSelectedItem().toString());

            // ASIGNACIÓN DINÁMICA: Seteamos el ID del periodo que se encuentra activo en la base de datos
            asiento.setPeriodo_id(periodoActivo.getId());

            // Verificamos si hay una sesión activa para evitar errores de puntero nulo (NullPointerException)
            if (Clases.Sesion.getUsuarioActual() != null) {
                asiento.setCreado_por(Clases.Sesion.getUsuarioActual().getId());
            } else {
                // Si por alguna razón pruebas el panel sin loguearte antes, asignará el ID 2 por defecto (Contador)
                asiento.setCreado_por(2);
            }

            // Guardamos el asiento y obtenemos su ID generado por la BD
            int idAsientoGenerado = asientoDAO.insertarAsiento(asiento, con);

            // ====================================================================
            // SINCRONIZACIÓN: IGUALAMOS EL CAMPO 'NUMERO' AL 'ID' AUTOGENERADO
            // ====================================================================
            String sqlUpdateNumero = "UPDATE asientos SET numero = ? WHERE id = ?";
            try (java.sql.PreparedStatement psUpdate = con.prepareStatement(sqlUpdateNumero)) {
                psUpdate.setString(1, String.valueOf(idAsientoGenerado));
                psUpdate.setInt(2, idAsientoGenerado);
                psUpdate.executeUpdate();
            }
            // ====================================================================

            // Recorremos la lista temporal e insertamos las partidas vinculadas al ID del asiento
            for (Partida partida : listaTemporalPartidas) {
                partida.setAsiento_id(idAsientoGenerado); // Seteamos el ID real foráneo
                partida.setHaber(partida.getHaber());
                partida.setDebe(partida.getDebe());

                partidaDAO.insertarPartida(partida, con);
            }

            // Si todo salió bien hasta aquí sin excepciones, confirmamos los datos en la BD
            con.commit();
            javax.swing.JOptionPane.showMessageDialog(this, "Asiento Diario y Partidas guardados exitosamente con el N# Asiento: " + idAsientoGenerado, "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            // 4. Limpieza de la interfaz gráfica y REFRESCAMIENTO DEL CORRELATIVO
            listaTemporalPartidas.clear();
            actualizarTabla();
            recalcularTotales();
            
            txtConcepto.setText("");
            txtCantidad.setText("");
            
            // Re-calculamos el número de asiento automático para la siguiente entrada
            generarNumeroAsientoAutomatico();

        } catch (Exception e) {
            // Si algo falla, revertimos absolutamente todo para no dejar datos corruptos
            if (con != null) {
                try {
                    con.rollback();
                } catch (java.sql.SQLException ex) {
                    ex.printStackTrace();
                }
            }
            javax.swing.JOptionPane.showMessageDialog(this, "Error crítico al guardar en la base de datos: " + e.getMessage(), "Error de Transacción", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Aseguramos el cierre de la conexión en el bloque finally
            if (con != null) {
                try {
                    con.close();
                } catch (java.sql.SQLException ex) {
                    ex.printStackTrace();
                }
            }
        
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

        this.recalcularTotales();
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    // mejora de interfaz
    private void estiloTabla() {

        tblCuentas.setDefaultRenderer(Object.class,
                new javax.swing.table.DefaultTableCellRenderer() {

            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                java.awt.Component c
                        = super.getTableCellRendererComponent(
                                table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new java.awt.Color(232, 242, 252));
                    } else {
                        c.setBackground(java.awt.Color.WHITE);
                    }
                }

                return c;
            }
        });
    }

    private void limpiarFormulario() {

        // Limpiar campos
        txtNumeroAsiento.setText("");
        txtCantidad.setText("");
        txtConcepto.setText("");

        // Fecha actual
        ftxtFecha.setText(fechaFormateada);

        // Reiniciar combos
        cmbTipo.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);

        if (cmb_Cuentas.getItemCount() > 0) {
            cmb_Cuentas.setSelectedIndex(0);
        }

        // Limpiar lista temporal
        listaTemporalPartidas.clear();

        // Limpiar tabla
        actualizarTabla();

        // Reiniciar totales
        recalcularTotales();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDebe;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHaber;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JComboBox<String> cmb_Cuentas;
    private javax.swing.JFormattedTextField ftxtFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDebe;
    private javax.swing.JLabel lblHaber;
    private javax.swing.JLabel lblNumeroAsiento;
    private javax.swing.JLabel lblTotalDebe;
    private javax.swing.JLabel lblTotalHaber;
    private javax.swing.JPanel panelInputs;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTable tblCuentas;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtNumeroAsiento;
    // End of variables declaration//GEN-END:variables
}
