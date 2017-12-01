package com.condominiosweb.controller;

import com.condominiosweb.bl.JuntaBL;
import com.condominiosweb.bl.ResidenteBL;
import com.condominiosweb.model.Junta;
import com.condominiosweb.model.JuntaResidente;
import com.condominiosweb.model.JuntaTema;
import com.condominiosweb.model.Residente;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JuntaController extends HttpServlet {    
    private ResidenteBL residenteBL;
    private JuntaBL juntaBL;
    
    public JuntaController(){
        super();
        residenteBL = new ResidenteBL();
        juntaBL = new JuntaBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String junta = request.getParameter("idj");
        if(CWString.isNullOrEmpty(junta)){
            CWResultado resResidente = residenteBL.listarResidentes("");
            if(resResidente.esCorrecto()){
                ArrayList<Residente> lstResidentes = (ArrayList<Residente>) resResidente.getListaEntidades();
                request.getSession().setAttribute("lstResidentes", lstResidentes);
            }
            request.getRequestDispatcher("pages/juntas/junta.jsp").forward(request, response);
        }else{
            CWResultado resResidente = juntaBL.listarResidentesJunta(junta);
            if(resResidente.esCorrecto()){
                ArrayList<JuntaResidente> lstJResidentes = (ArrayList<JuntaResidente>) resResidente.getListaEntidades();
                request.getSession().setAttribute("lstJResidentes", lstJResidentes);
            }
            
            CWResultado resTema = juntaBL.listarTemasJunta(junta);
            if(resTema.esCorrecto()){
                ArrayList<JuntaTema> lstJTemas = (ArrayList<JuntaTema>) resTema.getListaEntidades();
                request.getSession().setAttribute("lstJTemas", lstJTemas);
            }
            
            CWResultado resJunta = juntaBL.obtenerJunta(junta);
            if(resJunta.esCorrecto()){
                Junta juntaSel = (Junta) resJunta.getEntidad();
                request.getSession().setAttribute("juntaSel", juntaSel);
            }
            
            request.getRequestDispatcher("pages/juntas/juntadetalle.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/juntas/junta.jsp";
        String[] residentes = request.getParameterValues("residente");
        String[] temas = request.getParameterValues("tema");
        String[] acuerdos = request.getParameterValues("acuerdo");
        String fechaInicio = request.getParameter("fechainicio");
        String fechaFin = request.getParameter("fechafin");
        String junta = request.getParameter("junta");
        
        CWResultado res = juntaBL.agregarJunta(junta, fechaInicio, fechaFin, residentes, temas, acuerdos);
        if(res.esCorrecto()){
            res.setMensaje("Se agrego la junta satisfactoriamente.");
        }
        
        request.setAttribute("resultado", res);
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }
}
