package com.condominiosweb.bl;

import com.condominiosweb.dao.PeriodoDao;
import com.condominiosweb.util.CWResultado;

public class PeriodoBL {
    private PeriodoDao periodoDao;
    
    public PeriodoBL(){
        periodoDao = new PeriodoDao();
    }
    
    public CWResultado listarPeriodos(){
        CWResultado res;
        try{
            res = periodoDao.listarPeriodos();
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener los periodos.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
