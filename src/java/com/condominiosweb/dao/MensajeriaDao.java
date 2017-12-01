package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Mensajeria;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MensajeriaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarMensaje(Mensajeria mensaje){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_MENSAJERIA_REGISTRAR(?,?,?)}");
            cs.setString("p_titulo", mensaje.getTitulo());
            cs.setString("p_contenido", mensaje.getContenido());
            cs.setTimestamp("p_fechapublicacion", new java.sql.Timestamp(mensaje.getFechaPublicacion().getTime()));
            
            int resEjecucion = cs.executeUpdate();
            resultado = new CWResultado();
            if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos del mensaje.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos del mensaje.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarMensajes(){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_MENSAJERIA_LISTAR()}");
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Mensajeria> listaMensajes = new ArrayList<Mensajeria>();
            
            if(rs != null){
                while(rs.next()){
                    Mensajeria mensaje = new Mensajeria();
                    mensaje.setIdMensajeria(rs.getInt("idmensajeria"));
                    mensaje.setTitulo(rs.getString("titulo"));
                    mensaje.setContenido(rs.getString("contenido"));
                    mensaje.setFechaPublicacion(new java.util.Date(rs.getTimestamp("fechapublicacion").getTime()));
                    mensaje.setStrFechaPublicacion(CWUtil.dateToString(mensaje.getFechaPublicacion(), "yyyy-MM-dd hh:mm:ss a"));
                    listaMensajes.add(mensaje);
                }
            }
            resultado.setListaEntidades(listaMensajes);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar los mensajes.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
