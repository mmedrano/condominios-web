package com.condominiosweb.model;

public class AreaComun {
    private int idAreaComun;
    private String areaComun;
    private Condominio condominio;
    
    public AreaComun(){
        
    }

    public AreaComun(int idAreaComun, String areaComun) {
        this.idAreaComun = idAreaComun;
        this.areaComun = areaComun;
    }

    public int getIdAreaComun() {
        return idAreaComun;
    }

    public void setIdAreaComun(int idAreaComun) {
        this.idAreaComun = idAreaComun;
    }

    public String getAreaComun() {
        return areaComun;
    }

    public void setAreaComun(String areaComun) {
        this.areaComun = areaComun;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
}
