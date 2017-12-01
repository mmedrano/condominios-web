package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Cuota;
import com.condominiosweb.model.Periodo;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class CuotaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado registrarCuota(Cuota cuota){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CUOTA_REGISTRAR(?,?,?,?,?,?,?)}");
            cs.setInt("p_idvivienda", cuota.getVivienda().getIdVivienda());
            cs.setInt("p_idperiodo", cuota.getPeriodo().getIdPeriodo());
            cs.setDouble("p_importe", cuota.getImporte());
            cs.setDate("p_fechavenc", new java.sql.Date(cuota.getFechaVenc().getTime()));
            cs.setDate("p_fecharegistro", new java.sql.Date(cuota.getFechaRegistro().getTime()));
            cs.setString("p_estado", cuota.getEstado());
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos de la cuota.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos de la cuota.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado obtenerCuota(int idVivienda, int idPeriodo){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CUOTA_OBTENER(?,?)}");
            cs.setInt("p_idvivienda", idVivienda);
            cs.setInt("p_idperiodo", idPeriodo);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            
            if(rs!=null){
                while(rs.next()){
                    Cuota cuota = new Cuota();
                    Vivienda vivienda = new Vivienda();
                    Periodo periodo = new Periodo();
                    Residente residente = new Residente();
                    
                    residente.setNombreCompleto(rs.getString("nombrecompleto"));
                    
                    vivienda.setIdVivienda(rs.getInt("idvivienda"));
                    vivienda.setTorre(rs.getString("torre"));
                    vivienda.setNumero(rs.getInt("numero"));
                    vivienda.setResidente(residente);
                    
                    periodo.setIdPeriodo(rs.getInt("idperiodo"));
                    periodo.setPeriodo(rs.getString("periodo"));
                    
                    cuota.setEstado(rs.getString("estado"));
                    cuota.setImporte(rs.getDouble("importe"));
                    cuota.setImportePagado(rs.getDouble("importepagado"));
                    cuota.setFechaVenc(new java.util.Date(rs.getDate("fechavenc").getTime()));
                    cuota.setStrFechaVenc(CWUtil.dateToString(cuota.getFechaVenc(), "yyyy-MM-dd"));
                    cuota.setVivienda(vivienda);
                    cuota.setPeriodo(periodo);
                    
                    resultado.setEntidad(cuota);
                }
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al obtener los datos de la cuota.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarCuotas(int idVivienda){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CUOTA_LISTAR(?)}");
            cs.setInt("p_idvivienda", idVivienda);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Cuota> listaCuotas = new ArrayList<Cuota>();
            
            if(rs != null){
                while(rs.next()){
                    Cuota cuota = new Cuota();
                    cuota.setVivienda(new Vivienda(rs.getInt("idvivienda")));
                    cuota.setPeriodo(new Periodo(rs.getInt("idperiodo"), rs.getString("periodo")));
                    cuota.setImporte(rs.getDouble("importe"));
                    cuota.setFechaVenc(new java.util.Date(rs.getDate("fechavenc").getTime()));
                    cuota.setStrFechaVenc(CWUtil.dateToString(cuota.getFechaVenc(), "yyyy-MM-dd"));
                    cuota.setFechaRegistro(new java.util.Date(rs.getDate("fecharegistro").getTime()));
                    cuota.setImportePagado(rs.getDouble("importepagado"));
                    cuota.setEstado(rs.getString("estado"));
                    listaCuotas.add(cuota);
                }
            }
            resultado.setListaEntidades(listaCuotas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de cuotas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarCuotasPorResidente(int idResidente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CUOTA_LISTAR_RESIDENTE(?)}");
            cs.setInt("p_idresidente", idResidente);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Cuota> listaCuotas = new ArrayList<Cuota>();
            
            if(rs != null){
                while(rs.next()){
                    Cuota cuota = new Cuota();
                    Vivienda vivienda = new Vivienda(rs.getInt("idvivienda"));
                    vivienda.setTorre(rs.getString("torre"));
                    vivienda.setNumero(rs.getInt("numero"));
                    cuota.setVivienda(vivienda);
                    cuota.setPeriodo(new Periodo(rs.getInt("idperiodo"), rs.getString("periodo")));
                    cuota.setImporte(rs.getDouble("importe"));
                    cuota.setFechaVenc(new java.util.Date(rs.getDate("fechavenc").getTime()));
                    cuota.setStrFechaVenc(CWUtil.dateToString(cuota.getFechaVenc(), "yyyy-MM-dd"));
                    cuota.setFechaRegistro(new java.util.Date(rs.getDate("fecharegistro").getTime()));
                    cuota.setImportePagado(rs.getDouble("importepagado"));
                    cuota.setEstado(rs.getString("estado"));
                    listaCuotas.add(cuota);
                }
            }
            resultado.setListaEntidades(listaCuotas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de cuotas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarMorosos(int idPeriodo){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CUOTA_VENC_LISTAR(?)}");
            cs.setInt("p_idperiodo", idPeriodo);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Cuota> listaCuotas = new ArrayList<Cuota>();
            
            if(rs != null){
                while(rs.next()){
                    Residente residente = new Residente();
                    residente.setNombreCompleto(rs.getString("nombrecompleto"));
                    
                    Vivienda vivienda = new Vivienda();
                    vivienda.setTorre(rs.getString("torre"));
                    vivienda.setNumero(rs.getInt("numero"));
                    vivienda.setResidente(residente);
                    
                    Cuota cuota = new Cuota();
                    cuota.setPeriodo(new Periodo(rs.getInt("idperiodo")));
                    cuota.setImporte(rs.getDouble("importe"));
                    cuota.setFechaVenc(new java.util.Date(rs.getDate("fechavenc").getTime()));
                    cuota.setStrFechaVenc(CWUtil.dateToString(cuota.getFechaVenc(), "yyyy-MM-dd"));
                    cuota.setImportePagado(rs.getDouble("importepagado"));
                    cuota.setEstado(rs.getString("estado"));
                    cuota.setVivienda(vivienda);
                    
                    listaCuotas.add(cuota);
                }
            }
            resultado.setListaEntidades(listaCuotas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de morosos.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
