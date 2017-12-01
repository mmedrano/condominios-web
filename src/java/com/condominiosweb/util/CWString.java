package com.condominiosweb.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CWString {
    static final String[] patronesFecha = {
        "yyyy-MM-dd'T'HH:mm",
        "yyyy-MM-dd",
        "dd-mm-yyyy",
        "yyyy.MM.dd G 'at' HH:mm:ss z",
        "EEE, MMM d, ''yy",
        "yyyyy.MMMMM.dd GGG hh:mm aaa",
        "EEE, d MMM yyyy HH:mm:ss Z",
        "yyMMddHHmmssZ",
        "d MMM yyyy HH:mm:ss z",
        "yyyy-MM-dd'T'HH:mm:ss",
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd'T'HH:mm:ssZ",
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
        "yyyy-MM-dd'T'HH:mm:ssz",
        "yyyy-MM-dd'T'HH:mm:ss.SSSz",
        "EEE, d MMM yy HH:mm:ssz",
        "EEE, d MMM yy HH:mm:ss",
        "EEE, d MMM yy HH:mm z",
        "EEE, d MMM yy HH:mm Z",
        "EEE, d MMM yyyy HH:mm:ss z",
        "EEE, d MMM yyyy HH:mm:ss Z",
        "EEE, d MMM yyyy HH:mm:ss ZZZZ",
        "EEE, d MMM yyyy HH:mm z",
        "EEE, d MMM yyyy HH:mm Z",
        "d MMM yy HH:mm z",
        "d MMM yy HH:mm:ss z",
        "d MMM yyyy HH:mm z",
        "d MMM yyyy HH:mm:ss z"
    };
    
    public static boolean isNullOrEmpty(String str){
        if(str == null) return true;
        else return str.length() == 0;
    }
    
    public static int toInt(String str){
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            return Integer.MIN_VALUE;
        }
    }
    
    public static double toDouble(String str){
        try{
            return Double.parseDouble(str);
        }catch(NumberFormatException ex){
            return Double.NaN;
        }
    }
    
    public static Date toDate(String str){
        SimpleDateFormat formatter;
        Date fecha = new Date(0);
        for(String patron : patronesFecha){
            try{
                formatter = new SimpleDateFormat(patron, Locale.US);
                fecha = formatter.parse(str);
                break;
            }catch(Exception ex){ }
        }
        return fecha;
    }
}
