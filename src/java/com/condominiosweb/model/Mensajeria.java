package com.condominiosweb.model;

import java.util.Date;

public class Mensajeria {
    private int idMensajeria;
    private String titulo;
    private String contenido;
    private Date fechaPublicacion;
    private String strFechaPublicacion;

    public int getIdMensajeria() {
        return idMensajeria;
    }

    public void setIdMensajeria(int idMensajeria) {
        this.idMensajeria = idMensajeria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getStrFechaPublicacion() {
        return strFechaPublicacion;
    }

    public void setStrFechaPublicacion(String strFechaPublicacion) {
        this.strFechaPublicacion = strFechaPublicacion;
    }
}
