package com.condominiosweb.controller;

import com.condominiosweb.bl.QuejaBL;
import com.condominiosweb.bl.TipoQuejaBL;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoQueja;
import com.condominiosweb.model.Usuario;
import com.condominiosweb.util.CWResultado;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuejaController extends HttpServlet {
    private QuejaBL quejaBL;
    private TipoQuejaBL tipoQuejaBL;
    
    public QuejaController(){
        super();
        quejaBL = new QuejaBL();
        tipoQuejaBL = new TipoQuejaBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/quejas/queja.jsp";
        CWResultado resTipoQueja = tipoQuejaBL.listarTipoQueja();
        if(resTipoQueja.esCorrecto()){
            ArrayList<TipoQueja> lstTipoQueja = (ArrayList<TipoQueja>) resTipoQueja.getListaEntidades();
            request.getSession().setAttribute("lstTipoQueja", lstTipoQueja);
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
        
        String tipoqueja = request.getParameter("tipoqueja");
        String motivo = request.getParameter("motivo");
        String residente = String.valueOf(residenteLogueado.getIdResidente());
        
        CWResultado res;
        res = quejaBL.registrarQueja(residente, tipoqueja, motivo);
        
        if(res.esCorrecto()){
            res.setMensaje("Queja enviada satisfactoriamente.");
        }
        urlForward = "pages/quejas/queja.jsp";
        request.setAttribute("resultado", res);
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
}
