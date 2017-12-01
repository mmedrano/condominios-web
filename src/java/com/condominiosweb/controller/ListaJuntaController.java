package com.condominiosweb.controller;

import com.condominiosweb.dao.JuntaDao;
import com.condominiosweb.model.Junta;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaJuntaController extends HttpServlet {
    private JuntaDao juntaDao;
    
    public ListaJuntaController(){
        super();
        juntaDao = new JuntaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CWResultado resJuntas = juntaDao.listarJuntas();
        if(resJuntas.esCorrecto()){
            ArrayList<Junta> lstJuntas = (ArrayList<Junta>) resJuntas.getListaEntidades();
            request.getSession().setAttribute("lstJuntas", lstJuntas);
        }
        
        RequestDispatcher view = request.getRequestDispatcher("pages/juntas/listajuntas.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
