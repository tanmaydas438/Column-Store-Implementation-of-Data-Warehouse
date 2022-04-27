function filterfunction()   
 {  
	//document.getElementById("myTable").deleteRow(1);
	var a1 = document.getElementById("attr1");
    if(a1){
    	var attr1 = document.getElementById('attr1').selectedOptions[0].value;
    }
	
    var a2 = document.getElementById("attr2");
    if(a2){
    	var attr2 = document.getElementById('attr2').selectedOptions[0].value;
    }
    var a3 = document.getElementById("attr3");
    if(a3){
    	var attr3 = document.getElementById('attr3').selectedOptions[0].value;
    }
	var oTable = document.getElementById('myTable');
	var rowLength = oTable.rows.length;
	var deleteRows = new Array();
	var deleteRows_length=0;
	for (i = 1; i < rowLength; i++){
		var oCells = oTable.rows.item(i).cells;
		var cellLength = oCells.length;
		var flag=true;
		var j=0;
		var k=0;
		var l=0;
		if(a1)
			k=1;
		if(!a1 && a2)
			l=1;
		if(a1 && a2)
			l=2;
		console.log(j);
		console.log(k);
		console.log(l);
			
		
				if(a1 && (attr1.localeCompare("none")!=0) && (oCells.item(j).innerHTML.localeCompare(attr1)!=0))
				{
					flag=false;
				}
				if(a2 && (attr2.localeCompare("none")!=0) &&(oCells.item(k).innerHTML.localeCompare(attr2)!=0))
				{
					flag=false;
				}
				if(a3 && (attr3.localeCompare("none")!=0) &&(oCells.item(l).innerHTML.localeCompare(attr3)!=0))
				{
					flag=false;
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

