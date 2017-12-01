package com.condominiosweb.bl;

import com.condominiosweb.dao.PagoDao;
import com.condominiosweb.model.Pago;
import com.condominiosweb.model.Periodo;
import com.condominiosweb.model.TipoPago;
import com.condominiosweb.model.Vivienda;
import com.condominiosweb.util.CWResultado;
import com.condominiosweb.util.CWString;
import java.util.Date;

public class PagoBL {
    private PagoDao pagoDao;
    
    public PagoBL(){
        pagoDao = new PagoDao();
    }
    
    public CWResultado agregarPago(String vivienda, String periodo, String tipopago,
            String importepago, String fechapago){
        CWResultado resultado;
        try{
            resultado = obtenerPagoValido(vivienda, periodo, tipopago, importepago, vivienda);
            if(resultado.esCorrecto()){
                Pago nuevoPago = (Pago) resultado.getEntidad();
                resultado = pagoDao.agregarPago(nuevoPago);
            }
        }catch(Exception ex){
            resultado = new CWResultado();
            resultado.setMensaje("Error al ingresar el pago.");
            resultado.setMensajeDetalle(ex.getMessage());
        }
        return resultado;
    }
    
    private CWResultado obtenerPagoValido(String strvivienda, String strperiodo, String strtipopago,
            String strimporte, String strfecha){
        CWResultado resultado = new CWResultado();
        int vivienda = CWString.toInt(strvivienda);
        int periodo = CWString.toInt(strperiodo);
        int tipoPago = CWString.toInt(strtipopago);
        double importe = CWString.toDouble(strimporte);
        Date fechaPago = CWString.toDate(strfecha);
        
        Pago pagoValido = new Pago();
        pagoValido.setVivienda(new Vivienda(vivienda));
        pagoValido.setPeriodo(new Periodo(periodo));
        pagoValido.setTipoPago(new TipoPago(tipoPago));
        pagoValido.setImportePago(importe);
        pagoValido.setFechaPago(fechaPago);
        
        resultado.setEntidad(pagoValido);
        
        return resultado;
    }
}
