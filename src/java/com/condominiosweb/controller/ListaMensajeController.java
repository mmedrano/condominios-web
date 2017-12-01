package com.condominiosweb.controller;

import com.condominiosweb.bl.MensajeriaBL;
import com.condominiosweb.model.Mensajeria;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaMensajeController extends HttpServlet {
    private MensajeriaBL mensajeriaBL;
    
    public ListaMensajeController(){
        super();
        mensajeriaBL = new MensajeriaBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CWResultado res = mensajeriaBL.listarMensajes();
        if(res.esCorrecto()){
            ArrayList<Mensajeria> lstMensajes = (ArrayList<Mensajeria>) res.getListaEntidades();
            request.setAttribute("lstMensajes", lstMensajes);
        }
        
        RequestDispatcher view = request.getRequestDispatcher("pages/mensajeria/listamensaje.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
