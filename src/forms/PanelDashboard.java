
package forms;
public class PanelDashboard extends javax.swing.JPanel {

    public PanelDashboard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCuerpo = new javax.swing.JPanel();
        otro = new javax.swing.JPanel();
        panelTarjetas = new javax.swing.JPanel();
        tarjeta2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tarjeta1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tarjeta3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tarjeta4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tarjeta5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(0, 0));
        setLayout(new java.awt.BorderLayout());

        panelCuerpo.setBackground(new java.awt.Color(255, 255, 255));
        panelCuerpo.setLayout(new java.awt.BorderLayout());

        otro.setBackground(new java.awt.Color(255, 255, 255));
        otro.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 50, 20, 80));
        otro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        otro.setPreferredSize(new java.awt.Dimension(6340, 169));
        otro.setLayout(new java.awt.BorderLayout());

        panelTarjetas.setBackground(new java.awt.Color(255, 255, 255));
        panelTarjetas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelTarjetas.setLayout(new java.awt.GridLayout(1, 5, 10, 0));

        tarjeta2.setBackground(new java.awt.Color(134, 100, 250));

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Pasivos");

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("$0.0");

        javax.swing.GroupLayout tarjeta2Layout = new javax.swing.GroupLayout(tarjeta2);
        tarjeta2.setLayout(tarjeta2Layout);
        tarjeta2Layout.setHorizontalGroup(
            tarjeta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(tarjeta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tarjeta2Layout.setVerticalGroup(
            tarjeta2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        panelTarjetas.add(tarjeta2);

        tarjeta1.setBackground(new java.awt.Color(25, 205, 197));
        tarjeta1.setForeground(new java.awt.Color(25, 205, 197));

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Activos");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("$0.0");

        javax.swing.GroupLayout tarjeta1Layout = new javax.swing.GroupLayout(tarjeta1);
        tarjeta1.setLayout(tarjeta1Layout);
        tarjeta1Layout.setHorizontalGroup(
            tarjeta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(tarjeta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tarjeta1Layout.setVerticalGroup(
            tarjeta1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        panelTarjetas.add(tarjeta1);

        tarjeta3.setBackground(new java.awt.Color(59, 116, 231));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Capital");

        jLabel8.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("$0.0");

        javax.swing.GroupLayout tarjeta3Layout = new javax.swing.GroupLayout(tarjeta3);
        tarjeta3.setLayout(tarjeta3Layout);
        tarjeta3Layout.setHorizontalGroup(
            tarjeta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta3Layout.createSequentialGroup()
                .addGroup(tarjeta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tarjeta3Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel4))
                    .addGroup(tarjeta3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tarjeta3Layout.setVerticalGroup(
            tarjeta3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        panelTarjetas.add(tarjeta3);

        tarjeta4.setBackground(new java.awt.Color(46, 204, 113));
        tarjeta4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0)));
        tarjeta4.setForeground(new java.awt.Color(46, 204, 113));

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Excedende del Periodo");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("$0.0");

        javax.swing.GroupLayout tarjeta4Layout = new javax.swing.GroupLayout(tarjeta4);
        tarjeta4.setLayout(tarjeta4Layout);
        tarjeta4Layout.setHorizontalGroup(
            tarjeta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(25, 25, 25))
            .addGroup(tarjeta4Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tarjeta4Layout.setVerticalGroup(
            tarjeta4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        panelTarjetas.add(tarjeta4);

        tarjeta5.setBackground(new java.awt.Color(52, 73, 94));
        tarjeta5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Poppins", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Asientos Registrados");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("0");

        javax.swing.GroupLayout tarjeta5Layout = new javax.swing.GroupLayout(tarjeta5);
        tarjeta5.setLayout(tarjeta5Layout);
        tarjeta5Layout.setHorizontalGroup(
            tarjeta5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tarjeta5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tarjeta5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tarjeta5Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        tarjeta5Layout.setVerticalGroup(
            tarjeta5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tarjeta5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        panelTarjetas.add(tarjeta5);

        otro.add(panelTarjetas, java.awt.BorderLayout.CENTER);

        panelCuerpo.add(otro, java.awt.BorderLayout.NORTH);

        add(panelCuerpo, java.awt.BorderLayout.CENTER);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 30, 30));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1270, 430));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Numero", "Fecha", "Concepto", "Debe", "Haber", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFillsViewportHeight(true);
        jTable1.setPreferredSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.SOUTH);

        panelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        panelTitulo.setPreferredSize(new java.awt.Dimension(1270, 80));
        panelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText(" Resumen del Período");
        panelTitulo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 35));

        jPanel1.setBackground(new java.awt.Color(59, 116, 231));
        jPanel1.setPreferredSize(new java.awt.Dimension(270, 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelTitulo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 270, 5));

        add(panelTitulo, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel otro;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelTarjetas;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JPanel tarjeta1;
    private javax.swing.JPanel tarjeta2;
    private javax.swing.JPanel tarjeta3;
    private javax.swing.JPanel tarjeta4;
    private javax.swing.JPanel tarjeta5;
    // End of variables declaration//GEN-END:variables
}
