/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ControladorPedidos;
import Modelo.Pedido;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emito
 */
public class VListarPedidos extends javax.swing.JFrame {

    ControladorPedidos ctrl = null;

    public VListarPedidos() {
        ctrl = new ControladorPedidos();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        cmbEst = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePed = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 235, 83));

        btnEditar.setBackground(new java.awt.Color(255, 51, 0));
        btnEditar.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(255, 51, 0));
        btnAgregar.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 51, 0));
        btnEliminar.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtTotal.setBackground(new java.awt.Color(51, 51, 51));
        txtTotal.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        cmbEst.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        cmbEst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendiente", "Enviado", "Entregado" }));
        cmbEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel1.setText("Estado");

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel2.setText("Total de Pedido");

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel3.setText("Fecha de Pedido");

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel5.setText("Administrar Pedidos");

        txtDia.setBackground(new java.awt.Color(51, 51, 51));
        txtDia.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtDia.setForeground(new java.awt.Color(255, 255, 255));
        txtDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaActionPerformed(evt);
            }
        });

        txtMes.setBackground(new java.awt.Color(51, 51, 51));
        txtMes.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtMes.setForeground(new java.awt.Color(255, 255, 255));
        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });

        txtAnio.setBackground(new java.awt.Color(51, 51, 51));
        txtAnio.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtAnio.setForeground(new java.awt.Color(255, 255, 255));
        txtAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel7.setText("/");

        jLabel8.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel8.setText("/");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(66, 66, 66))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(cmbEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18))
        );

        TablePed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TablePed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablePed.setSelectionBackground(new java.awt.Color(0, 153, 255));
        TablePed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePedMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {

            float Total = Float.parseFloat(txtTotal.getText());
            String Estado = (String) cmbEst.getSelectedItem();

            //tomo los datos de la fecha
            String Dia = txtDia.getText();
            String Mes = txtMes.getText();
            String Anio = txtAnio.getText();

            String Fecha = Dia + "-" + Mes + "-" + Anio;

            ctrl.EscribirPedido(Total, Estado, Fecha);
            MostrarMensaje("Pedido Agregado", "El Pedido se agrego correctamente", "Info");
        } catch (Exception e) {
            MostrarMensaje("Error al Agregar Pedido", "Intente nuevamente y asegurese de"
                    + "\nrellenar los campos correctamente", "Error");
        }

        CargarTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (TablePed.getRowCount() != 0) {
            if (TablePed.getSelectedRow() != -1) {
                try {

                    //toma el codigo de producto de la fila seleccionada
                    int id = Integer.parseInt(String.valueOf(TablePed.getValueAt(TablePed.getSelectedRow(), 0)));
                    ctrl.EliminarPedido(id);

                    //actualiza la tabla
                    CargarTabla();

                    MostrarMensaje("Pedido Eliminado", "El Pedido se eliminó correctamente", "Info");
                } catch (Exception e) {
                    MostrarMensaje("Error al Eliminar Pedido", "Intente nuevamente", "Error");
                }
            } else {
                MostrarMensaje("Error al Eliminar Pedido", "No seleccionó ninguna fila", "Error");
            }
        } else {
            MostrarMensaje("Error al Eliminar Pedido", "La tabla esta vacia", "Error");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void MostrarMensaje(String Titulo, String Cuerpo, String Tipo) {

        if (Tipo.equals("Error")) {
            JOptionPane.showMessageDialog(null, Cuerpo, Titulo, 0);
        } else if (Tipo.equals("Info")) {
            JOptionPane.showMessageDialog(null, Cuerpo, Titulo, 1);
        }
    }
    private void cmbEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaActionPerformed

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

    private void txtAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioActionPerformed

    private void TablePedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePedMouseClicked
        String Estado = String.valueOf(TablePed.getValueAt(TablePed.getSelectedRow(), 1));
        String Fecha = String.valueOf(TablePed.getValueAt(TablePed.getSelectedRow(), 2));
        String Total = String.valueOf(TablePed.getValueAt(TablePed.getSelectedRow(), 3));

        //dividir fecha en dia mes y año
        String[] partes = Fecha.split("-");

        // Convertir las partes en números
        String Dia = partes[0];
        String Mes = partes[1];
        String Anio = partes[2];

        //escribe los valores de la tabla en los campos que el usuario puede editar
        cmbEst.setSelectedItem(Estado);
        txtDia.setText(Dia);
        txtMes.setText(Mes);
        txtAnio.setText(Anio);
        txtTotal.setText(Total);
    }//GEN-LAST:event_TablePedMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (TablePed.getRowCount() != 0) {
            if (TablePed.getSelectedRow() != -1) {
                try {
                    //tomo el identificador de la fila seleccionada
                    int id = Integer.parseInt(String.valueOf(TablePed.getValueAt(TablePed.getSelectedRow(), 0)));
                    //busco el producto en la base de datos
                    Pedido ped = ctrl.BuscarPedido(id);

                    //tomo los valores modificados desde los campos de texto de la vista
                    float Total = Float.parseFloat(txtTotal.getText());
                    String Estado = (String) cmbEst.getSelectedItem();

                    //tomo los datos de la fecha
                    String Dia = txtDia.getText();
                    String Mes = txtMes.getText();
                    String Anio = txtAnio.getText();

                    String Fecha = Dia + "-" + Mes + "-" + Anio;

                    ctrl.EditarPedido(ped, Estado, Fecha, Total);
                    CargarTabla();

                    MostrarMensaje("Pedido Editado", "El Pedido se editó correctamente", "Info");
                } catch (NumberFormatException e) {
                    MostrarMensaje("Error al Editar Pedido", "Intente nuevamente", "Error");
                }
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        CargarTabla();
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePed;
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JComboBox<String> cmbEst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    public void CargarTabla() {
        DefaultTableModel ModeloTabla = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //se asigan los nombres de los titulos de las columnas
        String titulos[] = {"Identificador", "Estado", "Fecha", "Total"};
        ModeloTabla.setColumnIdentifiers(titulos);

        //leer los productos de la base de datos  
        List<Pedido> LPeds = ctrl.LeerPedidos();

        //asigna a la tabla todos los productos de la lista
        if (LPeds != null) {
            for (Pedido ped : LPeds) {
                Object[] objetos = {ped.getId(), ped.getEstado(), ped.getFecha(), ped.getTotal()};
                ModeloTabla.addRow(objetos);
            }
        }
        //Asigno el modelo tabla al JTable
        TablePed.setModel(ModeloTabla);
    }
}
