package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.EstadoQueja;
import com.condominiosweb.model.Queja;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoQueja;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuejaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado registrarQueja(Queja queja){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_QUEJA_REGISTRAR(?,?,?,?)}");
            cs.setInt("p_idresidente", queja.getResidente().getIdResidente());
            cs.setInt("p_idtipoqueja", queja.getTipoQueja().getIdTipoQueja());
            cs.setString("p_motivo", queja.getMotivo());
            cs.setDate("p_fechaqueja", new java.sql.Date(queja.getFechaQueja().getTime()));
            
            int resEjecucion = cs.executeUpdate();
            resultado = new CWResultado();
            if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos de la queja.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos de la queja.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarQuejas(int idResidente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_QUEJA_RESIDENTE_LISTAR(?)}");
            cs.setInt("p_idresidente", idResidente);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Queja> listaQuejas = new ArrayList<Queja>();
            
            if(rs != null){
                while(rs.next()){
                    Queja queja = new Queja();
                    TipoQueja tipoQueja = new TipoQueja();
                    EstadoQueja estadoQueja = new EstadoQueja();
                    
                    tipoQueja.setIdTipoQueja(rs.getInt("idtipoqueja"));
                    tipoQueja.setTipoQueja(rs.getString("tipoqueja"));
                    
                    estadoQueja.setIdEstadoQueja(rs.getInt("idestadoqueja"));
                    estadoQueja.setEstadoQueja(rs.getString("estadoqueja"));
                    
                    queja.setIdQueja(rs.getInt("idqueja"));
                    queja.setMotivo(rs.getString("motivo"));
                    queja.setFechaQueja(new java.util.Date(rs.getDate("fechaqueja").getTime()));
                    queja.setTipoQueja(tipoQueja);
                    queja.setEstadoQueja(estadoQueja);
                    
                    listaQuejas.add(queja);
                }
            }
            resultado.setListaEntidades(listaQuejas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar las quejas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado actualizarEstadoQueja(Queja queja){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_QUEJA_ACTUALIZAR_ESTADO(?,?)}");
            cs.setInt("p_idqueja", queja.getIdQueja());
            cs.setInt("p_estado", queja.getEstadoQueja().getIdEstadoQueja());
            
            int resEjecucion = cs.executeUpdate();
            resultado = new CWResultado();
            if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se actualizo el estado de la queja.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al actualizar el estado de la queja.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarQuejas(){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_QUEJA_LISTAR()}");
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Queja> listaQuejas = new ArrayList<Queja>();
            
            if(rs != null){
                while(rs.next()){
                    Queja queja = new Queja();
                    TipoQueja tipoQueja = new TipoQueja();
                    EstadoQueja estadoQueja = new EstadoQueja();
                    Residente residente = new Residente();
                    
                    residente.setNombreCompleto(rs.getString("nombrecompleto"));
                    
                    tipoQueja.setIdTipoQueja(rs.getInt("idtipoqueja"));
                    tipoQueja.setTipoQueja(rs.getString("tipoqueja"));
                    
                    estadoQueja.setIdEstadoQueja(rs.getInt("idestadoqueja"));
                    estadoQueja.setEstadoQueja(rs.getString("estadoqueja"));
                    
                    queja.setResidente(residente);
                    queja.setIdQueja(rs.getInt("idqueja"));
                    queja.setMotivo(rs.getString("motivo"));
                    queja.setFechaQueja(new java.util.Date(rs.getDate("fechaqueja").getTime()));
                    queja.setStrFechaQueja(CWUtil.dateToString(queja.getFechaQueja(), "yyyy-MM-dd"));
                    queja.setTipoQueja(tipoQueja);
                    queja.setEstadoQueja(estadoQueja);
                    
                    listaQuejas.add(queja);
                }
            }
            resultado.setListaEntidades(listaQuejas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar las quejas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
