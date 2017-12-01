package com.condominiosweb.controller;

import com.condominiosweb.bl.QuejaBL;
import com.condominiosweb.model.Queja;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaQuejasController extends HttpServlet {
    private QuejaBL quejaBL;
    
    public ListaQuejasController(){
        quejaBL = new QuejaBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String idqueja = request.getParameter("idq");
        if(CWString.isNullOrEmpty(action)) action = "";
        
        if(action.equalsIgnoreCase("atd")){
            quejaBL.atenderQueja(idqueja);
        }else if(action.equalsIgnoreCase("rec")){
            quejaBL.rechazarQueja(idqueja);
        }
        
        CWResultado res = quejaBL.listarQuejas();
        if(res.esCorrecto()){
            ArrayList<Queja> lstQuejas = (ArrayList<Queja>) res.getListaEntidades();
            request.setAttribute("lstQuejas", lstQuejas);
        }
        
        request.getRequestDispatcher("pages/quejas/listaquejas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
