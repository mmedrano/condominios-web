package com.condominiosweb.model;

public class TipoPago {
    private int idTipoPago;
    private String tipoPago;

    public TipoPago(){
        
    }

    public TipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }
        
    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    
    
}
