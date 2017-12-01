package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.AreaComun;
import com.condominiosweb.model.Reserva;
import com.condominiosweb.model.Residente;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class ReservaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado registrarReserva(Reserva reserva){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_RESERVA_REGISTRAR(?,?,?,?,?)}");
            cs.setInt("p_idareacomun", reserva.getAreaComun().getIdAreaComun());
            cs.setInt("p_idresidente", reserva.getResidente().getIdResidente());
            cs.setTimestamp("p_fechareservainicio", new java.sql.Timestamp(reserva.getFechaReservaInicio().getTime()));
            cs.setTimestamp("p_fechareservafin", new java.sql.Timestamp(reserva.getFechaReservaFin().getTime()));
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos de la reserva.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos de la reserva.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarReservas(int idResidente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_RESERVA_LISTAR(?)}");
            cs.setInt("p_idresidente", idResidente);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();
            
            if(rs != null){
                while(rs.next()){
                    Residente residente = new Residente();
                    residente.setIdResidente(rs.getInt("idresidente"));
                    residente.setNombreCompleto(rs.getString("nombrecompleto"));
                    
                    Reserva reserva = new Reserva();
                    reserva.setIdReserva(rs.getInt("idreserva"));
                    reserva.setAreaComun(new AreaComun(rs.getInt("idareacomun"), rs.getString("areacomun")));
                    reserva.setFechaReservaFin(new java.util.Date(rs.getTimestamp("fechareservafin").getTime()));
                    reserva.setStrFechaReservaFin(CWUtil.dateToString(reserva.getFechaReservaFin(), "yyyy-MM-dd hh:mm:ss a"));
                    reserva.setFechaReservaInicio(new java.util.Date(rs.getTimestamp("fechareservainicio").getTime()));
                    reserva.setStrFechaReservaInicio(CWUtil.dateToString(reserva.getFechaReservaInicio(), "yyyy-MM-dd hh:mm:ss a"));
                    reserva.setResidente(residente);
                    listaReserva.add(reserva);
                }
            }
            resultado.setListaEntidades(listaReserva);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar las reservas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
