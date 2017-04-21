
/**
 * Copyright(c) 2000-2013 HC360.COM, All Rights Reserved.
 * Project: spring-mybatis-demo-all-in-one 
 * Author: dixingxing
 * Createdate: ����3:47:01
 * Version: 1.0
 *
 */
package com.hc360.mobileaccount.web.showcase.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc360.mobileaccount.po.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @project spring-mybatis-demo-all-in-one
 * @author dixingxing
 * @version 1.0
 * @date 2013-10-12 ����3:47:01   
 */
public class PdfView extends AbstractIText5PdfView{
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
 
        List<User> users = (List<User>) model.get("users");
         
        doc.add(new Paragraph("All users : "));
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {1.0f, 3.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
         
        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("ZipCode", font));
        table.addCell(cell);
          
         
        for (User u : users) {
            table.addCell(String.valueOf(u.getId()));
            table.addCell(u.getName());
            table.addCell(String.valueOf(u.getAge()));
            table.addCell(u.getZipCode());
        }
         
        doc.add(table);
	}
}