package com.condominiosweb.bl;

import com.condominiosweb.dao.ViviendaDao;
import com.condominiosweb.model.Condominio;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class ViviendaBL {
    private ViviendaDao viviendaDao;
    
    public ViviendaBL(){
        viviendaDao = new ViviendaDao();
    }
    
    public CWResultado agregarVivienda(String residente, String condominio, String torre, 
            String numero, String metraje){
        CWResultado res;
        try{
            res = validarDatosVivienda(true, "", residente, condominio, torre, numero, metraje);
            if(res.esCorrecto()){
                Vivienda vivienda = (Vivienda) res.getEntidad();
                vivienda.setFechaRegistro(new Date());
                res = viviendaDao.agregarVivienda(vivienda);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la vivienda.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado actualizarVivienda(String idvivienda, String residente, String condominio, String torre, 
            String numero, String metraje){
        CWResultado res;
        try{
            res = validarDatosVivienda(false, idvivienda, residente, condominio, torre, numero, metraje);
            if(res.esCorrecto()){
                Vivienda vivienda = (Vivienda) res.getEntidad();
                vivienda.setFechaRegistro(new Date());
                res = viviendaDao.actualizarVivienda(vivienda);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al actualizar los datos de la vivienda.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado obtenerVivienda(String stridvivienda){
        CWResultado res;
        try{
            int idvivienda = Integer.parseInt(stridvivienda);
            res = viviendaDao.obtenerVivienda(idvivienda);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los datos de la vivienda.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarViviendas(String filtro1, String filtro2){
        CWResultado res;
        try{
            res = viviendaDao.listarViviendas(filtro1, filtro2);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los condominios.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosVivienda(boolean esNuevo, String stridvivienda, String residente, 
            String condominio, String torre, String strnumero, String strmetraje){
        CWResultado res;
        try{
            res = new CWResultado();
            int idvivienda = CWString.toInt(stridvivienda);
            int idresidente = CWString.toInt(residente);
            int idcondominio = CWString.toInt(condominio);
            double metraje = strmetraje.equals("") ? 0.00 : CWString.toDouble(strmetraje);
            int numero = CWString.toInt(strnumero);
            
            if(idvivienda == Integer.MIN_VALUE && !esNuevo){
                res.setMensaje("No se encontraron datos de la vivienda seleccionada.");
            }else if(idresidente == Integer.MIN_VALUE){
                res.setMensaje("Debe seleccionar un residente.");
            }else if(idcondominio == Integer.MIN_VALUE){
                res.setMensaje("Debe seleccionar un condominio.");
            }else if(Double.compare(metraje, Double.NaN) == 0){
                res.setMensaje("Campo metraje: Ingrese un valor válido.");
            }else if(metraje < 0.00){
                res.setMensaje("El valor de metraje debe ser mayor a 0.");
            }else if(numero == Integer.MIN_VALUE){
                res.setMensaje("Campo número: Ingrese un valor valido.");
            }else if(numero <= 0){
                res.setMensaje("El valor del campo número debe ser mayor a 0.");
            }else if(torre.trim().equals("")){
                res.setMensaje("El campo torre no puede estar vacío.");
            }else{
                Vivienda v = new Vivienda();
                v.setIdVivienda(idvivienda);
                v.setResidente(new Residente(idresidente));
                v.setCondominio(new Condominio(idcondominio));
                v.setMetraje(metraje);
                v.setNumero(numero);
                v.setTorre(torre.trim());
                res.setEntidad(v);
            }
            
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al validar los datos de la vivienda");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
