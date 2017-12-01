package com.condominiosweb.bl;

import com.condominiosweb.dao.JuntaDao;
import com.condominiosweb.dao.JuntaResidenteDao;
import com.condominiosweb.dao.JuntaTemaDao;
import com.condominiosweb.model.Junta;
import com.condominiosweb.model.JuntaResidente;
import com.condominiosweb.model.JuntaTema;
import com.condominiosweb.model.Residente;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class JuntaBL {
    private JuntaDao juntaDao;
    private JuntaTemaDao juntaTemaDao;
    private JuntaResidenteDao juntaResidenteDao;
    
    public JuntaBL(){
        juntaDao = new JuntaDao();
        juntaTemaDao = new JuntaTemaDao();
        juntaResidenteDao = new JuntaResidenteDao();
    }
    
    public CWResultado agregarJunta(String junta, String fechaInicio, String fechaFin, 
            String[] residentes, String[] temas, String[] acuerdos){
        CWResultado res;
        try{
            res = new CWResultado();
            if(residentes.length == 0){
                res.setMensaje("Debe seleccionar al menos un residente");
            }else if(temas.length == 0){
                res.setMensaje("Debe especificar al menos un tema.");
            }else if(acuerdos.length == 0){
                res.setMensaje("Debe especificar al menos un acuerdo.");
            }else if(temas.length != acuerdos.length){
                res.setMensaje("Deben existir la misma cantidad de temas y acuerdos.");
            }else{
                res = validarDatosJunta(junta, fechaInicio, fechaFin);
                if(res.esCorrecto()){
                    Junta nuevaJunta = (Junta) res.getEntidad();
                    res = juntaDao.agregarJunta(nuevaJunta);
                    if(res.esCorrecto()){
                        nuevaJunta = (Junta) res.getEntidad();
                        res = agregarResidentesJunta(residentes, nuevaJunta.getIdJunta());
                        if(res.esCorrecto()){
                            res = agregasTemasJunta(temas, acuerdos, nuevaJunta.getIdJunta());
                        }
                    }
                }
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la junta.");
            res.setMensajeDetalle(ex.getMessage() + ex.toString());
        }
        return res;
    }
    
    public CWResultado listarJuntas(){
        CWResultado res = juntaDao.listarJuntas();
        return res;
    }
    
    public CWResultado listarResidentesJunta(String strjunta){
        int idjunta = CWString.toInt(strjunta);
        CWResultado res = juntaResidenteDao.listarResidentesJunta(idjunta);
        return res;
    }
    
    public CWResultado listarTemasJunta(String strjunta){
        int idjunta = CWString.toInt(strjunta);
        CWResultado res = juntaTemaDao.listarTemasJunta(idjunta);
        return res;
    }
    
    public CWResultado obtenerJunta(String strjunta){
        int idjunta = CWString.toInt(strjunta);
        CWResultado res = juntaDao.obtenerJunta(idjunta);
        return res;
    }
    
    private CWResultado agregarResidentesJunta(String[] residentes, int idjunta){
        CWResultado res;
        try{
            res = new CWResultado();
            for(String r : residentes){
                int idresidente = CWString.toInt(r);
                if(idresidente > 0){
                    JuntaResidente jResidente = new JuntaResidente(idjunta, idresidente);
                    res = juntaResidenteDao.agregarJuntaResidente(jResidente);
                }
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos del los residentes.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado agregasTemasJunta(String[] temas, String[] acuerdos, int idjunta){
        CWResultado res;
        try{
            res = new CWResultado();
            if(temas.length == acuerdos.length){
                for(int i = 0; i < temas.length; i++){
                    JuntaTema jt = new JuntaTema();
                    jt.setJunta(new Junta(idjunta));
                    jt.setTema(temas[i]);
                    jt.setAcuerdo(acuerdos[i]);
                    res = juntaTemaDao.agregarJuntaTema(jt);
                }
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los temas de la junta.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosJunta(String junta, String strfechaInicio, String strfechaFin){
        CWResultado res = new CWResultado();
        try{
            Date fechaInicio = CWString.toDate(strfechaInicio);
            Date fechaFin = CWString.toDate(strfechaFin);
            
            if(CWString.isNullOrEmpty(junta)){
                res.setMensaje("Debe especificar el asunto de la junta.");
            }else if(junta.trim().equals("")){
                res.setMensaje("Debe especificar el asunto de la junta.");
            }else{
                Junta nuevaJunta = new Junta();
                nuevaJunta.setJunta(junta);
                nuevaJunta.setFechaInicio(fechaInicio);
                nuevaJunta.setFechaFin(fechaFin);
                res.setEntidad(nuevaJunta);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al validar los datos de la junta.");
            res.setMensajeDetalle(ex.getMessage() + ex.toString());
        }
        return res;
    }
}
