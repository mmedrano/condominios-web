package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Condominio;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class ViviendaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarVivienda(Vivienda vivienda){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_VIVIENDA_REGISTRAR(?,?,?,?,?,?,?)}");
            cs.setString("p_torre", vivienda.getTorre());
            cs.setInt("p_numero", vivienda.getNumero());
            cs.setDouble("p_metraje", vivienda.getMetraje());
            cs.setDate("p_fecharegistro", new java.sql.Date(vivienda.getFechaRegistro().getTime()));
            cs.setInt("p_idresidente", vivienda.getResidente().getIdResidente());
            cs.setInt("p_idcondominio", vivienda.getCondominio().getIdCondominio());
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos de la vivienda.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos de la vivienda.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado actualizarVivienda(Vivienda vivienda){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_VIVIENDA_ACTUALIZAR(?,?,?,?,?,?,?,?)}");
            cs.setInt("p_idvivienda", vivienda.getIdVivienda());
            cs.setString("p_torre", vivienda.getTorre());
            cs.setInt("p_numero", vivienda.getNumero());
            cs.setDouble("p_metraje", vivienda.getMetraje());
            cs.setDate("p_fecharegistro", new java.sql.Date(vivienda.getFechaRegistro().getTime()));
            cs.setInt("p_idresidente", vivienda.getResidente().getIdResidente());
            cs.setInt("p_idcondominio", vivienda.getCondominio().getIdCondominio());
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se actualizaron los datos de la vivienda.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al actualizar los datos de la vivienda.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado obtenerVivienda(int idVivienda){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_VIVIENDA_OBTENER(?)}");
            cs.setInt("p_idvivienda", idVivienda);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            
            if(rs != null){
                while(rs.next()){
                    Vivienda vivienda = new Vivienda();
                    vivienda.setIdVivienda(rs.getInt("idvivienda"));
                    vivienda.setTorre(rs.getString("torre"));
                    vivienda.setNumero(rs.getInt("numero"));
                    vivienda.setMetraje(rs.getDouble("metraje"));
                    vivienda.setFechaRegistro(new java.util.Date(rs.getDate("fecharegistro").getTime()));
                    vivienda.setResidente(new Residente(rs.getInt("idresidente")));
                    vivienda.setCondominio(new Condominio(rs.getInt("idcondominio")));
                    resultado.setEntidad(vivienda);
                }
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de la vivienda.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarViviendas(String filtro1, String filtro2){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_VIVIENDA_LISTAR(?,?)}");
            cs.setString("p_torre", filtro1);
            cs.setString("p_numero", filtro2);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Vivienda> listaViviendas = new ArrayList<Vivienda>();
            
            if(rs != null){
                while(rs.next()){
                    Vivienda vivienda = new Vivienda();
                    vivienda.setIdVivienda(rs.getInt("idvivienda"));
                    vivienda.setTorre(rs.getString("torre"));
                    vivienda.setNumero(rs.getInt("numero"));
                    vivienda.setMetraje(rs.getDouble("metraje"));
                    vivienda.setFechaRegistro(new java.util.Date(rs.getDate("fecharegistro").getTime()));
                    
                    Residente residente = new Residente();
                    residente.setIdResidente(rs.getInt("idresidente"));
                    residente.setNombreCompleto(rs.getString("nombrecompleto"));
                    vivienda.setResidente(residente);
                    
                    Condominio condominio = new Condominio();
                    condominio.setIdCondominio(rs.getInt("idcondominio"));
                    condominio.setNombre(rs.getString("condominio"));
                    vivienda.setCondominio(condominio);
                    
                    listaViviendas.add(vivienda);
                }
            }
            resultado.setListaEntidades(listaViviendas);
            
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de viviendas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
}
