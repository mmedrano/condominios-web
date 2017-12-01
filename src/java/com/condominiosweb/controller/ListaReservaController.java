package com.condominiosweb.controller;

import com.condominiosweb.bl.ReservaBL;
import com.condominiosweb.model.Reserva;
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

public class ListaReservaController extends HttpServlet {
    private ReservaBL reservaBL;
    
    public ListaReservaController(){
        super();
        reservaBL = new ReservaBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/reservas/listareservas.jsp";
        String idresidente;
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
        if(usuarioLogueado.esResidente()){
            Residente residenteLogueado = usuarioLogueado.getResidente();
            idresidente = String.valueOf(residenteLogueado.getIdResidente());
        }else{
            idresidente = "0";
        }
        
        CWResultado res = reservaBL.listarReservas(idresidente);
        if(res.esCorrecto()){
            ArrayList<Reserva> lstReservas = (ArrayList<Reserva>) res.getListaEntidades();
            request.getSession().setAttribute("lstReservas", lstReservas);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(urlForward);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
