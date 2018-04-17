/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.datamodel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ismael
 */
public class DateFormatter {
    private static SimpleDateFormat sdfDate;
    private static SimpleDateFormat sdfDateTime;

    public static SimpleDateFormat getSdfDate() {
        if(sdfDate == null){
            sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        }
        return sdfDate;
    }

    public static void setSdfDate(SimpleDateFormat sdfDate) {
        DateFormatter.sdfDate = sdfDate;
    }
    
    public static SimpleDateFormat getSdfDateTime() {
        if(sdfDateTime == null){
            sdfDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        }
        return sdfDateTime;
    }

    public static void setSdfDateTime(SimpleDateFormat sdfDateTime) {
        DateFormatter.sdfDateTime = sdfDateTime;
    }
    
    public static String parseDateToString(Date date){
        return getSdfDate().format(date);
    }
    
    public static String parseDateTimeToString(Date date){
        return getSdfDateTime().format(date);
    }
    
    public static Date parseDateToString(String date) throws ParseException{
        return getSdfDate().parse(date);
    }
    
    public static Date parseDateTimeToString(String date) throws ParseException{
        return getSdfDateTime().parse(date);
    }
    
}
