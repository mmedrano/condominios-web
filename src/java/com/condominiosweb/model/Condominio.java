package com.condominiosweb.model;

public class Condominio {
    private int idCondominio;
    private String nombre;
    private String descripcion;

    public Condominio(){
        
    }

    public Condominio(int idCondominio){
        this.idCondominio = idCondominio;
    }
    
    public Condominio(int idCondominio, String nombre, String descripcion) {
        this.idCondominio = idCondominio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Condominio(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(int idCondominio) {
        this.idCondominio = idCondominio;
    }
    
}
