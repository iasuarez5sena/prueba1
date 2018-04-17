/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.datamodel.util;

import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author Ismael
 */
public class ClassUtil {

    public static Object getValueFormField(Object obj, String fieldName) throws ReflectiveOperationException {
        String methodGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return getValueMethod(obj, methodGet, null);
    }

    public static Object getValueFieldFormField(Object obj, String fieldName) throws ReflectiveOperationException {
        String methodGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return getValueMethod(obj, methodGet, null);
    }

    public static Object getValueMethodOrField(Object obj, String nameMethodOrField, List<Object> arguments, Class<?>... parameterTypes) throws ReflectiveOperationException {
        String[] fields = nameMethodOrField.split("\\.");
        Object temp = obj;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].endsWith(")")) {
                temp = getValueMethod(temp, fields[i], (i == fields.length - 1 ? arguments : null), (i == fields.length - 1 ? parameterTypes : null));
            } else {
                temp = getValueFormField(temp, fields[i]);
            }
        }
        return temp;
    }

    public static Object getValueMethod(Object obj, String methodName, List<Object> arguments, Class<?>... parameterTypes) throws ReflectiveOperationException {
        Method method = obj.getClass().getDeclaredMethod((methodName.endsWith(")"))?methodName.replaceAll("\\(\\)", ""):methodName, parameterTypes);
        System.out.println("Method name: " + methodName);
        System.out.println("\tArgumentos: " + arguments);
        System.out.println("\tTypes: " + parameterTypes);
        System.out.println("\tRet Types: " + method.getReturnType().getName());
        
        return method.invoke(obj, arguments);
    }
}
