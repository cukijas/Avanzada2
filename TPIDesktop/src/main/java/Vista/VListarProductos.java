/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ControladorProductos;
import Modelo.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VListarProductos extends javax.swing.JFrame {
    
    ControladorProductos ctrl = null;

    public VListarProductos() {
        ctrl = new ControladorProductos();
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        cmbCat = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProd = new javax.swing.JTable();

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

        txtNombre.setBackground(new java.awt.Color(51, 51, 51));
        txtNombre.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtDescripcion.setBackground(new java.awt.Color(51, 51, 51));
        txtDescripcion.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        cmbCat.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        cmbCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Libros", "Electronicos", "Ropa", "Alimentos" }));
        cmbCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCatActionPerformed(evt);
            }
        });

        txtPrecio.setBackground(new java.awt.Color(51, 51, 51));
        txtPrecio.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(255, 255, 255));

        txtStock.setBackground(new java.awt.Color(51, 51, 51));
        txtStock.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        txtStock.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel1.setText("Descripcion");

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel3.setText("Precio");

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel4.setText("Stock");

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabel5.setText("Administrar Productos");

        jLabel6.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        jLabel6.setText("Categoria");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(txtNombre)
                            .addComponent(txtDescripcion)
                            .addComponent(txtPrecio)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(29, 29, 29)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addGap(14, 14, 14))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnEditar)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18))
        );

        TableProd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TableProd.setSelectionBackground(new java.awt.Color(0, 153, 255));
        TableProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableProd);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try { //toma los datos de los campos de texto
            String Nombre = txtNombre.getText();
            String Descripcion = txtDescripcion.getText();
            float Precio = Float.parseFloat(txtPrecio.getText());
            int Stock = Integer.parseInt(txtStock.getText());
            String Categoria = (String) cmbCat.getSelectedItem();

            //envia los datos a la controladora logica
            ctrl.EscribirProducto(Nombre, Descripcion, Precio, Stock, Categoria);
            MostrarMensaje("Producto Agregado", "El Producto se agrego correctamente", "Info");
        } catch (Exception e) {
            MostrarMensaje("Error al Agregar Producto", "Intente nuevamente y asegurese de"
                    + "\nrellenar los campos correctamente", "Error");
        }
        //actualiza la tabla
        CargarTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if (TableProd.getRowCount() != 0) {
            if (TableProd.getSelectedRow() != -1) {
                try {

                    //toma el codigo de producto de la fila seleccionada
                    int CodigoProducto = Integer.parseInt(String.valueOf(TableProd.getValueAt(TableProd.getSelectedRow(), 0)));
                    ctrl.EliminarProducto(CodigoProducto);
                    //actualiza la tabla
                    CargarTabla();

                    MostrarMensaje("Producto Eliminado", "El Producto se eliminó correctamente", "Info");
                } catch (Exception e) {
                    MostrarMensaje("Error al Eliminar Producto", "Intente nuevamente", "Error");
                }
            } else {
                MostrarMensaje("Error al Eliminar Producto", "No seleccionó ninguna fila", "Error");
            }
        } else {
            MostrarMensaje("Error al Eliminar Producto", "La tabla esta vacia", "Error");
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void MostrarMensaje(String Titulo, String Cuerpo, String Tipo) {

        if (Tipo.equals("Error")) {
            JOptionPane.showMessageDialog(null, Cuerpo, Titulo, 0);
        } else if (Tipo.equals("Info")) {
            JOptionPane.showMessageDialog(null, Cuerpo, Titulo, 1);
        }
    }

    private void cmbCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCatActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //carga la tabla al iniciar la ventana
        CargarTabla();
    }//GEN-LAST:event_formWindowOpened

    private void TableProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProdMouseClicked

        //toma los valores de la fila seleccionada
        String nombre = String.valueOf(TableProd.getValueAt(TableProd.getSelectedRow(), 1));
        String descripcion = String.valueOf(TableProd.getValueAt(TableProd.getSelectedRow(), 2));
        String Precio = String.valueOf(TableProd.getValueAt(TableProd.getSelectedRow(), 3));
        String Stock = String.valueOf(TableProd.getValueAt(TableProd.getSelectedRow(), 4));
        String Categoria = String.valueOf(TableProd.getValueAt(TableProd.getSelectedRow(), 5));

        //escribe los valores de la tabla en los campos de texto
        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(Precio);
        txtStock.setText(Stock);
        cmbCat.setSelectedItem(Categoria);
    }//GEN-LAST:event_TableProdMouseClicked

    //boton para editar valores
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (TableProd.getRowCount() != 0) {
            if (TableProd.getSelectedRow() != -1) {
                try {
                    //tomo el identificador de la fila seleccionada
                    int CodigoProducto = Integer.parseInt(String.valueOf(TableProd.getValueAt(TableProd.getSelectedRow(), 0)));
                    //busco el producto en la base de datos
                    Producto prod = ctrl.BuscarProducto(CodigoProducto);

                    //tomo los valores modificados desde los campos de texto de la vista
                    String Nombre = txtNombre.getText();
                    String Descripcion = txtDescripcion.getText();
                    float Precio = Float.parseFloat(txtPrecio.getText());
                    int Stock = Integer.parseInt(txtStock.getText());
                    String Categoria = (String) cmbCat.getSelectedItem();

                    ctrl.EditarProducto(prod, Nombre, Descripcion, Precio, Stock, Categoria);
                    CargarTabla();

                    MostrarMensaje("Producto Editado", "El Producto se editó correctamente", "Info");
                } catch (NumberFormatException e) {
                    MostrarMensaje("Error al Editar Producto", "Intente nuevamente", "Error");
                }
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableProd;
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEditar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JComboBox<String> cmbCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    public javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    //metodo que carga los datos y el modelo del JTable
    public void CargarTabla() {
        DefaultTableModel ModeloTabla = new DefaultTableModel() {
            //no permite editar los datos directamente en la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //asigna los nombres de los titulos de las columnas
        String titulos[] = {"Codigo", "Nombre", "Descripcion", "Precio", "Stock", "Categoria"};
        ModeloTabla.setColumnIdentifiers(titulos);

        //leer los productos de la base de datos  
        List<Producto> LProds = ctrl.LeerProductos();

        //asigna a la tabla todos los productos de la lista
        if (LProds != null) {
            for (Producto prod : LProds) {
                Object[] objetos = {prod.getId(), prod.getNombre_producto(), prod.getDescripcion(), prod.getPrecio(),
                    prod.getStock(), prod.getCategoria()};
                ModeloTabla.addRow(objetos);
            }
        }
        //Asigna el modelo tabla al JTable
        TableProd.setModel(ModeloTabla);
    }

}
