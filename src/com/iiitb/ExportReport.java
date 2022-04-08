package com.iiitb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;

public class ExportReport extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		ArrayList<Sales> salesList=(ArrayList<Sales>) req.getAttribute("salesList");
		int noOfRows=salesList.size();
		String checkBoxProductName=(String) req.getAttribute("checkBoxProductName");
		String checkBoxCategory=(String) req.getAttribute("checkBoxCategory");
		String checkBoxCustomerCity=(String) req.getAttribute("checkBoxCustomerCity");
		String checkBoxCustomerType=(String) req.getAttribute("checkBoxCustomerType");
		String checkSalesPersonType=(String) req.getAttribute("checkSalesPersonType");
		String checkSalesPersonExp=(String) req.getAttribute("checkSalesPersonExp");
		int count=1;
		if(checkBoxProductName!=null)
			count++;
		if(checkBoxCategory!=null)
			count++;
		if(checkBoxCustomerCity!=null)
			count++;
		if(checkBoxCustomerType!=null)
			count++;
		if(checkSalesPersonType!=null)
			count++;
		if(checkSalesPersonExp!=null)
			count++;
		String[] header=new String[count];
		int i=0;
		if(checkBoxProductName!=null)
			header[i++]="Product Name";
		if(checkBoxCategory!=null)
			header[i++]="Category";
		if(checkBoxCustomerCity!=null)
			header[i++]="Customer City";
		if(checkBoxCustomerType!=null)
			header[i++]="Customer Type";
		if(checkSalesPersonType!=null)
			header[i++]="SalesPerson Type";
		if(checkSalesPersonExp!=null)
			header[i++]="Salesperson Experience";
		header[i]="Sales Units";
		
		File file = new File("D:\\IIITB\\Term II\\DM\\Project\\Report.csv");
	    try {
	        // create FileWriter object with file as parameter
	        FileWriter outputfile = new FileWriter(file);
	  
	        // create CSVWriter object filewriter object as parameter
	        CSVWriter writer = new CSVWriter(outputfile);
	  
	        // adding header to csv
	        writer.writeNext(header);
	  
	        // add data to csv
	        for(Sales sale:salesList)
	        {
	        	String[] data=new String[count];
	        	int k=0;
	        	if(checkBoxProductName!=null)
	    			data[k++]=sale.getProduct().getPRODUCTNAME();
	    		if(checkBoxCategory!=null)
	    			data[k++]=sale.getProduct().getCATEGORY();
	    		if(checkBoxCustomerCity!=null)
	    			data[k++]=sale.getCustomer().getCUSTOMERCITY();
	    		if(checkBoxCustomerType!=null)
	    			data[k++]=sale.getCustomer().getCUSTOMERTYPE();
	    		if(checkSalesPersonType!=null)
	    			data[k++]=sale.getSalesperson().getSALESPERSONTYPE();
	    		if(checkSalesPersonExp!=null)
	    			data[k++]=String.valueOf(sale.getSalesperson().getSALESPERSONEXPERIENCE());
	    		data[k]=String.valueOf(sale.getSlaesUnit());
	    		writer.writeNext(data);
	        }
	  
	        // closing writer connection
	        writer.close();
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}

}
