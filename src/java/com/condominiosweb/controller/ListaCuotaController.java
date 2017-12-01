package com.condominiosweb.controller;

import com.condominiosweb.bl.CuotaBL;
import com.condominiosweb.bl.ViviendaBL;
import com.condominiosweb.model.Cuota;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.Usuario;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListaCuotaController extends HttpServlet {
    CuotaBL cuotaBL;
    ViviendaBL viviendaBL;
    
    public ListaCuotaController(){
        super();
        cuotaBL = new CuotaBL();
        viviendaBL = new ViviendaBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vivienda = "0";
        Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
        
        CWResultado resVivienda = viviendaBL.listarViviendas("", "");
        if(resVivienda.esCorrecto()){
            ArrayList<Vivienda> lstViviendas = (ArrayList<Vivienda>) resVivienda.getListaEntidades();
            request.setAttribute("lstViviendas", lstViviendas);
        }
        
        CWResultado res;
        if(usuarioLogueado.esResidente()){
            Residente residenteLogueado = usuarioLogueado.getResidente();
            String residente = String.valueOf(residenteLogueado.getIdResidente());
            res = cuotaBL.listarCuotasPorResidente(residente);
        }else{
            vivienda = request.getParameter("vivienda");
            if(CWString.isNullOrEmpty(vivienda)) vivienda = "0";
            res = cuotaBL.listarCuotas(vivienda);
        }
        
        if(res.esCorrecto()){
            ArrayList<Cuota> lstCuotas = (ArrayList<Cuota>) res.getListaEntidades();
            request.getSession().setAttribute("lstCuotas", lstCuotas);
        }
        
        if(!usuarioLogueado.esResidente()){
            Vivienda viviendaSel = new Vivienda(CWString.toInt(vivienda));
            request.getSession().setAttribute("viviendaSel", viviendaSel);
        }
        
        request.getRequestDispatcher("pages/cuotas/listacuotas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}