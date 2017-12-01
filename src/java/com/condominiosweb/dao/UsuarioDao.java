package com.condominiosweb.dao;

import com.condominiosweb.dao.cnx.DBConexion;
import com.condominiosweb.dao.cnx.DBUtil;
import com.condominiosweb.model.Condominio;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Usuario;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UsuarioDao {
    private Connection con;
    private CallableStatement cs;
    
    public CWResultado obtenerUsuarioLogin(Usuario usuario){
        CWResultado resultado;
        try{
            con = DBConexion.getConnection();
            cs = con.prepareCall("{call SP_USUARIO_VALIDAR(?,?)}");
            cs.setString("p_documento", usuario.getCodigo());
            cs.setString("p_clave", usuario.getContrasena());
            cs.execute();
            
            resultado = new CWResultado();
            Usuario usuarioLogueado = null;
            ResultSet rs = cs.getResultSet();
            
            if(rs != null){
                while(rs.next()){
                    usuarioLogueado = new Usuario();
                    Residente residenteLogueado = new Residente();
                    residenteLogueado.setIdResidente(rs.getInt("idresidente"));
                    residenteLogueado.setApellidoMaterno(rs.getString("apellidomaterno"));
                    residenteLogueado.setApellidoPaterno(rs.getString("apellidopaterno"));
                    residenteLogueado.setNombres(rs.getString("nombres"));
                    residenteLogueado.setNombreCompleto(rs.getString("nombrecompleto"));
                    
                    Vivienda vivienda = new Vivienda(rs.getInt("idvivienda"));
                    Condominio condominio = new Condominio(rs.getInt("idcondominio"));
                    vivienda.setCondominio(condominio);
                    
                    residenteLogueado.setVivienda(vivienda);
                    
                    usuarioLogueado.setResidente(residenteLogueado);
                }
            }
            
            if(usuarioLogueado != null){
                resultado.setEntidad(usuarioLogueado);
            }else{
                resultado.setMensaje("Usuario o contraseña incorrecto.");
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al validar los datos del usuario.");
            resultado.setMensajeDetalle(ex.getMessage() + ex.toString());
        }finally{
            DBUtil.close(cs);
            DBUtil.close(con);
        }
        return resultado;
    }
}
