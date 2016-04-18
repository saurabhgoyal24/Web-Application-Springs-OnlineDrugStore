package edu.neu.project;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import edu.neu.project.model.Order;
import edu.neu.project.model.OrderItem;
import edu.neu.project.model.Payment;


public class PDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> map,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
			
		Order order=(Order) map.get("order");
		Payment payment=(Payment)map.get("payment");
		float total = (Float) map.get("total");
	


	      Chunk chunk=new Chunk("Order Receipt!");
	      Phrase space=new Phrase("\n");
	      Chunk chunk12= new Chunk("Your Payment and Order details! \n");
	     
	      
	        Table table2= new Table(4);
	        
	        table2.setWidth(100.0f);
	        table2.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f});
	        table2.setSpacing(10);
	        
	        Cell cell2= new Cell("Order ID: ");
	        cell2.disableBorderSide(Rectangle.BOX);
	        Cell cell3 = new Cell(String.valueOf(order.getOrderId()));
	        cell3.disableBorderSide(Rectangle.BOX);
	        Cell cell4 = new Cell("Payment ID:  ");
	        cell4.disableBorderSide(Rectangle.BOX);
	        Cell cell5= new Cell(String.valueOf(payment.getPaymentId()));
	        cell5.disableBorderSide(Rectangle.BOX);
	        Cell cell6 = new Cell("Total Amount: ");
	        cell6.disableBorderSide(Rectangle.BOX);
	        Cell cell7 = new Cell(String.valueOf(total));
	        cell7.disableBorderSide(Rectangle.BOX);	        
//	        Cell cell8 = new Cell("Date of Order: ");
//	        cell8.disableBorderSide(Rectangle.BOX);
//	        Cell cell9 = new Cell(String.valueOf(order.getTimestamp()));
//	        cell9.disableBorderSide(Rectangle.BOX);
	        Cell cell10 = new Cell("Credit Card Number: ");
	        cell10.disableBorderSide(Rectangle.BOX);
	        Cell cell11 = new Cell(String.valueOf(payment.getNumber()));
	        cell11.disableBorderSide(Rectangle.BOX);
	        
	        Table table3= new Table(3);
	        
	        
	        table3.setWidth(100.0f);
	        //table3.setWidths(new float[] {3.0f, 2.0f, 2.0f});
	       // table3.setSpacing(10);
	        
	        Cell cell12 = new Cell("   Product Name: ");
	        cell12.setBackgroundColor(Color.WHITE);
	        Cell cell13 = new Cell("   Quantity Ordered: ");
	        cell13.setBackgroundColor(Color.white);
	        
	        Cell cell14 = new Cell("   Price: ");
	        cell14.setBackgroundColor(Color.white);
	        
	        table3.addCell(cell12);
	        table3.addCell(cell13);
	        table3.addCell(cell14);
	        
	        for(OrderItem oi: order.getOrderItem())
	        {
	        	 Cell cell15 = new Cell("   " +oi.getDrugProduct().getName());
	        	 table3.addCell(cell15);
	        	
	        	 Cell cell16= new Cell("   "+String.valueOf(oi.getQuantity()));
	        	 
	        	 table3.addCell(cell16);
	        	 Cell cell17 = new Cell("   "+String.valueOf(oi.getTotalCost()));
	        	 table3.addCell(cell17);
	        }
	        
	table2.addCell(cell2);
	table2.addCell(cell3);
	table2.addCell(cell4);
	table2.addCell(cell5);
	table2.addCell(cell6);
	table2.addCell(cell7);
//	table2.addCell(cell8);
//	table2.addCell(cell9);
	table2.addCell(cell10);
	table2.addCell(cell11);
	     table2.setBorderColor(Color.white);
	        table2.setAlignment(Element.ALIGN_MIDDLE);  
	          table3.setBackgroundColor(Color.lightGray);
	          table3.setBorderColor(Color.BLACK);
	          
	    //  document.add(image);
	      document.add(space);
	     document.add(chunk);
	     document.add(space);
	     document.add(chunk12);
	     document.add(space);
	     
	       document.add(table2);
	       document.add(space);
	       document.add(space);
	       document.add(table3);
	    
		document.close();	
	}
	
	

}
