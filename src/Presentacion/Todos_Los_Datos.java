/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;


import Datos.Base_Datos;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juanma
 */
public class Todos_Los_Datos extends javax.swing.JDialog {

    /**
     * Creates new form MostrarDatosGUI
     */
    public Todos_Los_Datos() {
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
            cmbTablas.removeAllItems();
            while (resultSet.next()){
                count++;
                Object [] miReglon = new Object[numeroDeCampos];
                //System.out.println("Registros: "+count);
                //System.out.println("datos de las filas "+resultSet.getString(count));
                for (int i=0;i<numeroDeCampos;i++){//sirve para el objeto mirReglo carge con cada columna del obejto de resultSet
                    miReglon[i]=resultSet.getObject(i+1);
                    System.out.println("datos  "+miReglon[i]);
                    cmbTablas.addItem(miReglon[i].toString());
                }
                 
            }
            
        }catch(Exception e){
            //System.out.println("The exception raised is:" + e);
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void mostrar_tabla(String nombre_tabla){
        
        System.out.println("************************");
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
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                
                
                
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
                 tblContenido.setModel(miDefaultTableModel);
            }
            
            
        }catch (SQLException ex) {
            //Logger.getLogger(Seleccion_Tablas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        //Logger.getLogger(Seleccion_Tablas.class.getName()).log(Level.SEVERE, null, ex);
        
            
         
    }
     
    
    public Todos_Los_Datos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbTablas = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblContenido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre de las Tablas");

        cmbTablas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione una tabla" }));
        cmbTablas.setToolTipText("");
        cmbTablas.setName(""); // NOI18N
        cmbTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTablasActionPerformed(evt);
            }
        });

        tblContenido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblContenido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(cmbTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTablasActionPerformed
        // TODO add your handling code here:
        if(cmbTablas.getSelectedItem()!=null){
            System.out.println("****************************************");
            String nomre_tabla=cmbTablas.getSelectedItem().toString();
            System.out.println("nombre tabla: "+nomre_tabla);
            mostrar_tabla(nomre_tabla);
            //mostrar_IDS(nombre_campo_global, nomre_tabla);
            //actualizar_registro_tabla.setNombre_tabla(nomre_tabla);//guardamosel nombre de la tabla
            //objeto_actualizar_tabla.nombre_tabla_interfas_actualizar_tabla(nomre_tabla);
        }
    }//GEN-LAST:event_cmbTablasActionPerformed

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
            java.util.logging.Logger.getLogger(Todos_Los_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Todos_Los_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Todos_Los_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Todos_Los_Datos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Todos_Los_Datos dialog = new Todos_Los_Datos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void actualizarTabla(String nombreTabla){        
        DefaultTableModel modelo = (DefaultTableModel) tblContenido.getModel();
    }
    
    private void limpiaTabla(){
        DefaultTableModel temp = (DefaultTableModel) tblContenido.getModel();
        temp.setColumnCount(0);
        temp.setRowCount(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbTablas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tblContenido;
    // End of variables declaration//GEN-END:variables
}
