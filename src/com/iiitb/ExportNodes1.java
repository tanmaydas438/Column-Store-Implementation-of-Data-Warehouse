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

public class ExportNodes1 extends HttpServlet{
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
		String attr4="";
		String attr5="";
		for(String attr:node)
		{
			attr=attr.trim();
			String dimName=util.getDimensionName(attr);
			//System.out.println(dimName);
			if(dimName.equals("Product"))
			{
				if(attr1.equals(""))
				{
					attr1+=attr;
				}	
				else
					attr4+=attr;
			}
			else if(dimName.equals("Customer"))
			{
				if(attr2.equals(""))
					attr2+=attr;
				else
					attr5+=attr;
			}
				
			else
				attr3+=attr;
		}
		HashMap<Lattice1,Integer> hm=util.getGroupBy1(attr1, attr2, attr3, attr4, attr5);
		Set<Lattice1> hs=new HashSet<>();
		hs=hm.keySet();
		ArrayList<NodeValue1> nodeValueList=new ArrayList<>();
		for(Lattice1 l:hs)
		{
			NodeValue1 n=new NodeValue1();
			n.setAttr1(l.getAttr1());
			n.setAttr2(l.getAttr2());
			n.setAttr3(l.getAttr3());
			n.setValue(hm.get(l));
			nodeValueList.add(n);
		}
		createCsvForEachNode(nodeValueList,attr1,attr2,attr3,attr4,attr5,p);
	}
	
	public void createCsvForEachNode(ArrayList<NodeValue1> nodeValueList,String attr1,String attr2,String attr3,String attr4,String attr5,String p)
	{
		int count=1;
		if(!attr1.equals(""))
			count++;
		if(!attr2.equals(""))
			count++;
		if(!attr3.equals(""))
			count++;
		if(!attr4.equals(""))
			count++;
		if(!attr5.equals(""))
			count++;
		String[] header=new String[count];
		int i=0;
		if(!attr1.equals(""))
			header[i++]=attr1;
		if(!attr2.equals(""))
			header[i++]=attr2;
		if(!attr3.equals(""))
			header[i++]=attr3;
		if(!attr4.equals(""))
			header[i++]=attr4;
		if(!attr5.equals(""))
			header[i++]=attr5;
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
		if(!attr4.equals(""))
		{
			if(!fileName.equals(""))
				fileName+="-";
			fileName+=attr4;
		}
		if(!attr5.equals(""))
		{
			if(!fileName.equals(""))
				fileName+="-";
			fileName+=attr5;
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
	        for(NodeValue1 n:nodeValueList)
	        {
	        	String[] data=new String[count];
	        	int k=0;
	        	if(!attr1.equals(""))
	    			data[k++]=n.getAttr1();
	    		if(!attr2.equals(""))
	    			data[k++]=n.getAttr2();
	    		if(!attr3.equals(""))
	    			data[k++]=n.getAttr3();
	    		if(!attr4.equals(""))
	    			data[k++]=n.getAttr4();
	    		if(!attr5.equals(""))
	    			data[k++]=n.getAttr5();
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

