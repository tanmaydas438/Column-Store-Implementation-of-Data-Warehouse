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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

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
	String dimName="";
	int dimNameFlag=0;
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
	 
	 public String getDimensionName(String attr)
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
		        	  getDimensionNameRecursion(doc.getChildNodes(),attr);
		          }

		      } catch (ParserConfigurationException | SAXException | IOException e) {
		          e.printStackTrace();
		      }
			 String dName=dimName;
			 dimName="";
			 dimNameFlag=0;
			return dName;
		}
	 
	 public void getDimensionNameRecursion(NodeList nodeList,String attr) {
		  
		  //String Array for storing column name and path of particular dat file
		 
		  
		 if(dimNameFlag==1)
			 return;
	      for (int count = 0; count < nodeList.getLength(); count++) {

	          Node tempNode = nodeList.item(count);
	          if(dimNameFlag==1)
	          {
	        	  break;
	          }

	          // make sure it's element node.
	          if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
	        	  //System.out.println("hello1");

	              // get node name and value
	              //System.out.println("\nNode Name =" + tempNode.getNodeName());
	              //System.out.println("Node Value =" + tempNode.getTextContent());
	              //System.out.println("Node value : " + tempNode.getNodeValue());
	        	  if(tempNode.getNodeName().equals("name"))
	        	  {
	        		  //System.out.println("inside if");
	        		  dimName=tempNode.getTextContent();
	        		  //System.out.println(dimName);
	        	  }
	        	  if(tempNode.getNodeName().equals("dattrname"))
	        	  {
	        		  //System.out.println("inside if");
	        		  String attrName=tempNode.getTextContent();
	        		  if(attrName.equals(attr))
	        		  {
	        			  dimNameFlag=1;
	        		  }
	        	  }


	        	  
	              
	              
	        	  	if (tempNode.hasChildNodes()) {
	                  // loop again if has child nodes
	        	  		getDimensionNameRecursion(tempNode.getChildNodes(),attr);
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
	 
	 public ArrayList<String> getStringUniqueValues(String name)
	 {
		 ArrayList<String> hs=new ArrayList<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(name);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                String value=din.readUTF();
	                hs.add(value);
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
		 return hs;
	 }
	 public ArrayList<String> getRowIdForStringAttributes(String attr,String value)
	 {
		 ArrayList<String> hs=new ArrayList<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(attr);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                String data=din.readUTF();
	                if(data.equals(value))
	                hs.add(rowId);
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
		 return hs; 
	 }
	 
	 public ArrayList<String> getRowIdForDoubleAttributes(String attr,double value)
	 {
		 ArrayList<String> hs=new ArrayList<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(attr);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                double data=din.readDouble();
	                if(data==value)
	                hs.add(rowId);
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
		 return hs; 
	 }
	 public ArrayList<String> getRowIdForIntegerAttributes(String attr,int value)
	 {
		 ArrayList<String> hs=new ArrayList<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(attr);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                int data=din.readInt();
	                if(data==value)
	                	hs.add(rowId);
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
		 return hs; 
	 }
	 public ArrayList<String> getStringAttributesForRowIds(ArrayList<String> rows,String attr)
	 {
		 ArrayList<String> hs=new ArrayList<String>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(attr);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                String data=din.readUTF();
	                if(rows.contains(rowId))
	                	hs.add(data);
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
		 return hs; 
	 }
	 
	 public ArrayList<Double> getDoubleAttributesForRowIds(ArrayList<String> rows,String attr)
	 {
		 ArrayList<Double> hs=new ArrayList<Double>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(attr);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                double data=din.readDouble();
	                if(rows.contains(rowId))
	                	hs.add(data);
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
		 return hs; 
	 }
	 
	 public ArrayList<Integer> getIntegerAttributesForRowIds(ArrayList<String> rows,String attr)
	 {
		 ArrayList<Integer> hs=new ArrayList<Integer>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(attr);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                int data=din.readInt();
	                if(rows.contains(rowId))
	                	hs.add(data);
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
		 return hs; 
	 }
	 public ArrayList<String> getProductForiegnKeys(Product product)
	 {
		 Utility util=new Utility();
		 ArrayList<String> hs=new ArrayList<>();
		 if(!product.getPRODUCTNAME().equals(""))
		 {
			 ArrayList<String> temp1=util.getRowIdForStringAttributes("PRODUCTNAME", product.getPRODUCTNAME());
			 if(hs.size()==0)
				 hs=temp1;
			 else
				 hs.retainAll(temp1);
			 
		 }
		 
		 if(!product.getCATEGORY().equals(""))
		 {
			 ArrayList<String> temp1=util.getRowIdForStringAttributes("CATEGORY", product.getCATEGORY());
			 if(hs.size()==0)
				 hs=temp1;
			 else
				 hs.retainAll(temp1);
			 
		 }
		 hs=util.getStringAttributesForRowIds(hs, "PRODUCTKEY");
		 //System.out.println(hs);
		 
		 return hs;
	 }
	 
	 public ArrayList<String> getCustomerForiegnKeys(Customer customer)
	 {
		 Utility util=new Utility();
		 ArrayList<String> hs=new ArrayList<>();
		 if(!customer.getCUSTOMERCITY().equals(""))
		 {
			 ArrayList<String> temp1=util.getRowIdForStringAttributes("CUSTOMERCITY", customer.getCUSTOMERCITY());
			 if(hs.size()==0)
				 hs=temp1;
			 else
				 hs.retainAll(temp1);
			 
		 }
		 
		 if(!customer.getCUSTOMERTYPE().equals(""))
		 {
			 ArrayList<String> temp1=util.getRowIdForStringAttributes("CUSTOMERTYPE", customer.getCUSTOMERTYPE());
			 if(hs.size()==0)
				 hs=temp1;
			 else
				 hs.retainAll(temp1);
			 
		 }
		 hs=util.getStringAttributesForRowIds(hs, "CUSTOMERKEY");
		 //System.out.println(hs);
		 
		 return hs;
	 }
	 
	 public ArrayList<String> getSalesPersonForiegnKeys(SalesPerson person)
	 {
		 Utility util=new Utility();
		 ArrayList<String> hs=new ArrayList<>();
		 if(!person.getSALESPERSONTYPE().equals(""))
		 {
			 ArrayList<String> temp1=util.getRowIdForStringAttributes("SALESPERSONTYPE", person.getSALESPERSONTYPE());
			 if(hs.size()==0)
				 hs=temp1;
			 else
				 hs.retainAll(temp1);
			 
		 }
		 
		 if(!(person.getSALESPERSONEXPERIENCE()==0.0))
		 {
			 ArrayList<String> temp1=util.getRowIdForDoubleAttributes("SALESPERSONEXPERIENCE", person.getSALESPERSONEXPERIENCE());
			 if(hs.size()==0)
				 hs=temp1;
			 else
				 hs.retainAll(temp1);
			 
		 }
		 hs=util.getStringAttributesForRowIds(hs, "SALESPERSONKEY");
		 //System.out.println(hs);
		 
		 return hs;
	 }
	 
	 public ArrayList<String> getRowIdsFromSalesForForiegnKeys(ArrayList<String> keys,String keyName)
	 {
		 ArrayList<String> hs=new ArrayList<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath(keyName);
		 //System.out.println("hello");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String rowId=din.readUTF();
	                String data=din.readUTF();
	                if(keys.contains(data))
	                hs.add(rowId);
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
		 return hs; 
	 }
	 
	 public String getStringAttributeForParticularRowId(String rowId,String attr)
	 {
		String value="";
		Utility util=new Utility();
		String path=util.getAttributePath(attr);
		try{
            DataInputStream din = new DataInputStream( new BufferedInputStream( 
                                      new FileInputStream(path) ) );
            
            
			try{while(din.available()>0){
				String id=din.readUTF();
                String data=din.readUTF();
                if(id.equals(rowId))
                {
                	value=data;
                	break;
                }
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
	 return value;
	 }
	 
	 public int getIntegerAttributeForParticularRowId(String rowId,String attr)
	 {
		int value=0;
		Utility util=new Utility();
		String path=util.getAttributePath(attr);
		try{
            DataInputStream din = new DataInputStream( new BufferedInputStream( 
                                      new FileInputStream(path) ) );
            
            
			try{while(din.available()>0){
				String id=din.readUTF();
                int data=din.readInt();
                if(id.equals(rowId))
                {
                	value=data;
                	break;
                }
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
	 return value;
	 }
	 
	 public double getDoubleAttributeForParticularRowId(String rowId,String attr)
	 {
		double value=0.0;
		Utility util=new Utility();
		String path=util.getAttributePath(attr);
		try{
            DataInputStream din = new DataInputStream( new BufferedInputStream( 
                                      new FileInputStream(path) ) );
            
            
			try{while(din.available()>0){
				String id=din.readUTF();
                double data=din.readDouble();
                if(id.equals(rowId))
                {
                	value=data;
                	break;
                }
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
	 return value;
	 }
	 
	 public String getParticularRowIdForStringAttr(String attr,String value)
	 {
		String rowId="";
		Utility util=new Utility();
		String path=util.getAttributePath(attr);
		try{
            DataInputStream din = new DataInputStream( new BufferedInputStream( 
                                      new FileInputStream(path) ) );
            
            
			try{while(din.available()>0){
				String id=din.readUTF();
                String data=din.readUTF();
                if(data.equals(value))
                {
                	rowId=id;
                	break;
                }
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
	 return rowId;
	 }
	 
	  
	 public Product getProduct(String rowIdFactTable)
	 {
		 Product p=new Product();
		 Utility util=new Utility();
	
		 String productKey=util.getStringAttributeForParticularRowId(rowIdFactTable, "PRODUCTKEY-fk");
		 
		 String rowIdInProductTable=util.getParticularRowIdForStringAttr("PRODUCTKEY", productKey);
		 String productName=util.getStringAttributeForParticularRowId(rowIdInProductTable,"PRODUCTNAME");
		 String category=util.getStringAttributeForParticularRowId(rowIdInProductTable, "CATEGORY");
		 p.setPRODUCTKEY(productKey);
		 p.setPRODUCTNAME(productName);
		 p.setCATEGORY(category);
		 return p;
	 }
	 
	 public Customer getCustomer(String rowIdFactTable)
	 {
		 Customer c=new Customer();
		 Utility util=new Utility();
		 String customerKey=util.getStringAttributeForParticularRowId(rowIdFactTable, "CUSTOMERKEY-fk");
		 
		 String rowIdInCustomerTable=util.getParticularRowIdForStringAttr("CUSTOMERKEY", customerKey);
		 String customerCity=util.getStringAttributeForParticularRowId(rowIdInCustomerTable,"CUSTOMERCITY");
		 String customerType=util.getStringAttributeForParticularRowId(rowIdInCustomerTable, "CUSTOMERTYPE");
		 c.setCUSTOMERKEY(customerKey);
		 c.setCUSTOMERCITY(customerCity);
		 c.setCUSTOMERTYPE(customerType);
		 return c;
	 }
	 public SalesPerson getSalesPerson(String rowIdFactTable)
	 {
		 SalesPerson s=new SalesPerson();
		 Utility util=new Utility();
		 String salesPersonKey=util.getStringAttributeForParticularRowId(rowIdFactTable, "SALESPERSONKEY-fk");
		 
		 String rowIdInSalesPersonTable=util.getParticularRowIdForStringAttr("SALESPERSONKEY", salesPersonKey);
		 String salesPersonType=util.getStringAttributeForParticularRowId(rowIdInSalesPersonTable,"SALESPERSONTYPE");
		 double ex=util.getDoubleAttributeForParticularRowId(rowIdInSalesPersonTable, "SALESPERSONEXPERIENCE");
		 s.setSALESPERSONKEY(salesPersonKey);
		 s.setSALESPERSONTYPE(salesPersonType);
		 s.setSALESPERSONEXPERIENCE(ex);
		 return s;
	 }
	 public ArrayList<String> getAllRowIdsFromSales()
	 {
		 ArrayList<String> rowIds=new ArrayList<>();
		 Utility util=new Utility();
		 String path=util.getAttributePath("SALE_UNITS");
		 try{
	            DataInputStream din = new DataInputStream( new BufferedInputStream( 
	                                      new FileInputStream(path) ) );
	            
	            
				try{while(din.available()>0){
					String id=din.readUTF();
	                int data=din.readInt();
	                rowIds.add(id);
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
		 return rowIds;
	 }
	 
	 public ArrayList<ArrayList<String>> getAllCombinations(ArrayList<String> list)
	 {
		 ArrayList<ArrayList<String>> res=new ArrayList<>();
		 int n=list.size();
		 int[] visited=new int[n];
		 ArrayList<String> temp=new ArrayList<>();
		 for(int i=1;i<=n;i++)
		 {
			 recCombinations(res,list,visited,i,temp,0);
		 }
		 return res;
		 
	 }
	 
	 public void recCombinations(ArrayList<ArrayList<String>> res,ArrayList<String> list,int[] visited,int size,ArrayList<String> temp,int index)
	 {
		 if(temp.size()==size)
		 {
			 res.add(new ArrayList(temp));
			 return;
		 }
		 for(int i=index;i<list.size();i++)
		 {
			 if(visited[i]==0)
			 {
				 temp.add(list.get(i));
				 visited[i]=1;
				 recCombinations(res, list, visited, size, temp, i+1);
				 visited[i]=0;
				 temp.remove(list.get(i));
			 }
		 }
	 }
	 
	public HashMap<Lattice,Integer> getGroupBy(String attr1,String attr2,String attr3)
	{
		Utility util=new Utility();
		HashMap<Lattice,Integer> hm=new HashMap<>();
		ArrayList<String> allRowsInSales=new ArrayList<>();
		allRowsInSales=util.getAllRowIdsFromSales();
		for(String rowId:allRowsInSales)
		{
			String productKey=util.getStringAttributeForParticularRowId(rowId, "PRODUCTKEY-fk");
			String customerKey=util.getStringAttributeForParticularRowId(rowId, "CUSTOMERKEY-fk");
			String salesPersonKey=util.getStringAttributeForParticularRowId(rowId, "SALESPERSONKEY-fk");
			String rowIdInProduct=util.getParticularRowIdForStringAttr("PRODUCTKEY",productKey);
			String rowIdInCustomer=util.getParticularRowIdForStringAttr("CUSTOMERKEY",customerKey);
			String rowIdInSalesPerson=util.getParticularRowIdForStringAttr("SALESPERSONKEY",salesPersonKey);
			String attr1Value=util.getStringAttributeForParticularRowId(rowIdInProduct, attr1);
			String attr2Value=util.getStringAttributeForParticularRowId(rowIdInCustomer, attr2);
			String attr3Value=util.getStringAttributeForParticularRowId(rowIdInSalesPerson, attr3);
			Lattice l=new Lattice();
			if(!attr1.equals(""))
			l.setAttr1(attr1Value);
			if(!attr2.equals(""))
			l.setAttr2(attr2Value);
			if(!attr3.equals(""))
			l.setAttr3(attr3Value);
			int salesUnit=util.getIntegerAttributeForParticularRowId(rowId,"SALE_UNITS");
			if(hm.containsKey(l))
			{
				hm.put(l, hm.get(l)+salesUnit);
			}
			else
				hm.put(l, salesUnit);
			
		}
		return hm;
	}
	 
}
