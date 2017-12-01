package com.condominiosweb.bl;

import com.condominiosweb.dao.TipoDocumentoDao;
import com.condominiosweb.util.CWResultado;

public class TipoDocumentoBL {
    private TipoDocumentoDao tipoDocumentoDao;
    
    public TipoDocumentoBL(){
        tipoDocumentoDao = new TipoDocumentoDao();
    }
    
    public CWResultado listarTipoDocumento(){
        CWResultado res;
        try{
            res = tipoDocumentoDao.listarTipoDocumentos();
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los tipos de documento.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
