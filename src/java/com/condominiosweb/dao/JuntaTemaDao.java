package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Junta;
import com.condominiosweb.model.JuntaResidente;
import com.condominiosweb.model.JuntaTema;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class JuntaTemaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarJuntaTema(JuntaTema jtema){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_JUNTATEMA_REGISTRAR(?,?,?,?)}");
            cs.setInt("p_idjunta", jtema.getJunta().getIdJunta());
            cs.setString("p_tema", jtema.getTema());
            cs.setString("p_acuerdo", jtema.getAcuerdo());
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos del tema de la junta.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos del tema de la junta.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarTemasJunta(int idJunta){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_JUNTATEMA_LISTAR(?)}");
            cs.setInt("p_idjunta", idJunta);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<JuntaTema> listaTemas = new ArrayList<JuntaTema>();
            
            if(rs != null){
                while(rs.next()){
                    JuntaTema jt = new JuntaTema();
                    Junta j = new Junta();
                    j.setIdJunta(rs.getInt("idjunta"));
                    jt.setTema(rs.getString("tema"));
                    jt.setAcuerdo(rs.getString("acuerdo"));
                    jt.setJunta(j);
                    listaTemas.add(jt);
                }
            }
            resultado.setListaEntidades(listaTemas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar los temas de la junta.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
