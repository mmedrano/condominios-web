package com.condominiosweb.controller;

import com.condominiosweb.bl.CondominioBL;
import com.condominiosweb.dao.CondominioDao;
import com.condominiosweb.model.Condominio;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaCondominioController extends HttpServlet {
    private CondominioBL condominioBL;
    
    public ListaCondominioController(){
        super();
        condominioBL = new CondominioBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        if(CWString.isNullOrEmpty(nombre)) nombre = "";
        CWResultado res = condominioBL.listarCondominios(nombre);
        if(res.esCorrecto()){
            ArrayList<Condominio> lstCondominios = (ArrayList<Condominio>) res.getListaEntidades();
            request.getSession().setAttribute("lstCondominios", lstCondominios);
        }
        request.getRequestDispatcher("pages/condominios/listacondominios.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
