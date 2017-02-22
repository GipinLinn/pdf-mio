 $(document).ready(function() {
	   $('.form_date').datetimepicker({
		   format: 'yyyy-mm-dd hh:mm',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1
	    });
	  $("#submitBtn").click( function() {
		  var ischeck = isRadioCheck(); 
		  if (  $('#groupform').parsley().validate() && ischeck){
			  if($('#expiry_date').val()==''){
				  $('#expiry_date').replaceWith('<input id="expiry_date_copy"  class="form-control"  type="text" value="" readonly >');
			  }
	
			  $.ajax({
				   url: "../../groupsave",
				   type: "POST",
				   dataType: "json",
				   data:$('#groupform').serialize(),
				   success: function(msg){
					   var type= $('#group_type').val();
					   if(msg.ret== true || msg.ret== 'true' ){
						   window.location.href="../../grouplist/"+type; 
					   }else{
						   alert("save group failure");
						   $('#expiry_date_copy').replaceWith('<input id="expiry_date" name="expiryDate" class="form-control"  type="text" value="" readonly>');
					   }
					  
				   }
			  });
		  }
		
	   });
	   
	  $("#cancelBtn").click( function() {  
		  var type= $('#group_type').val();
		  window.location.href="../../grouplist/"+type; 
	  });
	  
	  $(":radio").click( function() {
		  isRadioCheck(); 
	  });
})


function isRadioCheck(){
	 $('#status_err').html('');
	  var item = $(":radio:checked"); 
	  var len=item.length; 
	  if(len<=0){ 
		  $('#status_err').html('This value is required.');
	   return false;
	  } 
	  return true;
 }


