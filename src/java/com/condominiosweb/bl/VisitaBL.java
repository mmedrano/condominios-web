package com.condominiosweb.bl;

import com.condominiosweb.dao.VisitaDao;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoDocumento;
import com.condominiosweb.model.Visita;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class VisitaBL {
    private VisitaDao visitaDao;
    
    public VisitaBL(){
        visitaDao = new VisitaDao();
    }
    
    public CWResultado agregarVisita(String tipodoc, String nrodoc, String nombre, String residente,
            String fechavisita){
        CWResultado res;
        try{
            res = validarDatosVisita(tipodoc, nrodoc, nombre, residente, fechavisita);
            if(res.esCorrecto()){
                Visita visita = (Visita) res.getEntidad();
                res = visitaDao.agregarVisita(visita);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la visita.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarVisitas(String residente){
        CWResultado res;
        try{
            int idResidente = CWString.toInt(residente);
            res = visitaDao.listarVisitas(idResidente);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la visita.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosVisita(String strtipodoc, String strnrodoc, String strnombre, 
            String strresidente, String strfechavisita){
        CWResultado res = new CWResultado();
        int tipodoc = CWString.toInt(strtipodoc);
        int residente = CWString.toInt(strresidente);
        Date fechaVisita = CWString.toDate(strfechavisita);
        
        if(tipodoc == Integer.MIN_VALUE){
            res.setMensaje("Debe elegir un tipo de documento.");
        }else if(residente == Integer.MIN_VALUE){
            res.setMensaje("Debe elegir un residente.");
        }else if(CWString.isNullOrEmpty(strnrodoc)){
            res.setMensaje("El documento no puede estar vacío.");
        }else if(strnrodoc.trim().equals("")){
            res.setMensaje("El documento no puede estar vacío.");
        }else if(CWString.isNullOrEmpty(strnombre)){
            res.setMensaje("El nombre del visitante no puede estar vacío.");
        }else if(strnombre.trim().equals("")){
            res.setMensaje("El nombre del visitante no puede estar vacío.");
        }else{
            Visita visita = new Visita();
            visita.setTipoDocumento(new TipoDocumento(tipodoc, ""));
            visita.setResidente(new Residente(residente));
            visita.setNroDocumento(strnrodoc);
            visita.setNombre(strnombre);
            visita.setFechaVivista(fechaVisita);
            res.setEntidad(visita);
        }
        return res;
    }
}
