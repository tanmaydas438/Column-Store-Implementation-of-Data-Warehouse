package com.iiitb;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
 

public class DatRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//String pa="D:\IIITB\Term II\DM\Project\dwh1\\\dim3\\dim3-attr2.dat";
		Utility util=new Utility();
		HashSet<String> products=util.getStringUniqueValues("PRODUCTNAME");
		for(String p:products)
		{
			System.out.println(p);
		}
		
		//System.out.println(util.getAttributePath("dim1-attr1"));
		/*HashMap<String,Integer> hm=util.getIntegerColumnValues("dim1-attr1");
		for(String key:hm.keySet())
		{
			System.out.print(key);
			System.out.print(hm.get(key));
			System.out.println();
		}*/
		/*try{
            String attr = "";
            String p = "";
            DataInputStream din = new DataInputStream( new BufferedInputStream( 
                                      new FileInputStream("D:/IIITB/Term II/DM/Project/SALES/path.dat") ) );
            
            
            boolean eof=false;
			try{while(din.available()>0){
				String key=din.readUTF();
                String value=din.readUTF();
                
                
                System.out.print(key+"  :");
                System.out.print(value);
                System.out.println();
            }}catch(EOFException e){
            	e.printStackTrace();
            }
            System.out.println("Fetched Information:");
            System.out.println(attr);
            System.out.println(p);
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
       catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
       catch(IOException e){
            System.out.println(e.getMessage());
        }*/
    }

	}

