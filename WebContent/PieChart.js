function caller()
{
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
    if((a1 && !a2 && !a3)||(!a1 && a2 && !a3)||(!a1 && !a2 && a3))
    	createPie();
}

function createPie(){
	
	var oTable = document.getElementById('myTable');
	var rowLength = oTable.rows.length;
	var X=new Array();
	var Y=new Array();
	for (i = 1; i < rowLength; i++){
		var oCells = oTable.rows.item(i).cells;
		var cellLength = oCells.length;
		X.push(oCells.item(0).innerHTML);
		Y.push(oCells.item(1).innerHTML);
	}
	console.log(X);
	console.log(Y);
	
	
	

	var barColors = [
	  "#b91d47",
	  "#00aba9",
	  "#2b5797",
	  "#e8c3b9",
	  "#1e7145",
	  "#00aba9",
	  "#e8c3b9",
	  "#b91d47"
];

new Chart("myPieChart", {
  type: "pie",
  data: {
    labels: X,
    datasets: [{
      backgroundColor: barColors,
      data: Y
    }]
  },
  options: {
    title: {
      display: true,
      text: "World Wide Wine Production 2018"
    }
  }
}),
new Chart("myBarChart", {
	  type: "bar",
	  data: {
	    labels: X,
	    datasets: [{
	      backgroundColor: barColors,
	      data: Y
	    }]
	  },
	  options: {
	    title: {
	      display: true,
	      text: "World Wide Wine Production 2018"
	    }
	  }
	});
}