/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Base_Datos;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Objeto_Instanciar {
    static Objeto objeto;
    
    //variables de los campos para concatenar
    public static String global_nombre_tabla;
    public static String campos_concaenados;
    public static int numero_campos;
    public static String[] campos = new String [100];
    
    //variables de los cdatos para concatenar
   public static String datos_cancatennados;
   public static int numero_dato;
   public static String datos[] = new String [100];
    
    //insertar un nuevo registro
    public static void insertar_nombre_tabla(String nombre_tabla){
        objeto.setNombre_tabla(nombre_tabla);
    }
    public static int  validar(String dato_para_camposss){
        int aux=0;
        for (int j=0;j<numero_campos;j++){
            if(dato_para_camposss==campos[j]){
                aux=1;
            }
        }
        return aux;
        
    }
    public static void ingresa_campos_concatenar(String campo){
        
        if(campo!=null){
            campos[numero_campos]=campo;
            numero_campos++;
            
        }
        
    }
    
    public static void insertar_dato_concatenar(String dato){
        if(dato!=null){
            datos[numero_dato]=dato;
            numero_dato++;
        }else{
            System.out.println("Eroro dato no ingresado ");
            JOptionPane.showMessageDialog(null, "Eroro tiene que ingresar un dato primero");
        }
    }
    
    
    //devuelve la variable que va a contener los campos que el usurio elija 
    public static  String devolver_campos_concatenados(){
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
        //insertar_registro.setCampo(campos_concaenados);
        objeto.setCampo_nombre(campos_concaenados);
        return campos_concaenados;
    }
    
    public static String devolver_datos_concatenados(){
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
        objeto.setDatos_insertar(datos_cancatennados);
        return datos_cancatennados;
    }
    
    public static void inicializar_variables(){
        
        numero_campos=0;
        campos_concaenados=" ";
    }
    
    public static void inciar_variables_datos_concatenados(){
        numero_dato=0;
        datos_cancatennados=" ";
    }
   
    public static void enviar_ejecutar(){
        Base_Datos consultar_insertar_datos_registro = new Base_Datos();
        consultar_insertar_datos_registro.ingreso_tabla(objeto);
    } 
    
    
    // PARA ELIMINAR UN REGISTRO
    
    //interfaz eliminar registro
    public static void nombre_campo_interfaz_eliminar_registro(String nombre_campo){
        objeto.setCampo(nombre_campo);
    }
    public static void nombre_tabla_interfaz_eliminar_registro(String nombre_tabla){
        objeto.setNombre_tabla(nombre_tabla);
    }
    public static void valor_campo_interfaz_eliminar_registro(String valor_campo){
        objeto.setValor_campo(valor_campo);
    }
    public static void ejecutar_eliminar_regirto(){
        Base_Datos consulta_eliminar_registro = new Base_Datos();
        consulta_eliminar_registro.eliminar_registro(objeto);
    }
    
    
    //interfaz actualizar registro tabla
    public static void nuevo_valor_interfaz_actualizar_tabla(String nuevo_valor){
        objeto.setDato(nuevo_valor);
    }
    public static void nombre_tabla_interfas_actualizar_tabla(String nombre_tabla){
        objeto.setNombre_tabla(nombre_tabla);
    }
    public static void nombre_campo_actualizar_interfaz_actualizar_tabla(String nombre_campo_actualizar){
        objeto.setCampo_actualizar(nombre_campo_actualizar);
    }
    public static void nombre_campo_interfaz_actualizar_tabla(String nombre_campo){
        objeto.setCampo(nombre_campo);
    }
    public static void valor_id_interfaz_actualizar_tabla(String valor_id){
        objeto.setId(valor_id);
    }
    public static void ejecutar_actulizar_tabla(){
        Base_Datos actualuzar_reg = new Base_Datos();
        actualuzar_reg.actualizar_registro_tabla(objeto);
    }
    
    
    //interfaz usaurio
    public static void obtener_datos_conecion_interfaz_usuario(String usuario, String contrasena){
        objeto.setNombre_usurio(usuario);
        objeto.setContrasena(contrasena);
    }
    
}
