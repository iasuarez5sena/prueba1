/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.datamodel;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import edu.pruebas.datamodel.util.ClassUtil;
import edu.pruebas.datamodel.util.DateFormatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ismael
 */
public abstract class DataTableLazy<E> {

    private int page = 1;
    private int pageSize = 10;
    private Paginator paginator;
    private String sortBy;
    private Boolean asc = true;
    protected DataModel<E> model;
    protected List<DataTableColumn> columns;

    public DataTableLazy() {
    }

    public DataTableLazy(int pageSize, List<DataTableColumn> columns) {
        this.pageSize = pageSize;
        this.columns = columns;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int newPage) {
        if (page > 0 && getPaginator().isHasNextPage(newPage)) {
            System.out.println("n&&&&&&&&&&& - " + newPage);
            this.page = newPage;
            resetModel();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        page = 1;
        resetModel();
    }

    public E getSelected() {
        if(getModel().isRowAvailable()){
            return getModel().getRowData();
        }
        return null;
    }
    
    public void setSelectedIndex(int index) {
        System.out.println("set......");
        getModel().setRowIndex(index);
    }

    public List<DataTableColumn> getColumns() {
        return columns;
    }

    public abstract int getTotalItems();

    public abstract DataModel<E> getModel();

    public abstract List<E> getElemntsToExport();

    public void resetModel() {
        model = null;
        paginator = null;
    }

    public void nextPage() {
        System.out.println("dsndjnasndkasdl");
        try {
            Thread.sleep(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(DataTableLazy.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (getPaginator().isHasPreviousPage(page + 1)) {
            page--;
            resetModel();
        }
    }

    public void previousPage() {
        if (getPaginator().isHasPreviousPage(page - 1)) {
            page--;
            resetModel();
        }
    }

    public Paginator getPaginator() {
        if (paginator == null) {
            paginator = new Paginator(getTotalItems(), pageSize, page);
        }
        return paginator;
    }

    public void exportPDF() throws FileNotFoundException, IOException, ReflectiveOperationException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        List<E> datos = getElemntsToExport();
        String fileName = ctx.getExternalContext().getRealPath(File.separator) + File.separator + "pruebas.pdf";
        File file = new File(fileName);
        //file.getParentFile().mkdir();
        createPdf(fileName, datos);
        resp(file);
        if (file.delete()) {
            System.out.println("Deleted file.");
        }
    }

    private void resp(File f) throws IOException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(f);
        byte[] bytes = new byte[1000];
        int read = 0;

        if (!ctx.getResponseComplete()) {
            String fileName = f.getName();
            //String contentType = "application/vnd.ms-excel";
            String contentType = "application/pdf";
            HttpServletResponse response
                    = (HttpServletResponse) ctx.getExternalContext().getResponse();

            response.setContentType(contentType);

            response.setHeader("Content-Disposition",
                    "attachment;filename=\"" + fileName + "\"");

            ServletOutputStream out = response.getOutputStream();

            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            out.flush();
            out.close();
            fis.close();
            System.out.println("\nDescargado\n");
            ctx.responseComplete();
        }
    }

    public void createPdf(String dest, List<E> datos) throws IOException, ReflectiveOperationException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.LETTER);

        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        // Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(font));
        /*
        // Create a List
        com.itextpdf.layout.element.List list = new com.itextpdf.layout.element.List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
        // Add ListItem objects
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));
        // Add the list
        document.add(list);
         */
 /*
        Table table = new Table(columns.size());
        for (DataTableColumn column : columns) {
            Cell cell = new Cell();
            cell.setBackgroundColor(Color.BLACK, 0.4F);
            cell.setTextAlignment(TextAlignment.CENTER);
            cell.add(column.getLabel());
            table.addCell(cell);
        }
        table.setTextAlignment(TextAlignment.CENTER);
        for (E dato : datos) {
            for (DataTableColumn column : columns) {
                Object value = ClassUtil.getValueMethodOrField(dato, column.getFieldName(), null, null);
                table.addCell(value.getClass().equals(java.util.Date.class) ? DateFormatter.parseDateToString((Date) value): value.toString());
            }
        }
        document.add(table);
        
         */
        ConverterProperties properties = new ConverterProperties();
        properties.setBaseUri("/");
        HtmlConverter.convertToPdf("<h1 style='color: #FF00000;'>Hola html to pdf</h1>", pdf, properties);

        //Close document
        document.close();
    }
}
