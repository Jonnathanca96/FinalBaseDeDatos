/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;


import Datos.Base_Datos;
import Logica.Objeto_Instanciar;
import static Presentacion.Producto_Dato_campo.jLabel1_campo;
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
public class Producto_Insertar extends javax.swing.JFrame {
    //Negocios.Objeto_Insertar_Registro insertar_registro;
    Objeto_Instanciar objeto_instanciar;
    //variables de los campos para concatenar
     String global_nombre_tabla;
    String campos_concaenados;
    int numero_campos;
    String[] campos = new String [100];
    
    //variables de los cdatos para concatenar
   String datos_cancatennados;
   int numero_dato;
   String datos[] = new String [100];
    
    /**
     * Creates new form Insertar
     */
    public Producto_Insertar() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        actualizarJTabale();
    }
    
     public void actualizarJTabale(){
        try{  
            //para establecer la conexxioon 
            Base_Datos conexion = new Base_Datos();
            Statement statement = conexion.conexion();
            
            //para realizar la consulta
            String consulta = "SELECT table_name FROM all_tables WHERE OWNER='BAR_RESTAURANTE'"; 
            ResultSet resultSet = statement.executeQuery(consulta);
            //vector para los nombres de las columnas 
            Vector miListaDeCampos = new Vector();
            int numeroDeCampos;
            ResultSetMetaData miMetaData;
            miMetaData = resultSet.getMetaData();
            
            numeroDeCampos=miMetaData.getColumnCount();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
            }
            //DefaultTableModel en base a nuestro vector
            DefaultTableModel miDefaultTableModel = new DefaultTableModel(miListaDeCampos,0);
            //reorrido con el ResultSet
            int count=0;
            while (resultSet.next()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                for (int i=0;i<numeroDeCampos;i++){//sirve para el objeto mirReglo carge con cada columna del obejto de resultSet
                    miReglon[i]=resultSet.getObject(i+1);
                    jComboBox1.addItem(miReglon[i].toString());
                }
                 
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     public void mostrar_campos_tabla(String nombre_tabla){
     
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
            
            String[] camposdeTabla = new String[100];
            
            numeroDeCampos=miMetaData.getColumnCount();
            //jComboBox2.removeAllItems();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                camposdeTabla[i]=miMetaData.getColumnName(i+1);
            }
            miListaDeCampos.add("campos");//con esto estamos creacnod un solo campo

            //DefaultTableModel en base a nuestro vector
            DefaultTableModel miDefaultTableModel = new DefaultTableModel(miListaDeCampos,0);
            //reorrido con el ResultSet
            int count=0,h=0;
            while (h<miMetaData.getColumnCount()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                miReglon[0]=camposdeTabla[h];
                miDefaultTableModel.addRow(miReglon);
                tabla_campos.setModel(miDefaultTableModel);
                h++;
            }
            
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
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
           
            for (int i=0;i<miMetaData.getColumnCount();i++){
                miListaDeCampos.add(miMetaData.getColumnName(i+1));                

            }

            //DefaultTableModel en base a nuestro vector
            DefaultTableModel miDefaultTableModel = new DefaultTableModel(miListaDeCampos,0);
            //reorrido con el ResultSet
            int count=0;
            while (resultSet.next()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                for (int i=0;i<numeroDeCampos;i++){//sirve para el objeto mirReglo carge con cada columna del obejto de resultSet
                    miReglon[i]=resultSet.getObject(i+1);
                }
                 miDefaultTableModel.addRow(miReglon);
                 tabla_insertar.setModel(miDefaultTableModel);
            } 
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    
    public void ingresa_campos_concatenar(String campo){
        
        if(campo!=null){
            campos[numero_campos]=campo;
            numero_campos++;
            
        }
        
    }
    
    public void insertar_dato_concatenar(String dato){
        if(dato!=null){
            datos[numero_dato]=dato;
            numero_dato++;
        }else{
            System.out.println("Eroro dato no ingresado ");
            JOptionPane.showMessageDialog(null, "Error tiene que ingresar un dato primero!");
        }
    }
    
    
    //devuelve la variable que va a contener los campos que el usurio elija 
    public String devolver_campos_concatenados(){
        String dato_concatenado="";
        campos_concaenados=" ";
        for (int j=0;j<numero_campos;j++){
            if(j==numero_campos-1){
                dato_concatenado=campos[j];
            }else{
               dato_concatenado=campos[j]+", ";
            }
            campos_concaenados=campos_concaenados+dato_concatenado;
        }
        
        return campos_concaenados;
    }
    
    public String devolver_datos_concatenados(){
        String datos_concate=" ";
        datos_cancatennados= " ";
        for (int j=0;j<numero_dato;j++){
            if(j==numero_dato-1){
                datos_concate="'"+datos[j]+"'";
            }else{
                datos_concate="'"+datos[j]+"', ";
            }
            datos_cancatennados=datos_cancatennados+datos_concate;
        }
        
        return datos_cancatennados;
    }
    
    public void inicializar_variables(){
        
        numero_campos=0;
        campos_concaenados=" ";
    }
    
    public void inciar_variables_datos_concatenados(){
        numero_dato=0;
        datos_cancatennados=" ";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_campos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_insertar = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        mostrar_consulta_insertar = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Elija la tabla en la que decea ingresar datos:");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla_campos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_campos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_camposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_campos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Elija el campo a ingrear", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, Short.MAX_VALUE))
        );

        jLabel1.setText("Tabla");

        tabla_insertar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabla_insertar);

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        mostrar_consulta_insertar.setColumns(20);
        mostrar_consulta_insertar.setRows(5);
        jScrollPane3.setViewportView(mostrar_consulta_insertar);

        jButton1.setText("Ejecutar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addContainerGap())))
        );

        jLabel7.setText("Consulta:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7)))
                        .addGap(6, 6, 6)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String nomre_tabla=jComboBox1.getSelectedItem().toString();
        System.out.println("nombre tabla: "+nomre_tabla);
        
        
        //insertar_registro.setNombre_tabla(nomre_tabla);//guardamos el nombre de la tabla
        objeto_instanciar.insertar_nombre_tabla(nomre_tabla);
        mostrar_tabla(nomre_tabla);
        
        mostrar_campos_tabla(nomre_tabla);
        
        inicializar_variables();
        inciar_variables_datos_concatenados();
        objeto_instanciar.inciar_variables_datos_concatenados();
        objeto_instanciar.inicializar_variables();
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        objeto_instanciar.inciar_variables_datos_concatenados();
        objeto_instanciar.inicializar_variables();
        objeto_instanciar.enviar_ejecutar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //System.exit(1);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla_camposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_camposMouseClicked
        // TODO add your handling code here:
       int seleccion = tabla_campos.rowAtPoint(evt.getPoint());
        String dato_para_camposss= tabla_campos.getValueAt(seleccion, 0).toString();
        
        System.out.println("campo seleccionado "+dato_para_camposss);
        
        
        
        if(objeto_instanciar.validar(dato_para_camposss)==0){
            new Producto_Dato_campo().setVisible(true);
            jLabel1_campo.setText(dato_para_camposss);
        } else{
                JOptionPane.showMessageDialog(null, "El campo "+dato_para_camposss+" ya se eligio");
            }
    }//GEN-LAST:event_tabla_camposMouseClicked

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
            java.util.logging.Logger.getLogger(Producto_Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Producto_Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Producto_Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Producto_Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Producto_Insertar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextArea mostrar_consulta_insertar;
    public static javax.swing.JTable tabla_campos;
    public static javax.swing.JTable tabla_insertar;
    // End of variables declaration//GEN-END:variables
}
