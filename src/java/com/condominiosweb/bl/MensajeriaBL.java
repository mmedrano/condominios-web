package com.condominiosweb.bl;

import com.condominiosweb.dao.MensajeriaDao;
import com.condominiosweb.model.Mensajeria;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class MensajeriaBL {
    private MensajeriaDao mensajeriaDao;
    
    public MensajeriaBL(){
        mensajeriaDao = new MensajeriaDao();
    }
    
    public CWResultado agregarMensaje(String titulo, String contenido){
        CWResultado res;
        try{
            res = validarDatosMensaje(titulo, contenido);
            if(res.esCorrecto()){
                Mensajeria mensaje = (Mensajeria) res.getEntidad();
                mensaje.setFechaPublicacion(new Date());
                res = mensajeriaDao.agregarMensaje(mensaje);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos del mensaje.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarMensajes(){
        CWResultado res;
        try{
            res = mensajeriaDao.listarMensajes();
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al listar los mensajes");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosMensaje(String titulo, String contenido){
        CWResultado res = new CWResultado();
        if(CWString.isNullOrEmpty(titulo)){
            res.setMensaje("El campo titulo no puede estar vacío.");
        }else if(titulo.trim().equals("")){
            res.setMensaje("El campo titulo no puede estar vacío.");
        }else if(CWString.isNullOrEmpty(contenido)){
            res.setMensaje("El campo contenido no puede estar vacío.");
        }else if(contenido.trim().equals("")){
            res.setMensaje("El campo contenido no puede estar vacío.");
        }else{
            Mensajeria mensaje = new Mensajeria();
            mensaje.setTitulo(titulo.trim());
            mensaje.setContenido(contenido.trim());
            res.setEntidad(mensaje);
        }
        return res;
    }
}
