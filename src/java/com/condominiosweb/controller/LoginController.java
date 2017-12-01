package com.condominiosweb.controller;

import com.condominiosweb.bl.UsuarioBL;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {
    private UsuarioBL usuarioBL;
    
    public LoginController(){
        super();
        usuarioBL = new UsuarioBL();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Usuario usuario = new Usuario();
        //usuario.setCodigo(request.getParameter("usuario"));
        //usuario.setContrasena(request.getParameter("contrasena"));
        
        String codigo = request.getParameter("usuario");
        String clave = request.getParameter("contrasena");
        
        if(codigo == null || clave == null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else {
            if(usuarioBL.iniciarSesionAdmin(codigo, clave)){
                request.getSession().setAttribute("usuario", new Usuario());
                response.sendRedirect("Inicio");
            }else{
                CWResultado res = usuarioBL.obtenerUsuarioLogin(codigo, clave);
                if(res.esCorrecto()){
                    Usuario usuarioLogueado = (Usuario) res.getEntidad();
                    request.getSession().setAttribute("usuario", usuarioLogueado);
                    response.sendRedirect("Inicio");
                }else{
                    request.setAttribute("resultado", res);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
