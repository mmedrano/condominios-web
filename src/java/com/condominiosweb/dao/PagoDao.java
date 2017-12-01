package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Pago;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;

public class PagoDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado agregarPago(Pago pago){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_PAGO_REGISTRAR(?,?,?,?,?)}");
            cs.setInt("p_idvivienda", pago.getVivienda().getIdVivienda());
            cs.setInt("p_idperiodo", pago.getPeriodo().getIdPeriodo());
            cs.setInt("p_idtipopago", pago.getTipoPago().getIdTipoPago());
            cs.setDouble("p_importepago", pago.getImportePago());
            cs.setDate("p_fechapago", new java.sql.Date(pago.getFechaPago().getTime()));
            cs.executeUpdate();
            
            int resEjecucion = cs.executeUpdate();
            resultado = new CWResultado();
            if(resEjecucion == 0){
                resultado.setMensaje("Ocurrio un error. No se ingresaron los datos del pago.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar los datos del pago.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
