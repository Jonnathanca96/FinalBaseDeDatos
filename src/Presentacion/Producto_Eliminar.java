/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;


import Datos.Base_Datos;
import Logica.Objeto_Instanciar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Producto_Eliminar extends javax.swing.JFrame {
    static String nombre_campo_global;
    //Negocios.Objeto_Eliminar_Registro datos_eliminar_registro;
    Objeto_Instanciar objeto_eliminar_registro;
    static String global_nombre_tabla;
    /**
     * Creates new form Eliminar_Registro
     */
    public Producto_Eliminar() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
         actualizarJTabale();
    }   

    public void actualizarJTabale(){
         //String consulta = "select table_name from user_tables"; 
         String consulta = "SELECT table_name FROM all_tables WHERE OWNER='BAR_RESTAURANTE'";
        try{  
            //para establecer la conexxioon 
            Base_Datos conexion = new Base_Datos();
            Statement statement = conexion.conexion();
            
            //para realizar la consulta
            ResultSet resultSet = statement.executeQuery(consulta);
            //vector para los nombres de las columnas 
            Vector miListaDeCampos = new Vector();
            int numeroDeCampos;
            ResultSetMetaData miMetaData;
            miMetaData = resultSet.getMetaData();
            
            numeroDeCampos=miMetaData.getColumnCount();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                //jComboBox1.addItem(miMetaData.getColumnName(i+1));
            }
            //DefaultTableModel en base a nuestro vector
            DefaultTableModel miDefaultTableModel = new DefaultTableModel(miListaDeCampos,0);
            //reorrido con el ResultSet
            int count=0;
            while (resultSet.next()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                //System.out.println("Registros: "+count);
                //System.out.println("datos de las filas "+resultSet.getString(count));
                for (int i=0;i<numeroDeCampos;i++){//sirve para el objeto mirReglo carge con cada columna del obejto de resultSet
                    miReglon[i]=resultSet.getObject(i+1);
                    jComboBox1.addItem(miReglon[i].toString());
                }
                 
            }
            
        }catch(Exception e){
            //System.out.println("The exception raised is:" + e);
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     
    public void mostrar_IDS(String campo,String tabla){

        String consulta = "select "+campo+" from BAR_RESTAURANTE."+tabla; //enviamos nuestras consultas
        try{  
            //para establecer la conexxioon 
            Base_Datos conexion = new Base_Datos();
            Statement statement = conexion.conexion();
            
            //para realizar la consulta
            //String consulta = "select table_name from user_tables"; 
            ResultSet resultSet = statement.executeQuery(consulta);
            //vector para los nombres de las columnas 
            Vector miListaDeCampos = new Vector();
            int numeroDeCampos;
            ResultSetMetaData miMetaData;
            miMetaData = resultSet.getMetaData();
            
            numeroDeCampos=miMetaData.getColumnCount();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                //jComboBox1.addItem(miMetaData.getColumnName(i+1));
            }
            //DefaultTableModel en base a nuestro vector
            DefaultTableModel miDefaultTableModel = new DefaultTableModel(miListaDeCampos,0);
            //reorrido con el ResultSet
            int count=0;
            jComboBox3.removeAllItems();
            while (resultSet.next()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                for (int i=0;i<numeroDeCampos;i++){//sirve para el objeto mirReglo carge con cada columna del obejto de resultSet
                    miReglon[i]=resultSet.getObject(i+1);
                    //System.out.println("datos  "+miReglon[i]);
                    jComboBox3.addItem(miReglon[i].toString());
                }    
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void mostrar_tabla(String nombre_tabla){
        String select =" select";
        String todas= " * ";
        String from = " from BAR_RESTAURANTE.";
        String empleados=nombre_tabla;
        String consulta = select+todas+from+empleados; //enviamos nuestras consultas

        try {
            //para establecer la conexxioon 
            Base_Datos conexion = new Base_Datos();
            Statement statement = conexion.conexion();
            
            //para realizar la consulta
            ResultSet resultSet = statement.executeQuery(consulta);
            //vector para los nombres de las columnas 
            Vector miListaDeCampos = new Vector();
            int numeroDeCampos;
            ResultSetMetaData miMetaData;
            miMetaData = resultSet.getMetaData();
            
            numeroDeCampos=miMetaData.getColumnCount();
            jComboBox2.removeAllItems();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                if(i<1){
                    jComboBox2.addItem(miMetaData.getColumnName(i+1));
                }
                
            }

            //DefaultTableModel en base a nuestro vector
            DefaultTableModel miDefaultTableModel = new DefaultTableModel(miListaDeCampos,0);
            //reorrido con el ResultSet
            int count=0;
            jComboBox3.removeAllItems();
            while (resultSet.next()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                //System.out.println("Registros: "+count);
                //System.out.println("datos de las filas "+resultSet.getString(count));
                for (int i=0;i<numeroDeCampos;i++){//sirve para el objeto mirReglo carge con cada columna del obejto de resultSet
                    miReglon[i]=resultSet.getObject(i+1);
                    //System.out.println("datos  "+miReglon[i]);
                    //jComboBox1.addItem(miReglon[i].toString());
                }
                 miDefaultTableModel.addRow(miReglon);
                 tabla_eliminar_datos_registro.setModel(miDefaultTableModel);
            }
            
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        text_area_eliminar_registro = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_eliminar_datos_registro = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Elija el Nombre de la tabla:");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Insertar:");

        jLabel4.setText("Elija el campo:");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Elija el valor del campo:");

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Cosulta:");

        text_area_eliminar_registro.setColumns(20);
        text_area_eliminar_registro.setRows(5);
        jScrollPane2.setViewportView(text_area_eliminar_registro);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(105, 105, 105))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setText("Tabla:");

        tabla_eliminar_datos_registro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla_eliminar_datos_registro);

        jButton1.setText("Ejecutar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if(jComboBox2.getSelectedItem()!=null){ 
            String nombre_campo=jComboBox2.getSelectedItem().toString();
            System.out.println("Nombre del campo:  "+nombre_campo);
            nombre_campo_global=nombre_campo;
            //datos_eliminar_registro.setCampo(nombre_campo);
            objeto_eliminar_registro.nombre_campo_interfaz_eliminar_registro(nombre_campo);
        } 
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String nomre_tabla=jComboBox1.getSelectedItem().toString();
        System.out.println("nombre tabla: "+nomre_tabla);
        mostrar_tabla(nomre_tabla);
        mostrar_IDS(nombre_campo_global,nomre_tabla);
        //datos_eliminar_registro.setNombre_tabla(nomre_tabla);
        objeto_eliminar_registro.nombre_tabla_interfaz_eliminar_registro(nomre_tabla);
        global_nombre_tabla=nomre_tabla;
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
       if(jComboBox3.getSelectedItem()!=null){
            System.out.println("Valor ID: "+jComboBox3.getSelectedItem());
            //datos_eliminar_registro.setValor_campo((String) jComboBox3.getSelectedItem());
            objeto_eliminar_registro.valor_campo_interfaz_eliminar_registro((String) jComboBox3.getSelectedItem());
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        objeto_eliminar_registro.ejecutar_eliminar_regirto();
        mostrar_IDS(nombre_campo_global,global_nombre_tabla);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Producto_Eliminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Producto_Eliminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Producto_Eliminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Producto_Eliminar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Producto_Eliminar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JTable tabla_eliminar_datos_registro;
    public static javax.swing.JTextArea text_area_eliminar_registro;
    // End of variables declaration//GEN-END:variables
}
