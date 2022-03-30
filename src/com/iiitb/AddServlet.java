package com.iiitb;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)

public class AddServlet extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		 	Part filePart = req.getPart("file");
		    //get file name start
		    String fileName = getFilename(filePart);
		    //System.out.println(fileName);
		    //get file name end
		    for (Part part : req.getParts()) {
		      part.write("D:\\IIITB\\Term II\\DM\\" + fileName);
		      //System.out.println(1);
		    }
		    res.getWriter().print("The file uploaded sucessfully.");
		    
		    String targetPath="D:\\IIITB\\Term II\\DM\\"+fileName;
		    createFolder(targetPath);
		    res.getWriter().print("The folder created sucessfully.");
		    res.sendRedirect("uploadcsv.html");
		    
	}
	
	//method to extract filename of the uploaded file....
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	      if (cd.trim().startsWith("filename")) {
	        String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	        return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	      }
	    }
	    return null;
	  }
	
	public void createFolder(String targetPath)
	{
		// Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      HashMap<String,String> map=new HashMap<>();
	      try (InputStream is = readXmlFileIntoInputStream(targetPath)) {
	    	  //System.out.println(targetPath);
	    	  //System.out.println("12121");
	    	  //System.out.println(is);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          // read from a project's resources folder
	          Document doc = db.parse(is);

	          //System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          //System.out.println("------");
	          
	          NamedNodeMap attributes = doc.getDocumentElement().getAttributes();
	          String value="";
              for (int i = 0; i < attributes.getLength(); i++) {
                  Node node = attributes.item(i);
                  //System.out.println("attr name : " + node.getNodeName());
                  //System.out.println("attr value : " + node.getNodeValue());
                  String name=node.getNodeName();
                  
                  if(name.equals("dwhid"))
                  {
                	  value=node.getNodeValue();
                  }  
                	  
                	  
                  }
	          String rootPath="D:\\IIITB\\Term II\\DM\\Project\\"+value;
	          File f = new File(rootPath);
	          boolean val = f.mkdir();
	          //creating path.dat file
	          File file=new File(rootPath+"\\path.dat");
	          String datFilePath=rootPath+"\\path.dat";
	          //System.out.println("debug10..."+datFilePath);
	          val=file.createNewFile();

	          if (doc.hasChildNodes()) {
	              printNote(doc.getChildNodes(),rootPath,map);
	          }
	          
	          //putting map into path.dat file
	          try(DataOutputStream dout = new DataOutputStream( new BufferedOutputStream(
                      new FileOutputStream(datFilePath) ) );
							)
							{
	        	  				for(String name : map.keySet()){
	        	  					String path=map.get(name);
	        	  					System.out.println("name:"+name);
	        	  					System.out.println("path:"+path);
								dout.writeUTF(name);
								//System.out.println("before file:"+path);
								dout.writeUTF(path);
							}
							System.out.println("Record inserted!");
							}
							catch(FileNotFoundException e){
							System.out.println(e.getMessage());
							}
							catch(NullPointerException e){
							System.out.println(e.getMessage());
							}
							catch(IOException e){
							System.out.println(e.getMessage());
							}

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }
	}
	
	 // read file from project resource's folder.
	  public static InputStream readXmlFileIntoInputStream(final String fileName) throws FileNotFoundException {
	      //return AddServlet.class.getClassLoader().getResourceAsStream(fileName);
		  InputStream raw=new FileInputStream(new File(fileName));
		  return raw;
	  }
	  
	  public static void printNote(NodeList nodeList,String rootPath,HashMap<String,String> map) {
		  
		  //String Array for storing column name and path of particular dat file
		  

	      for (int count = 0; count < nodeList.getLength(); count++) {

	          Node tempNode = nodeList.item(count);

	          // make sure it's element node.
	          if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

	              // get node name and value
	              //System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
	              //System.out.println("Node Value =" + tempNode.getTextContent());
	              //System.out.println("Node value : " + tempNode.getNodeValue());
	              
	              String next="";
	              if (tempNode.hasAttributes()) {

	                  // get attributes names and values
	                  NamedNodeMap nodeMap = tempNode.getAttributes();
	                  for (int i = 0; i < nodeMap.getLength(); i++) {
	                      Node node = nodeMap.item(i);
	                      //System.out.println("attr name : " + node.getNodeName());
	                      //System.out.println("attr value : " + node.getNodeValue());
	                      String name=node.getNodeName();
	                      if(name.equals("dimid") || name.equals("factableid"))
	                      {
	                    	  next=node.getNodeValue();
	                    	  String path=node.getNodeValue();
	                    	  File file=new File(rootPath+"\\"+path);
	                    	  try {
	                    		  
	                    		  boolean result=file.mkdir();
	                    		  if(result)
	                    		  {
	                    			  System.out.println("Successfully created");
	                    		  }
	                    		  
	                    	  }catch(Exception e)
	                    	  {
	                    		  e.printStackTrace();
	                    	  }
	                    	  
	                      }
	                      else if(name.equals("dattrid") || name.equals("fkid") || name.equals("fid"))
	                      {
	                    	  String path=node.getNodeValue();
	                    	  File file=new File(rootPath+"\\"+path+".dat");
	                    	  //System.out.println("alag ..............."+rootPath);
	                    	  //System.out.println("alag ..............."+path);
	                    	  try {
	                    		  
	                    		  boolean result=file.createNewFile();
	                    		  if(result)
	                    		  {
	                    			  //putting attr and path to map
	                    			  System.out.println("before Map"+rootPath+"\\"+path+".dat");
	                    			  map.put(path, rootPath+"\\"+path+".dat");
	                    			  System.out.println("Successfully created");
	                    		  }
	                    		  
	                    	  }catch(Exception e)
	                    	  {
	                    		  e.printStackTrace();
	                    	  }
	                    	  
	                      }
	                  }

	              }

	              if (tempNode.hasChildNodes()) {
	                  // loop again if has child nodes
	                  printNote(tempNode.getChildNodes(),rootPath+"\\"+next,map);
	              }

	              //System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

	          }

	      }

	  }

}
