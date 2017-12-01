package com.condominiosweb.model;

public class Usuario {
    private String codigo;
    private String contrasena;
    private Residente residente;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }
    
    public boolean esResidente(){
        return this.residente != null;
    }
}
