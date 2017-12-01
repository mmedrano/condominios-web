package com.condominiosweb.controller;

import com.condominiosweb.bl.CondominioBL;
import com.condominiosweb.bl.ResidenteBL;
import com.condominiosweb.bl.ViviendaBL;
import com.condominiosweb.model.Condominio;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViviendaController extends HttpServlet {
    private ViviendaBL viviendaBL;
    private CondominioBL condominioBL;
    private ResidenteBL residenteBL;
    
    public ViviendaController(){
        super();
        condominioBL = new CondominioBL();
        residenteBL = new ResidenteBL();
        viviendaBL = new ViviendaBL();
    }
    
    private void cargarDatosVivienda(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        String urlForward = "pages/viviendas/vivienda.jsp";
        String action = request.getParameter("action");
        if(CWString.isNullOrEmpty(action)) action = "";
        
        if(action.equalsIgnoreCase("edit")){
            CWResultado res = viviendaBL.obtenerVivienda(request.getParameter("idv"));
            if(res.esCorrecto()){
                Vivienda v = (Vivienda) res.getEntidad();
                request.getSession().setAttribute("vivienda", v);
            }else{
                request.setAttribute("resultado", res);
            }
        }else{
            request.getSession().removeAttribute("vivienda");
        }
        
        CWResultado resCondominio = condominioBL.listarCondominios("");
        if(resCondominio.esCorrecto()){
            ArrayList<Condominio> lstCondominios = (ArrayList<Condominio>) resCondominio.getListaEntidades();
            request.getSession().setAttribute("lstCondominios", lstCondominios);
        }
        CWResultado resResidente = residenteBL.listarResidentes("");
        if(resCondominio.esCorrecto()){
            ArrayList<Residente> lstResidentes = (ArrayList<Residente>) resResidente.getListaEntidades();
            request.getSession().setAttribute("lstResidentes", lstResidentes);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cargarDatosVivienda(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean esEdicion = false;
        String urlForward;
        String stridvivienda = request.getParameter("idvivienda");
        String residente = request.getParameter("residente");
        String condominio = request.getParameter("condominio");
        String torre = request.getParameter("torre");
        String numero = request.getParameter("numero");
        String metraje = request.getParameter("metraje");
        
        CWResultado res;
        if(CWString.isNullOrEmpty(stridvivienda)){
            res = viviendaBL.agregarVivienda(residente, condominio, torre, numero, metraje);
        }else{
            esEdicion = true;
            res = viviendaBL.actualizarVivienda(stridvivienda, residente, condominio, torre, numero, metraje);
        }
        
        if(res.esCorrecto()){
            res.setMensaje("Vivienda " + (esEdicion ? "modificada" : "registrada") + " satisfactoriamente.");
            request.getSession().removeAttribute("vivienda");
        }
        
        request.setAttribute("resultado", res);
        urlForward = "pages/viviendas/vivienda.jsp";
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
    
}
