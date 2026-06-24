package forms;

import Clases.CierresDAO;
import Clases.CierreTemporalDTO;
import Clases.Sesion;
import Clases.Usuario; // Entidad de usuario de tu proyecto
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PanelCierre extends javax.swing.JPanel {

// Instancia del DAO de Cierres y DTO temporal
    private final CierresDAO cierresDAO = new CierresDAO();
    private CierreTemporalDTO cierreActual = null;

    public PanelCierre() {
        initComponents();

        // 1. CONSULTA CORRECTA DEL USUARIO DESDE TU CLASE SESION
        Usuario usuarioLogueado = Sesion.getUsuarioActual();

        if (usuarioLogueado == null) {
            JOptionPane.showMessageDialog(this, "Error: No se ha detectado ninguna sesión activa.", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            deshabilitarTodoElPanel();
            return;
        }

        // 2. VALIDACIÓN DE ROLES (Solo Admin y Contador)
        String rol = usuarioLogueado.getRol();
        if (rol == null || (!rol.equalsIgnoreCase("Admin") && !rol.equalsIgnoreCase("Contador"))) {
            JOptionPane.showMessageDialog(this,
                    "Acceso Denegado: Tu rol (" + rol + ") no tiene permisos para efectuar cierres contables.",
                    "Permisos Insuficientes", JOptionPane.WARNING_MESSAGE);
            deshabilitarTodoElPanel();
            return;
        }

        // 3. Inicialización de la interfaz si el usuario es válido
        txtFechaCierre.setEditable(false);
        txtUsuario.setEditable(false);
        btnGuardarCierre.setEnabled(false);

        // Mostrar fecha actual y nombre real del usuario logueado
        txtFechaCierre.setText(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        txtUsuario.setText(usuarioLogueado.getNombre());

        // Cargar periodos en el combobox
        cargarPeriodosAbiertos();

        // 4. Asignar los métodos a los botones correspondientes
        // btnCrearCierre.addActionListener(e -> accionCrearCierre());
        //btnGuardarCierre.addActionListener(e -> accionGuardarCierre());
    }

// Método auxiliar en caso de que entren sin rol permitido
    private void deshabilitarTodoElPanel() {
        btnCrearCierre.setEnabled(false);
        btnGuardarCierre.setEnabled(false);
        txtPeriodoNuevo.setEditable(false);
        txtAObservaciones.setEditable(false);
        cmbPeriodos.setEnabled(false);
    }

    // Llena el JComboBox consultando el DAO
    private void cargarPeriodosAbiertos() {
        cmbPeriodos.removeAllItems();
        try {
            List<String> periodos = cierresDAO.obtenerPeriodosAbiertos();
            for (String p : periodos) {
                cmbPeriodos.addItem(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los periodos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaCierre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAObservaciones = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCierre = new javax.swing.JTable();
        btnCrearCierre = new javax.swing.JButton();
        btnGuardarCierre = new javax.swing.JButton();
        cmbPeriodos = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtPeriodoNuevo = new javax.swing.JTextField();

        jLabel1.setText("Periodo Actual");

        jLabel2.setText("Fecha de cierre");

        jLabel3.setText("Generado por");

        jLabel4.setText("Observaciones");

        txtAObservaciones.setColumns(20);
        txtAObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtAObservaciones);

        tblCierre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblCierre);

        btnCrearCierre.setText("Crear Cierre");
        btnCrearCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCierreActionPerformed(evt);
            }
        });

        btnGuardarCierre.setText("Guardar Cierre");
        btnGuardarCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCierreActionPerformed(evt);
            }
        });

        jLabel6.setText("Nombre del nuevo periodo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuario))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPeriodoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(495, 495, 495)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCrearCierre))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGuardarCierre)
                                .addGap(35, 35, 35)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtFechaCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPeriodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPeriodoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnCrearCierre)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardarCierre)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCierreActionPerformed
        if (cmbPeriodos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "No hay ningún periodo activo seleccionado.");
            return;
        }

        try {
            // CORRECCIÓN: Recuperar el nombre del periodo del JComboBox y pasarlo al DAO
            String periodoSeleccionado = cmbPeriodos.getSelectedItem().toString();
            cierreActual = cierresDAO.calcularTotalesCierre(periodoSeleccionado);

            // Pintamos la tabla de forma limpia con los montos calculados
            DefaultTableModel model = new DefaultTableModel(
                    new Object[][]{
                        {"Total Activos", cierreActual.totalActivos},
                        {"Total Pasivos", cierreActual.totalPasivos},
                        {"Total Patrimonio", cierreActual.totalPatrimonio},
                        {"Total Ingresos", cierreActual.totalIngresos},
                        {"Total Gastos", cierreActual.totalGastos},
                        {"EXCEDENTE / DÉFICIT DEL EJERCICIO", cierreActual.excedente}
                    },
                    new String[]{"Concepto Contable", "Monto ($)"}
            ) {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }
            };

            tblCierre.setModel(model);
            btnGuardarCierre.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Totales calculados con éxito. Revise la tabla antes de guardar.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al calcular totales: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCrearCierreActionPerformed

    private void btnGuardarCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCierreActionPerformed
        if (cierreActual == null) {
            return;
        }

        String nombreNuevoPeriodo = txtPeriodoNuevo.getText().trim();
        if (nombreNuevoPeriodo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite el nombre que tendrá el NUEVO periodo.");
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de proceder con el cierre? Esta acción bloqueará los registros del periodo actual.",
                "Confirmación de Cierre", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }

        String periodoActual = cmbPeriodos.getSelectedItem().toString();

        // Obtención del ID del usuario desde la sesión real de tu app
        int idUsuarioActual = Sesion.getUsuarioActual().getId();

        // Ejecución de la transacción maestra en el DAO de Cierres
        boolean exito = cierresDAO.ejecutarTransaccionCierre(
                periodoActual,
                nombreNuevoPeriodo,
                cierreActual,
                txtAObservaciones.getText(),
                idUsuarioActual
        );

        if (exito) {
            JOptionPane.showMessageDialog(this, "¡Cierre contable aplicado exitosamente!\nSe bloqueó el periodo anterior y se abrió el nuevo periodo: " + nombreNuevoPeriodo);

            // Limpiar los componentes de la vista
            cierreActual = null;
            txtAObservaciones.setText("");
            txtPeriodoNuevo.setText("");
            tblCierre.setModel(new DefaultTableModel());
            btnGuardarCierre.setEnabled(false);

            // Recargar el combobox para reflejar los cambios
            cargarPeriodosAbiertos();
        } else {
            JOptionPane.showMessageDialog(this, "Error Crítico: No se pudo registrar el cierre. La base de datos revirtió los cambios.");
        }
    }//GEN-LAST:event_btnGuardarCierreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearCierre;
    private javax.swing.JButton btnGuardarCierre;
    private javax.swing.JComboBox<String> cmbPeriodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCierre;
    private javax.swing.JTextArea txtAObservaciones;
    private javax.swing.JTextField txtFechaCierre;
    private javax.swing.JTextField txtPeriodoNuevo;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
