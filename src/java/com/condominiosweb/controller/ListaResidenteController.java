package com.condominiosweb.controller;

import com.condominiosweb.bl.ResidenteBL;
import com.condominiosweb.model.Residente;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaResidenteController extends HttpServlet {
    ResidenteBL residenteBL;
    
    public ListaResidenteController(){
        super();
        residenteBL = new ResidenteBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        if(CWString.isNullOrEmpty(nombre)) nombre = "";
        CWResultado res = residenteBL.listarResidentes(nombre);
        if(res.esCorrecto()){
            ArrayList<Residente> lstResidentes = (ArrayList<Residente>) res.getListaEntidades();
            request.getSession().setAttribute("lstResidentes", lstResidentes);
        }
        request.getRequestDispatcher("pages/residentes/listaresidentes.jsp").forward(request, response);
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
