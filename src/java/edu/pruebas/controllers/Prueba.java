/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.controllers;

import edu.pruebas.datamodel.DataTableColumn;
import edu.pruebas.datamodel.DataTableLazy;
import edu.pruebas.model.Carro;
import edu.pruebas.util.DatosService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ismael
 */
@Named(value = "prueba")
@ViewScoped
public class Prueba implements Serializable {

    private DataTableLazy<Carro> datos;

    /**
     * Creates a new instance of Prueba
     */
    public Prueba() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Se reinicia.....");
        List<DataTableColumn> columns = new ArrayList<>();
        columns.add(new DataTableColumn("Id", "id"));
        columns.add(new DataTableColumn("Modelo", "modelo"));
        columns.add(new DataTableColumn("Año", "fecha.year"));
        columns.add(new DataTableColumn("Fecha", "fecha"));
        datos = new DataTableLazy<Carro>(15, columns) {
            @Override
            public int getTotalItems() {
                return getElemntsToExport().size();
            }

            @Override
            public DataModel getModel() {
                if (model == null) {
                    model = new ListDataModel<>(DatosService.getCarros(getPaginator().getPageFirstItem() - 1, getPageSize()));
                }
                return model;
            }

            @Override
            public List<Carro> getElemntsToExport() {
                return DatosService.getCarros();
            }
        };
    }

    public DataTableLazy getDatos() {
        return datos;
    }

    public void preparedView() {
        System.out.println("Dato:" + datos.getSelected().getId());
    }

    public void pruebaBoton(String param) {
        System.out.println("############################################");
        System.out.println("Dato param: " + param);
    }
    
    public void pruebaDelete(){
        if(datos.getSelected() != null){
            System.out.println("Vamo a eliminar... " + datos.getSelected().getId());
        } else{
            System.out.println("No se ha seleecionado ningún dato.");
        }
    }
}
