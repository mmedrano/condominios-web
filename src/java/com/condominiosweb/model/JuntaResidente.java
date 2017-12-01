package com.condominiosweb.model;

public class JuntaResidente {
    private Junta junta;
    private Residente residente;

    public JuntaResidente(){
        
    }
    
    public JuntaResidente(int idJunta, int idResidente){
        this.junta = new Junta(idJunta);
        this.residente = new Residente(idResidente);
    }
    
    public Junta getJunta() {
        return junta;
    }

    public void setJunta(Junta junta) {
        this.junta = junta;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }
    
}
