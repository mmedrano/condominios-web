package com.condominiosweb.model;

public class TipoQueja {
    private int idTipoQueja;
    private String tipoQueja;

    public TipoQueja(){
        
    }
    
    public TipoQueja(int idTipoQueja) {
        this.idTipoQueja = idTipoQueja;
    }
    
    public int getIdTipoQueja() {
        return idTipoQueja;
    }

    public void setIdTipoQueja(int idTipoQueja) {
        this.idTipoQueja = idTipoQueja;
    }

    public String getTipoQueja() {
        return tipoQueja;
    }

    public void setTipoQueja(String tipoQueja) {
        this.tipoQueja = tipoQueja;
    }
    
    
}
