package com.condominiosweb.secutiry;

import com.condominiosweb.model.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectFilter implements Filter{

    FilterConfig filterConfig = null;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI();
        System.out.println(path);
        if(!path.contains("login.jsp")){
            boolean usuLog = verificarUsuarioLogueado(req);
            if(usuLog){
                chain.doFilter(request, response);
            }else{
                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath()+"/login.jsp");
            }
        }else{
            chain.doFilter(request, response);
        }
    }
    
    private boolean verificarUsuarioLogueado(HttpServletRequest request){
        try{
            Usuario usuarioLogeado = (Usuario) request.getSession().getAttribute("usuario");
            return usuarioLogeado != null;
        }catch(Exception ex){
            System.out.println("Excepcion al verificar el usuario logueado: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public void destroy() {
    }
    
}
