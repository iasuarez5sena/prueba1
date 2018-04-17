/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.datamodel;

/**
 *
 * @author Ismael
 */
public class PageLink {
    
    private boolean action;
    private int page;
    private String text;
    private String classes;
    private String title;

    public PageLink() {
    }

    public PageLink(boolean action, int page, String text, String classes, String title) {
        this.action = action;
        this.page = page;
        this.text = text;
        this.classes = classes;
        this.title = title;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return text;
    }
    
    
}
