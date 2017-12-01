package com.condominiosweb.controller;

import com.condominiosweb.bl.QuejaBL;
import com.condominiosweb.model.Queja;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Usuario;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaQuejaResidenteController extends HttpServlet {
    private QuejaBL quejaBL;
    
    public ListaQuejaResidenteController(){
        quejaBL = new QuejaBL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
        Residente residenteLogueado = usuarioLogueado.getResidente();
        String residente = String.valueOf(residenteLogueado.getIdResidente());
        
        CWResultado res = quejaBL.listarQuejasResidente(residente);
        if(res.esCorrecto()){
            ArrayList<Queja> lstQuejas = (ArrayList<Queja>) res.getListaEntidades();
            request.setAttribute("lstQuejas", lstQuejas);
        }
        
        request.getRequestDispatcher("pages/quejas/listaquejasresidente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
