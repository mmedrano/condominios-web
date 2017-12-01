package com.condominiosweb.util;

import java.util.ArrayList;

public class CWResultado {
    private String mensaje;
    private String mensajeDetalle;
    private Object entidad;
    private ArrayList<?> listaEntidades;
    
    public CWResultado(){
        mensaje = "";
        mensajeDetalle = "";
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeDetalle() {
        return mensajeDetalle;
    }

    public void setMensajeDetalle(String mensajeDetalle) {
        this.mensajeDetalle = mensajeDetalle;
    }

    public Object getEntidad() {
        return entidad;
    }

    public void setEntidad(Object entidad) {
        this.entidad = entidad;
    }

    public boolean esCorrecto() {
        return CWString.isNullOrEmpty(mensaje);
    }
    
    public boolean tieneObservacion() {
        return !CWString.isNullOrEmpty(mensaje);
    }

    public ArrayList<?> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(ArrayList<?> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }
}
