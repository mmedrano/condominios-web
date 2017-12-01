package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.TipoDocumento;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoDocumentoDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado listarTipoDocumentos(){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_TIPODOCUMENTO_LISTAR()}");
            cs.execute();
            
            resultado = new CWResultado();
            ResultSet rs = cs.getResultSet();
            ArrayList<TipoDocumento> listaTipoDoc = new ArrayList<TipoDocumento>();
            if(rs != null){
                while(rs.next()){
                    TipoDocumento tipoDoc = new TipoDocumento();
                    tipoDoc.setIdtipodocumento(rs.getInt("idtipodocumento"));
                    tipoDoc.setTipoDocumento(rs.getString("tipodocumento"));
                    tipoDoc.setNombreDocumento(rs.getString("nombredocumento"));
                    listaTipoDoc.add(tipoDoc);
                }
            }
            resultado.setListaEntidades(listaTipoDoc);
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al consultar los tipos de documento.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
