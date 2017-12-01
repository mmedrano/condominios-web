package com.condominiosweb.model;

public class TipoDocumento {
    private int idtipodocumento;
    private String tipoDocumento;
    private String nombreDocumento;

    public TipoDocumento() {
    }

    public TipoDocumento(int idtipodocumento, String tipoDocumento) {
        this.idtipodocumento = idtipodocumento;
        this.tipoDocumento = tipoDocumento;
    }
    
    public int getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(int idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
        
}
