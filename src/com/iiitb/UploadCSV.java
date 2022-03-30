package com.iiitb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

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

public class UploadCSV extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
		Utility util=new Utility();
		Part filePart = req.getPart("myfile");
	    //get file name start
	    String fileName = getFilename(filePart);
	  //get file name end
	    for (Part part : req.getParts()) {
	      part.write("D:\\IIITB\\Term II\\DM\\" + fileName);
	      //System.out.println(1);
	    }
	    
	    //reading csv file
	    //System.out.println(fileName);
	    String csvPath="D:\\IIITB\\Term II\\DM\\"+fileName;
	    String splitBy=",";
	    BufferedReader br=new BufferedReader(new FileReader(csvPath));
	    String[] columns=br.readLine().split(splitBy);
	    String[][] data=new String[columns.length][500];
	    //System.out.println("debug2"+columns.length);
	    String line="";
	    int j=0;
	    //data[0][0]="hello";
	    //System.out.println(data[0][0]);
	    while((line=br.readLine())!=null)
	    {
	    	String[] row=line.split(splitBy);
	    	//System.out.println("debug1"+row.length);
	    	
	    	for(int i=0;i<row.length;i++)
	    	{
	    		
	    		
	    		data[i][j]=row[i];
	    		//System.out.println(data[i][j]);
	    		
	    	}
	    	j++;
	    }
	    int rowCount=j;
	    int columnCount=columns.length;
	    
	    
	    
	    res.getWriter().print("The file uploaded sucessfully.");
	    String datFilePath="D:\\IIITB\\Term II\\DM\\Project\\SALES\\path.dat";
	    String name="";
	    String path="";
	    
	    for(int p=0;p<columnCount;p++)
	    {
	    	path=util.getAttributePath(columns[p]);
	    	System.out.println(columns[p]);
	    	System.out.println("debg:  "+path);
	    	name=columns[p];
	    	
	    	
	    	try(DataOutputStream dout = new DataOutputStream( new BufferedOutputStream(
                    new FileOutputStream(path) ) );
							)
							{
	    						String type=util.getAttributeType(name);
	    						int l=fileName.length();
				    			String row_prefix=fileName.substring(0,l-4);
					    		for(int q=0;q<rowCount;q++)
						    	{
					    			String rowId=row_prefix+String.valueOf((q+1));
					    			dout.writeUTF(rowId);
					    			if(type.equals("integer"))
					    			{
					    				
					    				//dout.writeInt(q+1);
					    				dout.writeInt(Integer.parseInt(data[p][q]));
					    				
					    				
					    			}	
					    			else if(type.equals("float"))
					    			{
					    				
					    				//dout.writeInt(q+1);
					    				dout.writeDouble(Double.parseDouble(data[p][q]));
					    				
					    				
					    			}	
					    			else
					    			{
					    				//dout.writeInt(q+1);
					    				dout.writeUTF(data[p][q]);
					    				
					    			}
					    			
					    			
						    		//dout.w
						    	}
							//System.out.println("Record inserted!");
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
	    		
	    	
	    }
	    res.sendRedirect("uploadcsv.html");
	    

	}
	
	
	
	
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	      if (cd.trim().startsWith("filename")) {
	        String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	        return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	      }
	    }
	    return null;
	  }	
	
	public String getPath(String path)
	{
		String res="";
		String separator = "\\";
		String[] arr = path.split(Pattern.quote(separator));
		int n=arr.length;
		for(int i=0;i<n;i++)
		{
			//System.out.println(arr[i]);
			res+=arr[i];
			if(!arr[i].equals("") && i!=n-1)
				res+="\\";
		}
		return res;
	}
	

}
