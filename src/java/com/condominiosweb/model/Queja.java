package com.condominiosweb.model;

import java.util.Date;

public class Queja {
    private int idQueja;
    private Residente residente;
    private TipoQueja tipoQueja;
    private EstadoQueja estadoQueja;
    private String motivo;
    private Date fechaQueja;
    private String strFechaQueja;

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public int getIdQueja() {
        return idQueja;
    }

    public void setIdQueja(int idQueja) {
        this.idQueja = idQueja;
    }
    
    public TipoQueja getTipoQueja() {
        return tipoQueja;
    }

    public void setTipoQueja(TipoQueja tipoQueja) {
        this.tipoQueja = tipoQueja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaQueja() {
        return fechaQueja;
    }

    public void setFechaQueja(Date fechaQueja) {
        this.fechaQueja = fechaQueja;
    }

    public EstadoQueja getEstadoQueja() {
        return estadoQueja;
    }

    public void setEstadoQueja(EstadoQueja estadoQueja) {
        this.estadoQueja = estadoQueja;
    }

    public String getStrFechaQueja() {
        return strFechaQueja;
    }

    public void setStrFechaQueja(String strFechaQueja) {
        this.strFechaQueja = strFechaQueja;
    }
}
