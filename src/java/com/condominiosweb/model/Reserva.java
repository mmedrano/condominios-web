package com.condominiosweb.model;

import java.util.Date;

public class Reserva {
    private int idReserva;
    private AreaComun areaComun;
    private Residente residente;
    private Date fechaReservaInicio;
    private String strFechaReservaInicio;
    private Date fechaReservaFin;
    private String strFechaReservaFin;

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public AreaComun getAreaComun() {
        return areaComun;
    }

    public void setAreaComun(AreaComun areaComun) {
        this.areaComun = areaComun;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public Date getFechaReservaInicio() {
        return fechaReservaInicio;
    }

    public void setFechaReservaInicio(Date fechaReservaInicio) {
        this.fechaReservaInicio = fechaReservaInicio;
    }

    public String getStrFechaReservaInicio() {
        return strFechaReservaInicio;
    }

    public void setStrFechaReservaInicio(String strFechaReservaInicio) {
        this.strFechaReservaInicio = strFechaReservaInicio;
    }

    public Date getFechaReservaFin() {
        return fechaReservaFin;
    }

    public void setFechaReservaFin(Date fechaReservaFin) {
        this.fechaReservaFin = fechaReservaFin;
    }

    public String getStrFechaReservaFin() {
        return strFechaReservaFin;
    }

    public void setStrFechaReservaFin(String strFechaReservaFin) {
        this.strFechaReservaFin = strFechaReservaFin;
    }
    
}
