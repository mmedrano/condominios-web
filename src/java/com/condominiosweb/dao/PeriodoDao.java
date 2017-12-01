package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Periodo;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PeriodoDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado listarPeriodos(){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_PERIODO_LISTAR()}");
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<Periodo> listaPeriodos = new ArrayList<Periodo>();
            
            if(rs != null){
                while(rs.next()){
                    Periodo p = new Periodo();
                    p.setIdPeriodo(rs.getInt("idperiodo"));
                    p.setPeriodo(rs.getString("periodo"));
                    listaPeriodos.add(p);
                }
            }
            resultado.setListaEntidades(listaPeriodos);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar la información de periodos.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
