package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Junta;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class JuntaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarJunta(Junta junta){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_JUNTA_REGISTRAR(?,?,?,?,?)}");
            cs.setString("p_junta", junta.getJunta());
            cs.setTimestamp("p_fechajuntainicio", new java.sql.Timestamp(junta.getFechaInicio().getTime()));
            cs.setTimestamp("p_fechajuntafin", new java.sql.Timestamp(junta.getFechaFin().getTime()));
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            cs.registerOutParameter("p_idjunta", Types.INTEGER);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            int idjunta = cs.getInt("p_idjunta");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos de la junta.");
            }else if(idjunta > 0){
                Junta juntaResultado = new Junta(idjunta);
                resultado.setEntidad(juntaResultado);
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos de la junta.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado obtenerJunta(int idJunta){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_JUNTA_OBTENER(?)}");
            cs.setInt("p_idjunta", idJunta);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            
            if(rs != null){
                while(rs.next()){
                    Junta junta = new Junta();
                    junta.setIdJunta(rs.getInt("idjunta"));
                    junta.setJunta(rs.getString("junta"));
                    junta.setFechaInicio(new java.util.Date(rs.getTimestamp("fechajuntainicio").getTime()));
                    junta.setStrFechaInicio(CWUtil.dateToString(junta.getFechaInicio(), "yyyy-MM-dd hh:mm:ss a"));
                    junta.setFechaFin(new java.util.Date(rs.getTimestamp("fechajuntafin").getTime()));
                    junta.setStrFechaFin(CWUtil.dateToString(junta.getFechaFin(), "yyyy-MM-dd hh:mm:ss a"));
                    resultado.setEntidad(junta);
                }
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar las juntas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarJuntas(){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_JUNTA_LISTAR()}");
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Junta> listaJuntas = new ArrayList<Junta>();
            
            if(rs != null){
                while(rs.next()){
                    Junta junta = new Junta();
                    junta.setIdJunta(rs.getInt("idjunta"));
                    junta.setJunta(rs.getString("junta"));
                    junta.setFechaInicio(new java.util.Date(rs.getTimestamp("fechajuntainicio").getTime()));
                    junta.setStrFechaInicio(CWUtil.dateToString(junta.getFechaInicio(), "yyyy-MM-dd hh:mm:ss a"));
                    junta.setFechaFin(new java.util.Date(rs.getTimestamp("fechajuntafin").getTime()));
                    junta.setStrFechaFin(CWUtil.dateToString(junta.getFechaFin(), "yyyy-MM-dd hh:mm:ss a"));
                    listaJuntas.add(junta);
                }
            }
            resultado.setListaEntidades(listaJuntas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar las juntas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
