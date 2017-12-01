package com.condominiosweb.controller;

import com.condominiosweb.bl.CondominioBL;
import com.condominiosweb.model.Condominio;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CondominioController extends HttpServlet {
    private CondominioBL condominioBL;
    
    public CondominioController(){
        super();
        condominioBL = new CondominioBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/condominios/condominio.jsp";
        String action = request.getParameter("action");
        if(CWString.isNullOrEmpty(action)) action = "";
        
        if(action.equalsIgnoreCase("edit")){
            CWResultado res = condominioBL.obtenerCondominio(request.getParameter("idc"));
            if(res.esCorrecto()){
                urlForward = "pages/condominios/condominio.jsp";
                Condominio condominio = (Condominio) res.getEntidad();
                request.getSession().setAttribute("condominio", condominio);
            }else{
                request.setAttribute("resultado", res);
            }
        }else{
            request.getSession().removeAttribute("condominio");
        }
        
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward;
        boolean esEdicion = false;
        String strcondominio = request.getParameter("idcondominio");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Condominio condominio = new Condominio(nombre, descripcion);
        
        CWResultado res;
        
        if(CWString.isNullOrEmpty(strcondominio)){
            res = condominioBL.agregarCondominio(condominio);
        }else{
            int idcondominio = Integer.parseInt(strcondominio);
            condominio.setIdCondominio(idcondominio);
            res = condominioBL.actualizarCondominio(condominio);
            esEdicion = true;
        }
        
        if(res.esCorrecto()){
            res.setMensaje("Condominio " + (esEdicion ? "modificado" : "registrado") + " satisfactoriamente.");
            request.getSession().removeAttribute("condominio");
            //urlForward = "Condominio?action=add";
            //urlForward = "pages/condominios/condominio.jsp";
            
        }else{
            //urlForward = "Condominio?action=edit&idc=" + strcondominio;
            
            
        }
        
        urlForward = "pages/condominios/condominio.jsp";
            request.setAttribute("resultado", res);
            RequestDispatcher view = request.getRequestDispatcher(urlForward);
            view.forward(request, response);
        
    }
}
