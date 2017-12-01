package com.condominiosweb.controller;

import com.condominiosweb.bl.CuotaBL;
import com.condominiosweb.bl.PeriodoBL;
import com.condominiosweb.bl.ViviendaBL;
import com.condominiosweb.model.Periodo;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CuotaController extends HttpServlet {
    private PeriodoBL periodoBL;
    private ViviendaBL viviendaBL;
    private CuotaBL cuotaBL;

    public CuotaController(){
        super();
        periodoBL = new PeriodoBL();
        viviendaBL = new ViviendaBL();
        cuotaBL = new CuotaBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/cuotas/cuota.jsp";
        
        CWResultado resPeriodo = periodoBL.listarPeriodos();
        if(resPeriodo.esCorrecto()){
            ArrayList<Periodo> lstPeriodos = (ArrayList<Periodo>) resPeriodo.getListaEntidades();
            request.getSession().setAttribute("lstPeriodos", lstPeriodos);
        }
        
        CWResultado resVivienda = viviendaBL.listarViviendas("", "");
        if(resVivienda.esCorrecto()){
            ArrayList<Vivienda> lstViviendas = (ArrayList<Vivienda>) resVivienda.getListaEntidades();
            request.getSession().setAttribute("lstViviendas", lstViviendas);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward;
        String vivienda = request.getParameter("vivienda");
        String periodo = request.getParameter("periodo");
        String importe = request.getParameter("importe");
        String fecvenc = request.getParameter("fecvenc");
        
        CWResultado res = cuotaBL.agregarCuota(vivienda, periodo, importe, fecvenc);
        if(res.esCorrecto()){
            res.setMensaje("Cuota registrada satisfactoriamente.");            
        }
        
        request.setAttribute("resultado", res);
        urlForward = "pages/cuotas/cuota.jsp";
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
}
