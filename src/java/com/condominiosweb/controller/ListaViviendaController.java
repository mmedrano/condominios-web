package com.condominiosweb.controller;

import com.condominiosweb.bl.ViviendaBL;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaViviendaController extends HttpServlet {
    ViviendaBL viviendaBL;
    
    public ListaViviendaController(){
        super();
        viviendaBL = new ViviendaBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        if(CWString.isNullOrEmpty(f1)) f1 = "";
        if(CWString.isNullOrEmpty(f2)) f2 = "";
        
        CWResultado res = viviendaBL.listarViviendas(f1, f2);
        if(res.esCorrecto()){
            ArrayList<Vivienda> lstViviendas = (ArrayList<Vivienda>) res.getListaEntidades();
            request.getSession().setAttribute("lstViviendas", lstViviendas);
        }
        request.getRequestDispatcher("pages/viviendas/listaviviendas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
