package com.condominiosweb.bl;

import com.condominiosweb.dao.UsuarioDao;
import com.condominiosweb.model.Usuario;
import com.condominiosweb.util.CWResultado;
import java.util.ResourceBundle;

public class UsuarioBL {
    private UsuarioDao usuarioDao;
    
    public UsuarioBL(){
        usuarioDao = new UsuarioDao();
    }
    
    public boolean iniciarSesion(String user, String pass){
        ResourceBundle rb = ResourceBundle.getBundle("conf");
        String u = rb.getString("login.default.user");
        String p = rb.getString("login.default.pass");
        return u.equals(user) && p.equals(pass);
    }
    
    public boolean iniciarSesionAdmin(String user, String pass){
        ResourceBundle rb = ResourceBundle.getBundle("conf");
        String u = rb.getString("login.default.user");
        String p = rb.getString("login.default.pass");
        return u.equals(user) && p.equals(pass);
    }
    
    public CWResultado obtenerUsuarioLogin(String user, String pass){
        CWResultado res;
        try{
            res = obtenerUsuarioValido(user, pass);
            if(res.esCorrecto()){
                Usuario u = (Usuario) res.getEntidad();
                res = usuarioDao.obtenerUsuarioLogin(u);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al actualizar los datos del usuario.");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
    private CWResultado obtenerUsuarioValido(String u, String p){
        CWResultado res;
        try{
            res = new CWResultado();
            if(u.trim().equals("") || p.trim().equals("")){
                res.setMensaje("Ingrese usuario y contraseña");
            }else{
                Usuario usuario = new Usuario();
                usuario.setCodigo(u);
                usuario.setContrasena(p);
                res.setEntidad(usuario);
            }
        }catch(Exception ex){
            res = new CWResultado();
            res.setMensaje("Error al validar los datos del usuario");
            res.setMensajeDetalle(ex.getMessage());
        }
        return res;
    }
    
}
