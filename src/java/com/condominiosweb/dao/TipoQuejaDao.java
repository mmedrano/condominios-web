package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.TipoQueja;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoQuejaDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado listarTipoPago(){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_TIPOQUEJA_LISTAR()}");
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<TipoQueja> lstTipoQueja = new ArrayList<TipoQueja>();
            if(rs != null){
                while(rs.next()){
                    TipoQueja tipo = new TipoQueja();
                    tipo.setIdTipoQueja(rs.getInt("idtipoqueja"));
                    tipo.setTipoQueja(rs.getString("tipoqueja"));
                    lstTipoQueja.add(tipo);
                }
            }
            resultado.setListaEntidades(lstTipoQueja);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar los tipo de queja.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
