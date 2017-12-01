package com.condominiosweb.controller;

import com.condominiosweb.bl.ResidenteBL;
import com.condominiosweb.bl.TipoDocumentoBL;
import com.condominiosweb.bl.VisitaBL;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoDocumento;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisitaController extends HttpServlet {
    private TipoDocumentoBL tipoDocumentoBL;
    private ResidenteBL residenteBL;
    private VisitaBL visitaBL;
    
    public VisitaController(){
        super();
        tipoDocumentoBL = new TipoDocumentoBL();
        residenteBL = new ResidenteBL();
        visitaBL = new VisitaBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/visitas/visita.jsp";
        CWResultado resTipoDoc = tipoDocumentoBL.listarTipoDocumento();
        if(resTipoDoc.esCorrecto()){
            ArrayList<TipoDocumento> lstTipoDoc = (ArrayList<TipoDocumento>) resTipoDoc.getListaEntidades();
            request.getSession().setAttribute("lstTipoDoc", lstTipoDoc);
        }
        
        CWResultado resResidente = residenteBL.listarResidentes("");
        if(resResidente.esCorrecto()){
            ArrayList<Residente> lstResidentes = (ArrayList<Residente>) resResidente.getListaEntidades();
            request.getSession().setAttribute("lstResidentes", lstResidentes);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(urlForward);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/visitas/visita.jsp";
        String residente = request.getParameter("residente");
        String tipodoc = request.getParameter("tipodoc");
        String nombre = request.getParameter("nombre");
        String documento = request.getParameter("documento");
        String fecha = request.getParameter("fecha");
        
        CWResultado res = visitaBL.agregarVisita(tipodoc, documento, nombre, residente, fecha);
        if(res.esCorrecto()){
            res.setMensaje("La visita fue registrada correctamente.");
        }
        
        request.setAttribute("resultado", res);
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
}
