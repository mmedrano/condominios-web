package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoDocumento;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class ResidenteDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarResidente(Residente residente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_RESIDENTE_REGISTRAR(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("p_idtipodocumento", residente.getTipoDocumento().getIdtipodocumento());
            cs.setString("p_nrodocumento", residente.getDocumento());
            cs.setString("p_apellidopaterno", residente.getApellidoPaterno());
            cs.setString("p_apellidomaterno", residente.getApellidoMaterno());
            cs.setString("p_nombres", residente.getNombres());
            cs.setDate("p_fechanac", new Date(residente.getFechaNacimiento().getTime()));
            cs.setString("p_correoelectronico", residente.getCorreo());
            cs.setString("p_clave", residente.getClave());
            cs.setString("p_estado", residente.getEstado() ? "1" : "0");
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos del residente.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos del residente.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado obtenerResidente(int idResidente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_RESIDENTE_OBTENER(?)}");
            cs.setInt("p_idresidente", idResidente);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            
            if(rs != null){
                while(rs.next()){
                    Residente residente = new Residente();
                    residente.setIdResidente(rs.getInt("idresidente"));
                    residente.setTipoDocumento(new TipoDocumento(rs.getInt("idtipodocumento"), ""));
                    residente.setDocumento(rs.getString("nrodocumento"));
                    residente.setApellidoPaterno(rs.getString("apellidopaterno"));
                    residente.setApellidoMaterno(rs.getString("apellidomaterno"));
                    residente.setNombres(rs.getString("nombres"));
                    residente.setFechaNacimiento(new java.util.Date(rs.getDate("fechanac").getTime()));
                    residente.setCorreo(rs.getString("correoelectronico"));
                    residente.setClave(rs.getString("clave"));
                    residente.setEstado(rs.getString("estado").equals("1"));
                    resultado.setEntidad(residente);
                }
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información del residente.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarResidentes(String nombre){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_RESIDENTE_LISTAR(?)}");
            cs.setString("p_nombrecompleto", nombre);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Residente> listaResidentes = new ArrayList<Residente>();
            if(rs != null){
                while(rs.next()){
                    Residente residente = new Residente();
                    residente.setIdResidente(rs.getInt("idresidente"));
                    residente.setDocumento(rs.getString("nrodocumento"));
                    residente.setNombreCompleto(rs.getString("nombrecompleto"));
                    residente.setCorreo(rs.getString("correoelectronico"));
                    listaResidentes.add(residente);
                }
            }
            resultado.setListaEntidades(listaResidentes);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de residentes.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado actualizarResidente(Residente residente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_RESIDENTE_ACTUALIZAR(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("p_idresidente", residente.getIdResidente());
            cs.setInt("p_idtipodocumento", residente.getTipoDocumento().getIdtipodocumento());
            cs.setString("p_nrodocumento", residente.getDocumento());
            cs.setString("p_apellidopaterno", residente.getApellidoPaterno());
            cs.setString("p_apellidomaterno", residente.getApellidoMaterno());
            cs.setString("p_nombres", residente.getNombres());
            cs.setDate("p_fechanac", new Date(residente.getFechaNacimiento().getTime()));
            cs.setString("p_correoelectronico", residente.getCorreo());
            cs.setString("p_clave", residente.getClave());
            cs.setString("p_estado", residente.getEstado() ? "1" : "0");
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos del residente.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al actualizar los datos del residente.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
