/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Logica.Objeto;
import static Presentacion.Producto_Actualizar.tabla_actualizar_registro;
import static Presentacion.Producto_Eliminar.tabla_eliminar_datos_registro;
import static Presentacion.Producto_Eliminar.text_area_eliminar_registro;
import static Presentacion.Producto_Insertar.mostrar_consulta_insertar;
import static Presentacion.Producto_Insertar.tabla_insertar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Juanma
 */
public class Base_Datos {
    
    static Objeto usuario_datos;
    Statement stmt;
    Connection c=null;
    
   
    public  Base_Datos() {
    }
    
   
    
    public void conectar() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.OracleDriver");
        c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","restaurante","1234");
        System.out.println("conexion establecida");
        stmt=c.createStatement();
    }
    
    public Statement conexion(){
        System.out.println("Nombre: "+usuario_datos.getNombre_usurio()+" COntraseña "+usuario_datos.getContrasena());
        try{  
            //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Conectando con Systes exitosamente...");
            //Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","1234");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@172.26.144.226:1521:XE",usuario_datos.getNombre_usurio(),usuario_datos.getContrasena());
            Statement statement = connection.createStatement();
            return statement;
        }catch(Exception e){
            //System.out.println("The exception raised is:" + e);
            JOptionPane.showMessageDialog(null, "Error al establecer la coneccion...");
            return null;  
        } 
    }
    
    public Statement regresar_coneccion(){
        return stmt;
    }
    public static void obtner_datos_conecion (String usuario,String contrasena){
        usuario_datos.setNombre_usurio(usuario);
        usuario_datos.setContrasena(contrasena);
    }
    
    //para insertar un nuevo producto
    
    String consulta="";
    public void ingreso_tabla(Objeto insertar){
        if(insertar.getDatos_insertar()!=null){
            consulta = "insert into BAR_RESTAURANTE."+ insertar.getNombre_tabla()+ " ("+insertar.getCampo_nombre()+") values ("+insertar.getDatos_insertar()+")";
            System.out.println(":::::: Consulta:  "+consulta);
            
            try {
                //para establecer la conexxioon 
               
                Statement statement = conexion();
                //ejecutamos la incercion
                statement.execute(consulta);// para insertar un nuevo registro en la tabla
                mostrar_consulta_insertar.setText(consulta);
                
                mostrar_tabla_insertar(insertar.getNombre_tabla());
            }catch (SQLException ex) {
                //Logger.getLogger(Consulta_Insertar_Datos_Regsitro.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
           
        }else{
            JOptionPane.showMessageDialog(null, "Tiene que ingresar una valor ");
        }
    }
    
    //consulta para elimianr un registro
    public void eliminar_registro(Objeto eliminar){
        consulta= "delete from BAR_RESTAURANTE."+eliminar.getNombre_tabla()+" where "+eliminar.getCampo()+" = '"+eliminar.getValor_campo()+"'";
        System.out.println("Consulta}}}}}} "+consulta);
         try {
                //para establecer la conexxioon 
                Base_Datos conexion = new Base_Datos();
                Statement statement = conexion.conexion();
                //ejecutamos la incercion
                statement.execute(consulta);// para insertar un nuevo registro en la tabla
               
                mostrar_tabla_eliminar(eliminar.getNombre_tabla());
                
                text_area_eliminar_registro.setText(consulta);
            }catch (SQLException ex) {
                //Logger.getLogger(Consulta_Insertar_Datos_Regsitro.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
      
    }
    //para actualizar un regisstro
    public void actualizar_registro_tabla(Objeto actualizar){
        if(actualizar.getDato()!=null){
            consulta="update BAR_RESTAURANTE."+actualizar.getNombre_tabla()+" set "+actualizar.getCampo_actualizar()+" = '"+actualizar.getDato()+"' where "+actualizar.getCampo()+" = '"+actualizar.getId()+"'";
            System.out.println("Consulta______ "+consulta);
            
            try {
                //para establecer la conexxioon 
                Base_Datos conexion = new Base_Datos();
                Statement statement = conexion.conexion();
                //ejecutamos la incercion
                statement.execute(consulta);// para insertar un nuevo registro en la tabla
                
               
                
                mostrar_tabla_actualizar(actualizar.getNombre_tabla());
            }catch (SQLException ex) {
                //Logger.getLogger(Consulta_Insertar_Datos_Regsitro.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Tiene que ingresar una valor ");
        }
    }
    
    public void mostrar_tabla_actualizar(String nombre_tabla){
        
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
            //jComboBox2.removeAllItems();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                
                //jComboBox2.addItem(miMetaData.getColumnName(i+1));
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
    }
    
        public void mostrar_tabla_eliminar(String nombre_tabla){
        
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
            //jComboBox2.removeAllItems();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                
                //jComboBox2.addItem(miMetaData.getColumnName(i+1));
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
                 tabla_eliminar_datos_registro.setModel(miDefaultTableModel);
            }
        }catch (SQLException ex) {
            //Logger.getLogger(Seleccion_Tablas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    public void mostrar_tabla_insertar(String nombre_tabla){
        
         String select =" select";
         String todas= " * ";
         String from = " from BAR_RESTAURANTE.";
         String empleados=nombre_tabla;
         String consulta = select+todas+from+empleados; //enviamos nuestras consultas
         System.out.println("Consulta: "+consulta);
         
        try {
            //para establecer la conexxioon 
         
            Statement statement = conexion();
            
            //para realizar la consulta
            
            ResultSet resultSet = statement.executeQuery(consulta);

            //vector para los nombres de las columnas 
            Vector miListaDeCampos = new Vector();
            int numeroDeCampos;
            ResultSetMetaData miMetaData;
            miMetaData = resultSet.getMetaData();
            
            numeroDeCampos=miMetaData.getColumnCount();
            //jComboBox2.removeAllItems();
            for (int i=0;i<miMetaData.getColumnCount();i++){
                //  System.out.println(miMetaData.getColumnName(i+1));
                miListaDeCampos.add(miMetaData.getColumnName(i+1));
                
                //jComboBox2.addItem(miMetaData.getColumnName(i+1));
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
                 tabla_insertar.setModel(miDefaultTableModel);
            }
        }catch (SQLException ex) {
            //Logger.getLogger(Seleccion_Tablas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }  
    }
    
    
}
