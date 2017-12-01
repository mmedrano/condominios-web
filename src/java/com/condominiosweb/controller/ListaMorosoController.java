package com.condominiosweb.controller;

import com.condominiosweb.bl.CuotaBL;
import com.condominiosweb.bl.PeriodoBL;
import com.condominiosweb.model.Cuota;
import com.condominiosweb.model.Periodo;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaMorosoController extends HttpServlet {
    public CuotaBL cuotaBL;
    public PeriodoBL periodoBL;
    
    public ListaMorosoController(){
        super();
        cuotaBL = new CuotaBL();
        periodoBL = new PeriodoBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String periodo = request.getParameter("periodo");
        if(CWString.isNullOrEmpty(periodo)) periodo = "0";
        
        CWResultado resPeriodo = periodoBL.listarPeriodos();
        if(resPeriodo.esCorrecto()){
            ArrayList<Periodo> lstPeriodos = (ArrayList<Periodo>) resPeriodo.getListaEntidades();
            request.getSession().setAttribute("lstPeriodos", lstPeriodos);
        }
        
        CWResultado resultado = cuotaBL.listarMorosos(periodo);
        if(resultado.esCorrecto()){
            ArrayList<Cuota> lstMorosos = (ArrayList<Cuota>) resultado.getListaEntidades();
            request.getSession().setAttribute("lstMorosos", lstMorosos);
        }
        
        Periodo periodoSel = new Periodo(CWString.toInt(periodo));
        request.getSession().setAttribute("periodoSel", periodoSel);
        
        request.getRequestDispatcher("pages/cuotas/listamorosos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
