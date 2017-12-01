package com.condominiosweb.bl;

import com.condominiosweb.dao.ReservaDao;
import com.condominiosweb.model.AreaComun;
import com.condominiosweb.model.Reserva;
import com.condominiosweb.model.Residente;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class ReservaBL {
    private ReservaDao reservaDao;
    
    public ReservaBL(){
        reservaDao = new ReservaDao();
    }
    
    public CWResultado agregarReserva(String areacomun, String residente, String fechainicio, String fechafin){
        CWResultado res;
        try{
            res = validarDatosReserva(areacomun, residente, fechainicio, fechafin);
            if(res.esCorrecto()){
                Reserva reserva = (Reserva) res.getEntidad();
                res = reservaDao.registrarReserva(reserva);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la reserva.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    public CWResultado listarReservas(String residente){
        CWResultado res;
        try{
            int idresidente = CWString.toInt(residente);
            res = reservaDao.listarReservas(idresidente);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al listar las reservas.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado validarDatosReserva(String strareacomun, String strresidente, 
            String strfechainicio, String strfechafin){
        CWResultado res;
        try{
            int areacomun = CWString.toInt(strareacomun);
            int residente = CWString.toInt(strresidente);
            Date fechainicio = CWString.toDate(strfechainicio);
            Date fechafin = CWString.toDate(strfechafin);
            
            Reserva reserva = new Reserva();
            reserva.setAreaComun(new AreaComun(areacomun, ""));
            reserva.setResidente(new Residente(residente));
            reserva.setFechaReservaInicio(fechainicio);
            reserva.setFechaReservaFin(fechafin);
            
            res = new CWResultado();
            res.setEntidad(reserva);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al ingresar los datos de la reserva.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
