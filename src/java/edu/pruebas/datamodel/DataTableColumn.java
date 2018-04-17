/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.datamodel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author ismael
 */
public class DataTableColumn {
    
    private String label;
    private String fieldName;

    public DataTableColumn() {
    }
    
    public DataTableColumn(String label, String fieldName) {
        this.label = label;
        this.fieldName = fieldName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public Object valueField(Object obj) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
        String[] fields = fieldName.split("\\.");
        Object temp = obj;
        for (int i = 0; i < fields.length; i++) {
            String field = ((fields[i].endsWith(")"))?fields[i].replaceAll("\\(\\)", ""):"get"+fields[i].substring(0, 1).toUpperCase() + fields[i].substring(1));
            Method method = temp.getClass().getDeclaredMethod(field, null);
            temp = method.invoke(temp, null);
        }
        return temp;
    }
}
