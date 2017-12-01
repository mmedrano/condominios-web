package com.condominiosweb.controller;

import com.condominiosweb.bl.ResidenteBL;
import com.condominiosweb.bl.VisitaBL;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Visita;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaVisitaController extends HttpServlet {    
    private VisitaBL visitaBL;
    private ResidenteBL residenteBL;
    
    public ListaVisitaController(){
        super();
        visitaBL = new VisitaBL();
        residenteBL = new ResidenteBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/visitas/listavisitas.jsp";
        String residente = request.getParameter("residente");
        if(CWString.isNullOrEmpty(residente)) residente = "0";
        
        CWResultado resVisitas = visitaBL.listarVisitas(residente);
        if(resVisitas.esCorrecto()){
            ArrayList<Visita> lstVisitas = (ArrayList<Visita>) resVisitas.getListaEntidades();
            request.setAttribute("lstVisitas", lstVisitas);
        }
        
        CWResultado resResidente = residenteBL.listarResidentes("");
        if(resResidente.esCorrecto()){
            ArrayList<Residente> lstResidentes = (ArrayList<Residente>) resResidente.getListaEntidades();
            request.getSession().setAttribute("lstResidentes", lstResidentes);
        }
        
        Residente residenteSel = new Residente(CWString.toInt(residente));
        request.getSession().setAttribute("residenteSel", residenteSel);
        
        request.getRequestDispatcher(urlForward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
