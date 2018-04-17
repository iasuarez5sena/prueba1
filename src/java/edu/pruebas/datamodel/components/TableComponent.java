/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.datamodel.components;

import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIOutput;

/**
 *
 * @author ismael
 */
@FacesComponent("test.TableComponent")
public class TableComponent extends UIOutput{
   public final static String COMPONENT_TYPE = "test.TableComponent";

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }
}
