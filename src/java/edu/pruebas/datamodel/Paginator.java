/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ismael
 */
public class Paginator {

    public static enum RENDER_POSITION {
        TOP, BOTTOM, TOM_AND_BOTTOM
    };

    private final RENDER_POSITION renderPosition;
    private String info;
    private List<PageLink> pages;
    private int totalPages;
    private int totalItems;
    private int page;
    private int pageSize;

    public Paginator() {
        renderPosition = RENDER_POSITION.BOTTOM;
    }

    public Paginator(int totalItems, int pageSize, int page) {
        this.renderPosition = RENDER_POSITION.BOTTOM;
        this.totalItems = totalItems;
        this.pageSize = pageSize;
        this.page = page;
    }

    public int getPageFirstItem() {
        return (page - 1) * pageSize + 1;
    }

    public int getPageLastItem() {
        int lastItem = page * pageSize;
        return (lastItem >= totalItems ? totalItems : lastItem);
    }

    public RENDER_POSITION getRenderPosition() {
        return renderPosition;
    }

    public void setPage(int page) {
        this.page = page;
        pages = null;
    }

    public String getInfo() {
        return getPageFirstItem() + "..." + getPageLastItem() + " / " + totalItems;
    }

    public List<PageLink> getPages() {
        if (pages == null || pages.isEmpty()) {
            pages = new ArrayList<>();
            pages.add(getPreviousPageLink(page - 1));
            int prevPage = 0;
            for (int i = 1; i <= getTotalPages(); i++) {
                if (i <= 3 || i >= getTotalPages() - 2 || (i >= page-1 && i <= page + 1)) {
                    if (i - prevPage > 1) {
                        pages.add(getPointsSpace());
                    }
                    pages.add(getPageLink(i));
                    prevPage = i;
                }
            }
            pages.add(getNexPageLink(page + 1));
        }
        return pages;
    }

    private PageLink getPageLink(int page) {
        boolean isPage = (page == this.page);
        return new PageLink(!isPage, page, "" + page, (isPage ? "active" : ""), "paginatorGoPage");
    }

    private PageLink getPointsSpace() {
        return new PageLink(false, -1, "...", "", "");
    }

    private PageLink getPreviousPageLink(int goPage) {
        boolean previous = isHasPreviousPage(goPage);
        return new PageLink(previous, (previous ? goPage : 1), "&laquo;", (previous ? "" : "disabled"), "paginatorPrevious");
    }

    private PageLink getNexPageLink(int goPage) {
        boolean next = isHasNextPage(goPage);
        return new PageLink(next, (next ? goPage : totalPages), "&raquo;", (next ? "" : "disabled"), "paginatorNext");
    }

    public int getTotalPages() {
        if (totalPages == 0) {
            totalPages = ((int) Math.ceil((double) totalItems / pageSize));
        }
        return totalPages;
    }

    public boolean isHasNextPage(int goPage) {
        return goPage <= getTotalPages();
    }

    public boolean isHasPreviousPage(int goPage) {
        return (goPage >= 1);
    }

}
