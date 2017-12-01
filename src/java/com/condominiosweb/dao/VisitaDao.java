package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoDocumento;
import com.condominiosweb.model.Visita;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VisitaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarVisita(Visita visita){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_VISITA_REGISTRAR(?,?,?,?,?)}");
            cs.setInt("p_idtipodocumento", visita.getTipoDocumento().getIdtipodocumento());
            cs.setString("p_nrodocumento", visita.getNroDocumento());
            cs.setString("p_nombre", visita.getNombre());
            cs.setInt("p_idresidente", visita.getResidente().getIdResidente());
            cs.setTimestamp("p_fechavisita", new java.sql.Timestamp(visita.getFechaVivista().getTime()));
            
            int resEjecucion = cs.executeUpdate();
            resultado = new CWResultado();
            if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos de la visita.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos de la visita.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
    
    public CWResultado listarVisitas(int idResidente){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_VISITA_LISTAR(?)}");
            cs.setInt("p_idresidente", idResidente);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Visita> listaVisitas = new ArrayList<Visita>();
            
            if(rs != null){
                while(rs.next()){
                    TipoDocumento tipoDoc = new TipoDocumento();
                    tipoDoc.setIdtipodocumento(rs.getInt("idtipodocumento"));
                    
                    Residente residente = new Residente();
                    residente.setIdResidente(rs.getInt("idresidente"));
                    residente.setNombreCompleto(rs.getString("nombrecompleto"));
                    
                    Visita visita = new Visita();
                    visita.setIdVisita(rs.getInt("idvisita"));
                    visita.setNroDocumento(rs.getString("nrodocumento"));
                    visita.setNombre(rs.getString("nombre"));
                    visita.setFechaVivista(new java.util.Date(rs.getTimestamp("fechavisita").getTime()));
                    visita.setStrFechaVivista(CWUtil.dateToString(visita.getFechaVivista(), "yyyy-MM-dd hh:mm:ss a"));
                    visita.setTipoDocumento(tipoDoc);
                    visita.setResidente(residente);
                    
                    listaVisitas.add(visita);
                }
            }
            resultado.setListaEntidades(listaVisitas);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar las visitas.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
