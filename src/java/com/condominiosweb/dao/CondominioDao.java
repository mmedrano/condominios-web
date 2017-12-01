package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.model.Condominio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class CondominioDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarCondominio(Condominio condominio){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CONDOMINIO_REGISTRAR(?,?,?)}");
            cs.setString("p_condominio", condominio.getNombre());
            cs.setString("p_descripcion", condominio.getDescripcion());
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos del condominio.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos del condominio.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado obtenerCondominio(int idCondominio){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CONDOMINIO_OBTENER(?)}");
            cs.setInt("p_idcondominio", idCondominio);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            
            if(rs != null){
                while(rs.next()){
                    Condominio condominio = new Condominio();
                    condominio.setIdCondominio(rs.getInt("idcondominio"));
                    condominio.setNombre(rs.getString("condominio"));
                    condominio.setDescripcion(rs.getString("descripcion"));
                    resultado.setEntidad(condominio);
                }
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de condominios.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarCondominios(String nombre){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CONDOMINIO_LISTAR(?)}");
            cs.setString("p_condominio", nombre);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Condominio> listaCondominios = new ArrayList<Condominio>();
            if(rs != null){
                while(rs.next()){
                    Condominio condominio = new Condominio();
                    condominio.setNombre(rs.getString("condominio"));
                    condominio.setDescripcion(rs.getString("descripcion"));
                    condominio.setIdCondominio(rs.getInt("idcondominio"));
                    listaCondominios.add(condominio);
                }
            }
            resultado.setListaEntidades(listaCondominios);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de condominios.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado actualizarCondominio(Condominio condominio){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_CONDOMINIO_ACTUALIZAR(?,?,?,?)}");
            cs.setInt("p_idcondominio", condominio.getIdCondominio());
            cs.setString("p_condominio", condominio.getNombre());
            cs.setString("p_descripcion", condominio.getDescripcion());
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se actualizaron los datos del condominio.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al actualizar los datos del condominio.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
