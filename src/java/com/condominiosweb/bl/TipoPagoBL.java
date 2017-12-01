package com.condominiosweb.bl;

import com.condominiosweb.dao.TipoPagoDao;
import com.condominiosweb.util.CWResultado;

public class TipoPagoBL {
    private TipoPagoDao tipoPagoDao;
    
    public TipoPagoBL(){
        tipoPagoDao = new TipoPagoDao();
    }
    
    public CWResultado listarTipoPago(){
        CWResultado resultado;
        try{
            resultado = tipoPagoDao.listarTipoPago();
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar los tipo de pago.");
            resultado.setMensajeDetalle(ex.getMessage());
        }
        return resultado;
    }
}
