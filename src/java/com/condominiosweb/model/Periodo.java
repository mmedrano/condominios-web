package com.condominiosweb.model;

public class Periodo {
    private int idPeriodo;
    private String periodo;
    
    public Periodo(){
        
    }

    public Periodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    
    public Periodo(int idPeriodo, String periodo) {
        this.idPeriodo = idPeriodo;
        this.periodo = periodo;
    }
    
    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    
}
