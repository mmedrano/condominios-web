package com.condominiosweb.model;

import java.util.Date;

public class Visita {
    private int idVisita;
    private TipoDocumento tipoDocumento;
    private String nroDocumento;
    private String nombre;
    private Residente residente;
    private Date fechaVivista;
    private String strFechaVivista;

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public Date getFechaVivista() {
        return fechaVivista;
    }

    public void setFechaVivista(Date fechaVivista) {
        this.fechaVivista = fechaVivista;
    }

    public String getStrFechaVivista() {
        return strFechaVivista;
    }

    public void setStrFechaVivista(String strFechaVivista) {
        this.strFechaVivista = strFechaVivista;
    }
    
}
