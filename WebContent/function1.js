function filterfunction()   
 {  
	//document.getElementById("myTable").deleteRow(1);
	var a1 = document.getElementById("attr1");
    if(a1){
    	var attr1 = document.getElementById('attr1').selectedOptions[0].value;
    	var attr1Type=document.getElementById('attr1Type').innerHTML;
    }
	
    var a2 = document.getElementById("attr2");
    if(a2){
    	var attr2 = document.getElementById('attr2').selectedOptions[0].value;
    	var attr2Type=document.getElementById('attr2Type').innerHTML;
    }
    var a3 = document.getElementById("attr3");
    if(a3){
    	var attr3 = document.getElementById('attr3').selectedOptions[0].value;
    	var attr3Type=document.getElementById('attr3Type').innerHTML;
    }
    var a4 = document.getElementById("attr4");
    if(a4){
    	var attr4 = document.getElementById('attr4').selectedOptions[0].value;
    	var attr4Type=document.getElementById('attr4Type').innerHTML;
    }
    var a5 = document.getElementById("attr5");
    if(a5){
    	var attr5 = document.getElementById('attr5').selectedOptions[0].value;
    	var attr5Type=document.getElementById('attr5Type').innerHTML;
    }
	var oTable = document.getElementById('myTable');
	var rowLength = oTable.rows.length;
	var deleteRows = new Array();
	var deleteRows_length=0;
	for (i = 1; i < rowLength; i++){
		var oCells = oTable.rows.item(i).cells;
		var headers=oTable.rows.item(0).cells;
		var cellLength = oCells.length;
		var flag=true;
		  for(j=0;j<cellLength;j++)
			  {
			  	var type1=headers.item(j).innerHTML;
			  	console.log(type1);
			  	
			  	console.log(attr1Type);
			  	if(a1 && type1.localeCompare(attr1Type)==0 && (attr1.localeCompare("none")!=0) && (oCells.item(j).innerHTML.localeCompare(attr1)!=0))
			  	{
			  		flag=false;
			  	}
			  	if(a2 && type1.localeCompare(attr2Type)==0 && (attr2.localeCompare("none")!=0) && (oCells.item(j).innerHTML.localeCompare(attr2)!=0))
			  	{
			  		flag=false;
			  	}
			  	if(a3 && type1.localeCompare(attr3Type)==0 && (attr3.localeCompare("none")!=0) && (oCells.item(j).innerHTML.localeCompare(attr3)!=0))
			  	{
			  		flag=false;
			  	}
			  	if(a4 && type1.localeCompare(attr4Type)==0 && (attr4.localeCompare("none")!=0) && (oCells.item(j).innerHTML.localeCompare(attr4)!=0))
			  	{
			  		flag=false;
			  	}
			  	if(a5 && type1.localeCompare(attr5Type)==0 && (attr5.localeCompare("none")!=0) && (oCells.item(j).innerHTML.localeCompare(attr5)!=0))
			  	{
			  		flag=false;
			  	}
			  	
			  }
	       
		           if(!flag)
		           {
		        	   deleteRows_length=deleteRows.push(i);
		        	   
		           }
	}
	for (i = 0; i < deleteRows_length; i++){
		document.getElementById("myTable").deleteRow(deleteRows.pop());
	}
	
}

