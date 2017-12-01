package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Junta;
import com.condominiosweb.model.JuntaResidente;
import com.condominiosweb.model.Residente;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class JuntaResidenteDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarJuntaResidente(JuntaResidente jResidente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_JUNTARESIDENTE_REGISTRAR(?,?,?)}");
            cs.setInt("p_idjunta", jResidente.getJunta().getIdJunta());
            cs.setInt("p_idresidente", jResidente.getResidente().getIdResidente());
            cs.registerOutParameter("p_mensaje", Types.VARCHAR);
            
            int resEjecucion = cs.executeUpdate();
            String mensajeEjecucion = cs.getString("p_mensaje");
            resultado = new CWResultado();
            if(!mensajeEjecucion.equals("")){
                resultado.setMensaje(mensajeEjecucion);
            }else if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos del residente de la junta.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos del residente de la junta.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarResidentesJunta(int idJunta){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_JUNTARESIDENTE_LISTAR(?)}");
            cs.setInt("p_idjunta", idJunta);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<JuntaResidente> listaResidentes = new ArrayList<JuntaResidente>();
            
            if(rs != null){
                while(rs.next()){
                    JuntaResidente jr = new JuntaResidente();
                    Junta j = new Junta();
                    j.setIdJunta(rs.getInt("idjunta"));
                    
                    Residente r = new Residente();
                    r.setIdResidente(rs.getInt("idresidente"));
                    r.setNombreCompleto(rs.getString("nombrecompleto"));
                    
                    jr.setJunta(j);
                    jr.setResidente(r);
                    listaResidentes.add(jr);
                }
            }
            resultado.setListaEntidades(listaResidentes);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar los residentes de la junta.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
