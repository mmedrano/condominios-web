package com.condominiosweb.controller;

import com.condominiosweb.bl.CuotaBL;
import com.condominiosweb.bl.PagoBL;
import com.condominiosweb.bl.TipoPagoBL;
import com.condominiosweb.model.Cuota;
import com.condominiosweb.model.TipoPago;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagoController extends HttpServlet {
    PagoBL pagoBL;
    CuotaBL cuotaBL;
    TipoPagoBL tipoPagoBL;
    
    public PagoController(){
        super();
        pagoBL = new PagoBL();
        cuotaBL = new CuotaBL();
        tipoPagoBL = new TipoPagoBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/cuotas/registrarpago.jsp";
        String vivienda = request.getParameter("idv");
        String periodo = request.getParameter("idp");
        
        CWResultado res = cuotaBL.obtenerCuota(vivienda, periodo);
        if(res.esCorrecto()){
            Cuota cuota = (Cuota) res.getEntidad();
            request.setAttribute("cuota", cuota);
        }else{
            request.setAttribute("resultado", res);
        }
        
        CWResultado resTipoPago = tipoPagoBL.listarTipoPago();
        if(resTipoPago.esCorrecto()){
            ArrayList<TipoPago> lstTipoPago = (ArrayList<TipoPago>) resTipoPago.getListaEntidades();
            request.setAttribute("lstTipoPago", lstTipoPago);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward;
        String vivienda = request.getParameter("idv");
        String periodo = request.getParameter("idp");
        String tipopago = request.getParameter("tipopago");
        String importe = request.getParameter("importe");
        String fecha = request.getParameter("fecpago");
        
        CWResultado res;
        res = pagoBL.agregarPago(vivienda, periodo, tipopago, importe, fecha);
        
        if(res.esCorrecto()){
            urlForward = "ListaCuota?vivienda=" + vivienda;
            response.sendRedirect(urlForward);
        }else{
            urlForward = "pages/cuotas/registrarpago.jsp";
            request.setAttribute("resultado", res);
            RequestDispatcher view = request.getRequestDispatcher(urlForward);
            view.forward(request, response);
        }
    }
}
