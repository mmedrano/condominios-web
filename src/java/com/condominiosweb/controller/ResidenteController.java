package com.condominiosweb.controller;

import com.condominiosweb.bl.ResidenteBL;
import com.condominiosweb.bl.TipoDocumentoBL;
import com.condominiosweb.dao.ResidenteDao;
import com.condominiosweb.dao.TipoDocumentoDao;
import com.condominiosweb.model.Residente;
import com.condominiosweb.model.TipoDocumento;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import com.condominiosweb.util.CWUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResidenteController extends HttpServlet {
    private TipoDocumentoBL tipoDocBL;
    private ResidenteBL residenteBL;
    
    public ResidenteController(){
        super();
        tipoDocBL = new TipoDocumentoBL();
        residenteBL = new ResidenteBL();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward = "pages/residentes/residente.jsp";
        String action = request.getParameter("action");
        if(CWString.isNullOrEmpty(action)) action = "";
        
        if(action.equalsIgnoreCase("edit")){
            CWResultado res = residenteBL.obtenerResidente(request.getParameter("idr"));
            if(res.esCorrecto()){
                urlForward = "pages/residentes/residente.jsp";
                Residente residente = (Residente) res.getEntidad();
                residente.setCadFecNac(CWUtil.dateToString(residente.getFechaNacimiento(), "yyyy-MM-dd"));
                request.getSession().setAttribute("residente", residente);
            }else{
                request.setAttribute("resultado", res);
            }
        }else{
            request.getSession().removeAttribute("residente");
        }
        
        CWResultado resTipoDoc = tipoDocBL.listarTipoDocumento();
        if(resTipoDoc.esCorrecto()){
            ArrayList<TipoDocumento> lstTipoDocumento = (ArrayList<TipoDocumento>) resTipoDoc.getListaEntidades();
            request.getSession().setAttribute("lstTipoDocumento", lstTipoDocumento);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String urlForward;
        boolean esEdicion = false;
        String strresidente = request.getParameter("idresidente");
        String tipodocumento = request.getParameter("tipodocumento");
        String nrocumento = request.getParameter("nrodocumento");
        String appaterno = request.getParameter("appaterno");
        String apmaterno = request.getParameter("apmaterno");
        String nombres = request.getParameter("nombres");
        String fecnac = request.getParameter("fecnac");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        CWResultado res = new CWResultado();
        boolean continuarProceso = true;
        Residente residente = new Residente();
        int tipDoc = CWString.toInt(tipodocumento);
        if(tipDoc == -1){
            continuarProceso = false;
            res = new CWResultado();
            res.setMensaje("Especificar el documento de identidad");
        }
        
        if(continuarProceso){
            residente.setTipoDocumento(new TipoDocumento(tipDoc, ""));
            residente.setDocumento(nrocumento);
            residente.setNombres(nombres);
            residente.setApellidoMaterno(apmaterno);
            residente.setApellidoPaterno(appaterno);
            residente.setFechaNacimiento(CWString.toDate(fecnac));
            residente.setCorreo(correo);
            residente.setClave(clave);
            if(CWString.isNullOrEmpty(strresidente)){
                res = residenteBL.agregarResidente(residente);
                continuarProceso = res.esCorrecto();
            }else{
                residente.setIdResidente(CWString.toInt(strresidente));
                res = residenteBL.actualizarResidente(residente);
                continuarProceso = res.esCorrecto();
                esEdicion = true;
            }
        }
        
        if(continuarProceso){
            res.setMensaje("Residente " + (esEdicion ? "modificado" : "registrado") + " satisfactoriamente.");
            request.getSession().removeAttribute("residente");
        }
        
        request.setAttribute("resultado", res);
        urlForward = "pages/residentes/residente.jsp";
        RequestDispatcher view = request.getRequestDispatcher(urlForward);
        view.forward(request, response);
    }

}
