package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.TipoPago;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoPagoDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado listarTipoPago(){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_LISTAR_TIPOPAGO()}");
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<TipoPago> lstTipoPago = new ArrayList<TipoPago>();
            if(rs != null){
                while(rs.next()){
                    TipoPago tipo = new TipoPago();
                    tipo.setIdTipoPago(rs.getInt("idtipopago"));
                    tipo.setTipoPago(rs.getString("tipopago"));
                    lstTipoPago.add(tipo);
                }
            }
            resultado.setListaEntidades(lstTipoPago);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar los tipo de pago.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
