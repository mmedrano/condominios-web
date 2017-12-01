package com.condominiosweb.model;

import java.util.Date;

public class Junta {
    private int idJunta;
    private String junta;
    private Date fechaInicio;
    private String strFechaInicio;
    private Date fechaFin;
    private String strFechaFin;
    
    public Junta(){
        
    }

    public Junta(int idJunta) {
        this.idJunta = idJunta;
    }
    
    public int getIdJunta() {
        return idJunta;
    }

    public void setIdJunta(int idJunta) {
        this.idJunta = idJunta;
    }

    public String getJunta() {
        return junta;
    }

    public void setJunta(String junta) {
        this.junta = junta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getStrFechaInicio() {
        return strFechaInicio;
    }

    public void setStrFechaInicio(String strFechaInicio) {
        this.strFechaInicio = strFechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getStrFechaFin() {
        return strFechaFin;
    }

    public void setStrFechaFin(String strFechaFin) {
        this.strFechaFin = strFechaFin;
    }
    
}
