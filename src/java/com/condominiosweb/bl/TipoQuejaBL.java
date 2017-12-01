package com.condominiosweb.bl;

import com.condominiosweb.dao.TipoQuejaDao;
import com.condominiosweb.util.CWResultado;

public class TipoQuejaBL {
    private TipoQuejaDao tipoQuejaDao;
    
    public TipoQuejaBL(){
        tipoQuejaDao = new TipoQuejaDao();
    }
    
    public CWResultado listarTipoQueja(){
        CWResultado res;
        try{
            res = tipoQuejaDao.listarTipoPago();
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al listar los tipo de queja.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
