package com.condominiosweb.bl;

import com.condominiosweb.dao.ResidenteDao;
import com.condominiosweb.model.Residente;
import com.condominiosweb.util.CWResultado;

public class ResidenteBL {
    private ResidenteDao residenteDao;
    
    public ResidenteBL(){
        residenteDao = new ResidenteDao();
    }
    
    public CWResultado agregarResidente(Residente residente){
        CWResultado res;
        try{
            res = validarDatosResidente();
            if(res.esCorrecto()){
                res = residenteDao.agregarResidente(residente);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos del residente.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado actualizarResidente(Residente residente){
        CWResultado res;
        try{
            res = validarDatosResidente();
            if(res.esCorrecto()){
                res = residenteDao.actualizarResidente(residente);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al actualizar los datos del residente.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado obtenerResidente(String stridresidente){
        CWResultado res;
        try{
            int idresidente = Integer.parseInt(stridresidente);
            res = residenteDao.obtenerResidente(idresidente);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los datos del residente.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarResidentes(String filtro){
        CWResultado res;
        try{
            res = residenteDao.listarResidentes(filtro);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los residentes.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosResidente(){
        return new CWResultado();
    }
}
