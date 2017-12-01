package com.condominiosweb.bl;

import com.condominiosweb.dao.QuejaDao;
import com.condominiosweb.model.EstadoQueja;
import com.condominiosweb.model.Queja;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoQueja;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class QuejaBL {
    private QuejaDao quejaDao;
    
    public QuejaBL(){
        quejaDao = new QuejaDao();
    }
    
    public CWResultado registrarQueja(String residente, String tipoqueja, String motivo){
        CWResultado res;
        try{
            res = validarDatosQueja(residente, tipoqueja, motivo);
            if(res.esCorrecto()){
                Queja queja = (Queja) res.getEntidad();
                queja.setFechaQueja(new Date());
                res = quejaDao.registrarQueja(queja);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la queja.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarQuejasResidente(String residente){
        CWResultado res;
        try{
            int idresidente = CWString.toInt(residente);
            res = quejaDao.listarQuejas(idresidente);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al listar las quejas.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarQuejas(){
        CWResultado res;
        try{
            res = quejaDao.listarQuejas();
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al listar las quejas.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado atenderQueja(String strqueja){
        return actualizarEstadoQueja(strqueja, "2");
    }
    
    public CWResultado rechazarQueja(String strqueja){
        return actualizarEstadoQueja(strqueja, "3");
    }
    
    private CWResultado actualizarEstadoQueja(String strqueja, String strestado){
        CWResultado res;
        try{
            int idqueja = CWString.toInt(strqueja);
            int idestado = CWString.toInt(strestado);
            
            Queja queja = new Queja();
            queja.setIdQueja(idqueja);
            EstadoQueja estado = new EstadoQueja();
            estado.setIdEstadoQueja(idestado);
            queja.setEstadoQueja(estado);
            
            res = quejaDao.actualizarEstadoQueja(queja);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al listar las quejas.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosQueja(String strresidente, String strtipoqueja, String motivo){
        CWResultado res = new CWResultado();
        try{
            int residente = CWString.toInt(strresidente);
            int tipoqueja = CWString.toInt(strtipoqueja);
            
            if(residente == Integer.MIN_VALUE){
                res.setMensaje("Debe seleccionar un residente.");
            }else if(tipoqueja == Integer.MIN_VALUE){
                res.setMensaje("Debe seleccionar una tipo de queja.");
            }else if(CWString.isNullOrEmpty(motivo)){
                res.setMensaje("El campo motivo no puede estar vacío.");
            }else if(motivo.trim().equals("")){
                res.setMensaje("El campo motivo no puede estar vacío.");
            }else{
                Queja queja = new Queja();
                queja.setResidente(new Residente(residente));
                queja.setTipoQueja(new TipoQueja(tipoqueja));
                queja.setMotivo(motivo);
                res.setEntidad(queja);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la queja.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
