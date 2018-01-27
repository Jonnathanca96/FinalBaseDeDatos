/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author User
 */
public class Objeto {
    public static String nombre_tabla;
    public static String campo_nombre;
    public static String datos_insertar;
    //Datos para eliminar un registro
    public static String campo;
    public static String valor_campo;
    //para actualizar un registro
    public static String campo_actualizar;
    public static String dato=null; 
    public static String id;

    //para identificar al usuario
    public static String nombre_usurio="";
    public static String contrasena="";

    public static String getNombre_usurio() {
        return nombre_usurio;
    }

    public static void setNombre_usurio(String nombre_usurio) {
        Objeto.nombre_usurio = nombre_usurio;
    }

    public static String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        Objeto.contrasena = contrasena;
    }

   
    
    public static String getCampo_actualizar() {
        return campo_actualizar;
    }

    public static void setCampo_actualizar(String campo_actualizar) {
        Objeto.campo_actualizar = campo_actualizar;
    }

    public static String getDato() {
        return dato;
    }

    public static void setDato(String dato) {
        Objeto.dato = dato;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Objeto.id = id;
    }
    
    
    
    public static String getCampo() {
        return campo;
    }

    public static void setCampo(String campo) {
        Objeto.campo = campo;
    }

    public static String getValor_campo() {
        return valor_campo;
    }

    public static void setValor_campo(String valor_campo) {
        Objeto.valor_campo = valor_campo;
    }
    
    
    public static String getNombre_tabla() {
        return nombre_tabla;
    }

    public static void setNombre_tabla(String nombre_tabla) {
        Objeto.nombre_tabla = nombre_tabla;
    }

    public static String getCampo_nombre() {
        return campo_nombre;
    }

    public static void setCampo_nombre(String campo_nombre) {
        Objeto.campo_nombre = campo_nombre;
    }

    

    public static String getDatos_insertar() {
        return datos_insertar;
    }

    public static void setDatos_insertar(String datos_insertar) {
        Objeto.datos_insertar = datos_insertar;
    }
    
    
}
