package com.condominiosweb.controller;

import com.condominiosweb.bl.MensajeriaBL;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MensajeController extends HttpServlet {
    private MensajeriaBL mensajeriaBL;

    public MensajeController(){
        super();
        mensajeriaBL = new MensajeriaBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("pages/mensajeria/mensaje.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/mensajeria/mensaje.jsp";
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        
        CWResultado res = mensajeriaBL.agregarMensaje(titulo, contenido);
        if(res.esCorrecto()){
            res.setMensaje("Mensaje agregado correctamente.");
        }
        
        request.setAttribute("resultado", res);
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
}
