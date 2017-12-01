package com.condominiosweb.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CWUtil {
    public static String dateToString(Date fecha, String format){
        DateFormat df = new SimpleDateFormat(format);
        return df.format(fecha);
    }
}
