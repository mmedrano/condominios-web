package com.condominiosweb.model;

import java.util.Date;

public class Cuota {
    private Vivienda vivienda;
    private Periodo periodo;
    private double importe;
    private Date fechaVenc;
    private String strFechaVenc;
    private Date fechaRegistro;
    private double importePagado;
    private String estado;

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFechaVenc() {
        return fechaVenc;
    }

    public void setFechaVenc(Date fechaVenc) {
        this.fechaVenc = fechaVenc;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getImportePagado() {
        return importePagado;
    }

    public void setImportePagado(double importePagado) {
        this.importePagado = importePagado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStrFechaVenc() {
        return strFechaVenc;
    }

    public void setStrFechaVenc(String strFechaVenc) {
        this.strFechaVenc = strFechaVenc;
    }
}
