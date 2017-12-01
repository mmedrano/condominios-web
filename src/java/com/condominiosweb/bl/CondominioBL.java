package com.condominiosweb.bl;

import com.condominiosweb.dao.CondominioDao;
import com.condominiosweb.model.Condominio;
import com.condominiosweb.util.CWResultado;

public class CondominioBL {
    private CondominioDao condominioDao;
    
    public CondominioBL(){
        condominioDao = new CondominioDao();
    }
    
    public CWResultado agregarCondominio(Condominio condominio){
        CWResultado res;
        try{
            res = validarDatosCondominio();
            if(res.esCorrecto()){
                res = condominioDao.agregarCondominio(condominio);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos del condominio.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado actualizarCondominio(Condominio condominio){
        CWResultado res;
        try{
            res = validarDatosCondominio();
            if(res.esCorrecto()){
                res = condominioDao.actualizarCondominio(condominio);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al actualizar los datos del condominio.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado obtenerCondominio(String stridcondominio){
        CWResultado res;
        try{
            int idcondominio = Integer.parseInt(stridcondominio);
            res = condominioDao.obtenerCondominio(idcondominio);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los datos del condominio.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarCondominios(String filtro){
        CWResultado res;
        try{
            res = condominioDao.listarCondominios(filtro);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los condominios.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosCondominio(){
        return new CWResultado();
    }
}
