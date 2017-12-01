package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.AreaComun;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AreaComunDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado listarAreasComunes(int idCondominio){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_AREACOMUN_LISTAR(?)}");
            cs.setInt("p_idcondominio", idCondominio);
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<AreaComun> listaAreaComun = new ArrayList<AreaComun>();
            
            if(rs != null){
                while(rs.next()){
                    AreaComun areaComun = new AreaComun();
                    areaComun.setIdAreaComun(rs.getInt("idareacomun"));
                    areaComun.setAreaComun(rs.getString("areacomun"));
                    listaAreaComun.add(areaComun);
                }
            }
            resultado.setListaEntidades(listaAreaComun);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al listar las areas comunes.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
