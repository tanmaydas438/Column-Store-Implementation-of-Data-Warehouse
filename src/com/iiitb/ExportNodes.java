package com.iiitb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opencsv.CSVWriter;

public class ExportNodes extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		ArrayList<ArrayList<String>> nodes=new ArrayList<>();
		nodes=(ArrayList<ArrayList<String>>)req.getAttribute("nodes");
		Date d=new Date();
		String p=d.toString();
		p=p.replace(':','-');
		//System.out.println(p);
		for(ArrayList<String> node:nodes)
		{
			handleNode(node,p);
		}
	}
	public void handleNode(ArrayList<String> node,String p)
	{
		Utility util=new Utility();
		String attr1="";
		String attr2="";
		String attr3="";
		for(String attr:node)
		{
			attr=attr.trim();
			String dimName=util.getDimensionName(attr);
			//System.out.println(dimName);
			if(dimName.equals("Product"))
				attr1+=attr;
			else if(dimName.equals("Customer"))
				attr2+=attr;
			else
				attr3+=attr;
		}
		HashMap<Lattice,Integer> hm=util.getGroupBy(attr1, attr2, attr3);
		Set<Lattice> hs=new HashSet<>();
		hs=hm.keySet();
		ArrayList<NodeValue> nodeValueList=new ArrayList<>();
		for(Lattice l:hs)
		{
			NodeValue n=new NodeValue();
			n.setAttr1(l.getAttr1());
			n.setAttr2(l.getAttr2());
			n.setAttr3(l.getAttr3());
			n.setValue(hm.get(l));
			nodeValueList.add(n);
		}
		createCsvForEachNode(nodeValueList,attr1,attr2,attr3,p);
	}
	
	public void createCsvForEachNode(ArrayList<NodeValue> nodeValueList,String attr1,String attr2,String attr3,String p)
	{
		int count=1;
		if(!attr1.equals(""))
			count++;
		if(!attr2.equals(""))
			count++;
		if(!attr3.equals(""))
			count++;
		String[] header=new String[count];
		int i=0;
		if(!attr1.equals(""))
			header[i++]=attr1;
		if(!attr2.equals(""))
			header[i++]=attr2;
		if(!attr3.equals(""))
			header[i++]=attr3;
		header[i]="Sale Units";
		
		//creating folder 
		File file1=new File("D:\\IIITB\\Term II\\DM\\Project\\Lattice\\"+p);
  	  try {
  		  
  		  boolean result=file1.mkdir();
  		  //System.out.println(result);
  		  if(result)
  		  {
  			  System.out.println("Successfully created folder for Lattice");
  		  }
  		  
  	  }catch(Exception e)
  	  {
  		  e.printStackTrace();
  	  }
  	  	String fileName="";
  	  if(!attr1.equals(""))
			fileName+=attr1;
		if(!attr2.equals(""))
		{
			if(!fileName.equals(""))
				fileName+="-";
			fileName+=attr2;
		}	
		if(!attr3.equals(""))
		{
			if(!fileName.equals(""))
				fileName+="-";
			fileName+=attr3;
		}	
		String path="D:\\IIITB\\Term II\\DM\\Project\\Lattice\\"+p+"\\"+fileName+".csv";
		File file = new File(path);
	    try {
	        // create FileWriter object with file as parameter
	        FileWriter outputfile = new FileWriter(file);
	  
	        // create CSVWriter object filewriter object as parameter
	        CSVWriter writer = new CSVWriter(outputfile);
	  
	        // adding header to csv
	        writer.writeNext(header);
	        for(NodeValue n:nodeValueList)
	        {
	        	String[] data=new String[count];
	        	int k=0;
	        	if(!attr1.equals(""))
	    			data[k++]=n.getAttr1();
	    		if(!attr2.equals(""))
	    			data[k++]=n.getAttr2();
	    		if(!attr3.equals(""))
	    			data[k++]=n.getAttr3();
	    		data[k]=String.valueOf(n.getValue());
	    		writer.writeNext(data);
	        }
	        
	        writer.close();
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}
