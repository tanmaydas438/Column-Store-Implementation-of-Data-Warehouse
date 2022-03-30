package com.iiitb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utility {
	String attr_type="";
	public String getAttributeType(String attr)
	{
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 try (InputStream is = readXmlFileIntoInputStream("D:\\IIITB\\Term II\\DM\\StarSchema.xml")) {
	    	  

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          // read from a project's resources folder
	          Document doc = db.parse(is);

	          //System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	          //System.out.println("------");
	          if (doc.hasChildNodes()) {
	        	  getAttributeTypeRecursion(doc.getChildNodes(),attr,0);
	          }

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      }
		 String type=attr_type;
		 attr_type="";
		return type;
	}

	 public static InputStream readXmlFileIntoInputStream(final String fileName) throws FileNotFoundException {
	      //return AddServlet.class.getClassLoader().getResourceAsStream(fileName);
		  InputStream raw=new FileInputStream(new File(fileName));
		  return raw;
	  }
	 public void getAttributeTypeRecursion(NodeList nodeList,String attr,int flag) {
		  
		  //String Array for storing column name and path of particular dat file
		  
		 if(!attr_type.equals(""))
			 return;
	      for (int count = 0; count < nodeList.getLength(); count++) {

	          Node tempNode = nodeList.item(count);

	          // make sure it's element node.
	          if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

	              // get node name and value
	              //System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
	              //System.out.println("Node Value =" + tempNode.getTextContent());
	              //System.out.println("Node value : " + tempNode.getNodeValue());
	        	  if(flag==1 && (tempNode.getNodeName().equals("dtype") || tempNode.getNodeName().equals("ftype")))
	        	  {
	        		  //System.out.println("inside if");
	        		  attr_type=tempNode.getTextContent();
	        		  break;
	        	  }
	              
	              
	              if (tempNode.hasAttributes()) {

	                  // get attributes names and values
	                  NamedNodeMap nodeMap = tempNode.getAttributes();
	                  for (int i = 0; i < nodeMap.getLength(); i++) {
	                      Node node = nodeMap.item(i);
	                      //System.out.println("attr name : " + node.getNodeName());
	                      //System.out.println("attr value : " + node.getNodeValue());
	                      String name=node.getNodeName();
	                      String value=node.getNodeValue();
	                      if(value.equals(attr))
	                      {
	                    	  //System.out.println("inside iff");
	                    	  flag=1;
	                      }
	                    
	                   
	                  }

	              }

	              if (tempNode.hasChildNodes()) {
	                  // loop again if has child nodes
	            	  getAttributeTypeRecursion(tempNode.getChildNodes(),attr,flag);
	              }

	              //System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

	          }

	      }

	  }
	 
	 public String getAttributePath(String attrName)
	 {
		 //System.out.println(attrName);
		 String path="";
		 String name="";
		 try{
		 DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                new FileInputStream("D:\\IIITB\\Term II\\DM\\Project\\SALES\\path.dat") ) );
		 try{
	    		while(din.available()>0)
	    		{
	    		
	                name=din.readUTF();
	                path=din.readUTF();
	                //System.out.println(name);
	                
	                if(name.equals(attrName))
	                	break;
	    		}
	    		}catch(EOFException e){
	    			e.printStackTrace();
		 
	    		}
		 }catch(FileNotFoundException e)
		 {
			 e.printStackTrace();
		 }catch(IOException e){
			 e.printStackTrace();
		 }
		 return path;
	 }
	 public HashMap<String,Integer> getIntegerColumnValues(String name)
	 {
		 HashMap<String,Integer> hm=new HashMap<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(name);
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
	                String rowId=din.readUTF();
	                int value=din.readInt();
	                hm.put(rowId, value);
	            }}catch(EOFException e){
	            	e.printStackTrace();
	            }
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
		 
		 return hm;
	 }
	 
	 public HashMap<String,String> getStringColumnValues(String name)
	 {
		 HashMap<String,String> hm=new HashMap<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(name);
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                String value=din.readUTF();
	                hm.put(rowId, value);
	            }}catch(EOFException e){
	            	e.printStackTrace();
	            }
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
		 
		 return hm;
	 }
	 
	 public HashMap<String,Double> getDoubleColumnValues(String name)
	 {
		 HashMap<String,Double> hm=new HashMap<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(name);
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                double value=din.readInt();
	                hm.put(rowId, value);
	            }}catch(EOFException e){
	            	e.printStackTrace();
	            }
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
		 
		 return hm;
	 }
}
