package com.condominiosweb.bl;

import com.condominiosweb.dao.AreaComunDao;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;

public class AreaComunBL {
    private AreaComunDao areaComunDao;
    
    public AreaComunBL(){
        areaComunDao = new AreaComunDao();
    }
    
    public CWResultado listarAreasComunes(String condominio){
        CWResultado res;
        try{
            int idcondominio = CWString.toInt(condominio);
            res = areaComunDao.listarAreasComunes(idcondominio);
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al obtener las cuotas de la vivienda.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
}
