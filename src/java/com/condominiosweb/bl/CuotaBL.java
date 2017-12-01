package com.condominiosweb.bl;

import com.condominiosweb.dao.CuotaDao;
import com.condominiosweb.model.Cuota;
import com.condominiosweb.model.Periodo;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class CuotaBL {
    private CuotaDao cuotaDao;
    
    public CuotaBL(){
        cuotaDao = new CuotaDao();
    }
    
    public CWResultado agregarCuota(String vivienda, String periodo, String importe, String fecVenc){
        CWResultado res;
        try{
            res = validarDatosCuota(vivienda, periodo, importe, fecVenc);
            if(res.esCorrecto()){
                Cuota cuota = (Cuota) res.getEntidad();
                cuota.setFechaRegistro(new Date());
                res = cuotaDao.registrarCuota(cuota);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la cuota.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado obtenerCuota(String stridvienda, String stridperiodo){
        CWResultado resultado;
        try{
            int vivienda = CWString.toInt(stridvienda);
            int periodo = CWString.toInt(stridperiodo);
            resultado = cuotaDao.obtenerCuota(vivienda, periodo);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al obtener los datos de la cuota");
            resultado.setMensajeDetalle(ex.getMessage());
        }
        return resultado;
    }
    
    public CWResultado listarCuotas(String strvivienda){
        CWResultado res;
        try{
            int vivienda = CWString.toInt(strvivienda);
            res = cuotaDao.listarCuotas(vivienda);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener las cuotas de la vivienda.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarCuotasPorResidente(String strresidente){
        CWResultado res;
        try{
            int residente = CWString.toInt(strresidente);
            res = cuotaDao.listarCuotasPorResidente(residente);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener las cuotas del residente.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarMorosos(String stridperiodo){
        CWResultado res;
        try{
            int periodo = CWString.toInt(stridperiodo);
            res = cuotaDao.listarMorosos(periodo);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener la lista de mororos.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosCuota(String strvivienda, String strperiodo, String strimporte, String strfecVenc){
        CWResultado res;
        try{
            res = new CWResultado();
            int idvivienda = CWString.toInt(strvivienda);
            int idperiodo = CWString.toInt(strperiodo);
            double importe = CWString.toDouble(strimporte);
            Date fecVenc = CWString.toDate(strfecVenc);
            
            if(idvivienda == Integer.MIN_VALUE){
                res.setMensaje("Debe seleccionar una vivienda.");
            }else if(idperiodo == Integer.MIN_VALUE){
                res.setMensaje("Debe seleccionar un periodo.");
            }else if(Double.compare(importe, Double.NaN) == 0){
                res.setMensaje("Debe ingresar un importe válido");
            }else if(importe <= 0){
                res.setMensaje("Debe ingresar un monto mayor a 0.");
            }else if(fecVenc.compareTo(new Date(0)) == 0){
                res.setMensaje("De ingresar una fecha válida.");
            }else{
                Cuota c = new Cuota();
                c.setVivienda(new Vivienda(idvivienda));
                c.setPeriodo(new Periodo(idperiodo));
                c.setImporte(importe);
                c.setFechaVenc(fecVenc);
                res.setEntidad(c);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al validar los datos de la cuota");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
