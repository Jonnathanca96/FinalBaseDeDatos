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
public class Producto_Actualizar extends javax.swing.JFrame {
    static String nombre_campo_global;
    //Objeto_Actualizar_Tabla actualizar_registro_tabla;
    Objeto_Instanciar objeto_actualizar_tabla;
    /**
     * Creates new form Actualizar_Tabla
     */
    public Producto_Actualizar() {
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
                    System.out.println("datos  "+miReglon[i]);
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
         System.out.println("Consulta: "+consulta);
        
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
            jComboBox4.removeAllItems();
            while (resultSet.next()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                //System.out.println("Registros: "+count);
                //System.out.println("datos de las filas "+resultSet.getString(count));
                
                for (int i=0;i<numeroDeCampos;i++){//sirve para el objeto mirReglo carge con cada columna del obejto de resultSet
                    miReglon[i]=resultSet.getObject(i+1);
                    //System.out.println("datos  "+miReglon[i]);
                    jComboBox4.addItem(miReglon[i].toString());
                }
                 
            }
            
        }catch(Exception e){
            //System.out.println("The exception raised is:" + e);
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void mostrar_tabla(String nombre_tabla){
        String select =" select";
         String todas= " * ";
         String from = " from BAR_RESTAURANTE.";
         String empleados=nombre_tabla;
         String consulta = select+todas+from+empleados; //enviamos nuestras consultas
         System.out.println("Consulta: "+consulta);
         
         
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
            jComboBox3.removeAllItems();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                
                jComboBox2.addItem(miMetaData.getColumnName(i+1));
                if(i<1){
                    jComboBox3.addItem(miMetaData.getColumnName(i+1));
                }
                
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
                    //System.out.println("datos  "+miReglon[i]);
                    //jComboBox1.addItem(miReglon[i].toString());
                }
                 miDefaultTableModel.addRow(miReglon);
                 tabla_actualizar_registro.setModel(miDefaultTableModel);
            }
            
            
        }catch (SQLException ex) {
            //Logger.getLogger(Seleccion_Tablas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        //Logger.getLogger(Seleccion_Tablas.class.getName()).log(Level.SEVERE, null, ex);
        
            
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_actualizar_registro = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Elija la tabla:");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Datos a Ingresar:");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Elija el campo a actualizar los datos: ");

        jLabel4.setText("Ingrese el dato: ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Campo y valor donde se va actualizar los datos:");

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel8.setText("Codigo o ID:");

        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel6.setText("Dato del ID o Codigo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(181, 181, 181)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(174, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel7.setText("Tabla");

        tabla_actualizar_registro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla_actualizar_registro);

        jButton1.setText("Ejecutar");
        jButton1.setMaximumSize(new java.awt.Dimension(295, 137));
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nuevo_valor = jTextField1.getText();
        System.out.println("Valor: "+nuevo_valor);
        //actualizar_registro_tabla.setDato(nuevo_valor);
        objeto_actualizar_tabla.nuevo_valor_interfaz_actualizar_tabla(nuevo_valor);
        
        objeto_actualizar_tabla.ejecutar_actulizar_tabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:ç
        if(jComboBox1.getSelectedItem()!=null){
            System.out.println("****************************************");
            String nomre_tabla=jComboBox1.getSelectedItem().toString();
            System.out.println("nombre tabla: "+nomre_tabla);
            mostrar_tabla(nomre_tabla);
            mostrar_IDS(nombre_campo_global, nomre_tabla);
            //actualizar_registro_tabla.setNombre_tabla(nomre_tabla);//guardamosel nombre de la tabla
            objeto_actualizar_tabla.nombre_tabla_interfas_actualizar_tabla(nomre_tabla);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        if(jComboBox2.getSelectedItem()!=null){ 
            String nombre_campo=jComboBox2.getSelectedItem().toString();
            System.out.println("Nombre del campo:  "+nombre_campo);
            //actualizar_registro_tabla.setCampo_actualizar(nombre_campo);
            objeto_actualizar_tabla.nombre_campo_actualizar_interfaz_actualizar_tabla(nombre_campo);
        } 
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
       if(jComboBox3.getSelectedItem()!=null){ 
            String nombre_campo=jComboBox3.getSelectedItem().toString();
            nombre_campo_global=nombre_campo;
            System.out.println("Nombre del campo:  "+nombre_campo);
            //actualizar_registro_tabla.setCampo(nombre_campo);
            objeto_actualizar_tabla.nombre_campo_interfaz_actualizar_tabla(nombre_campo);
        } 
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        System.out.println("Valor ID: "+jComboBox4.getSelectedItem());
        //actualizar_registro_tabla.setId((String) jComboBox4.getSelectedItem());
        objeto_actualizar_tabla.valor_id_interfaz_actualizar_tabla((String) jComboBox4.getSelectedItem());
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(Producto_Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Producto_Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Producto_Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Producto_Actualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Producto_Actualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBox3;
    public static javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTable tabla_actualizar_registro;
    // End of variables declaration//GEN-END:variables
}
