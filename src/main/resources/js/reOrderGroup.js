$(document).ready(function() {
	  
	  var table = $('#reOrderGroupDatatable').DataTable( {
		  info:     false,
		  paging:   false,
		  searching: false,
		
		  rowReorder:  {
	            selector: 'tr'
	        },
	        columnDefs: [
	        	{ orderable: false, targets: [1,2,3,4,5] }, 
	            { targets: [0], visible: false }
	        ]
	    } );
	  
	  
	  $("#saveBtn").click( function() {	
		  var tableData = $('#reOrderGroupDatatable').DataTable().data();
		  function Group(sequence,groupId){
			　　this.sequence = sequence;
			　　this.groupId = groupId;
		  }
		  var groupArray = new Array();
		  for(var i=0;i<tableData.length;i++){
			  var grouptr = tableData[i];
			  var group = new Group(grouptr[0],grouptr[1]);
			  groupArray.push(group);
		  }
		  var groupjson = JSON.stringify(groupArray); 
		  
		  $.ajax({
			   url: "../../groupsaveSeq",
			   type: "POST",
			   dataType: "json",
			   data:{
				   groups:groupjson},
			   success: function(msg){
				   var type= $('#group_type').val();
				   if(msg.ret== true || msg.ret== 'true' ){
					   alert("save group order succeed");
				   }else{
					   alert("save group order failure");
				   }
				  
			   }
		  });

	  });
	 
 });
      
