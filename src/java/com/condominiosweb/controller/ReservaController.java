package com.condominiosweb.controller;

import com.condominiosweb.bl.AreaComunBL;
import com.condominiosweb.bl.ReservaBL;
import com.condominiosweb.model.AreaComun;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Usuario;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservaController extends HttpServlet {
    private AreaComunBL areaComunBL;
    private ReservaBL reservaBL;
    
    public ReservaController(){
        super();
        areaComunBL = new AreaComunBL();
        reservaBL = new ReservaBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/reservas/reservaareacomun.jsp";
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
        Residente residenteLogueado = usuarioLogueado.getResidente();
        
        CWResultado resArea = areaComunBL.listarAreasComunes(String.valueOf(residenteLogueado.getVivienda().getCondominio().getIdCondominio()));
        if(resArea.esCorrecto()){
            ArrayList<AreaComun> lstAreasComunes = (ArrayList<AreaComun>) resArea.getListaEntidades();
            request.getSession().setAttribute("lstAreasComunes", lstAreasComunes);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(urlForward);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward;
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
        Residente residenteLogueado = usuarioLogueado.getResidente();
        
        String areacomun = request.getParameter("areacomun");
        String residente = String.valueOf(residenteLogueado.getIdResidente());
        String fechainicio = request.getParameter("fechainicio");
        String fechafin = request.getParameter("fechafin");
        
        CWResultado res;
        res = reservaBL.agregarReserva(areacomun, residente, fechainicio, fechafin);
        
        if(res.esCorrecto()){
            res.setMensaje("Reserva registrada correctamente.");
        }
        
        urlForward = "pages/reservas/reservaareacomun.jsp";
        request.setAttribute("resultado", res);
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
}
