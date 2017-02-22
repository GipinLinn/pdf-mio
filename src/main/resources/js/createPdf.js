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
	 
	   
	   //file change event
	   $("#en_pdf").change( function() {
		   var filename = $(this).val();  
		   $("#en_pdf_name").html(filename);
		   validateFile();
	   });
 
	   
	  $("#submitBtn").click( function() {
		  var isEdit = false;
		  var url = window.location.href;
		  if( url.lastIndexOf("edit")>0){
			  isEdit =true;
		  }
		  
		  
		   var isValidate = validateFile(isEdit);
		   var ischeck = isRadioCheck(); 
		   if ($('#pdfform').parsley().validate() && isValidate && ischeck){
			   if($('#expiry_date').val()==''){
					  $('#expiry_date').replaceWith('<input id="expiry_date_copy"  class="form-control"  type="text" value="" readonly >');
				  }
			   
			   $("#pdfform").ajaxSubmit({
					type: "POST",
					url:"../../pdfsave",
					dataType: "json",
				    success: function(msg){
				    	  var type= $('#pdf_type').val();
						   if(msg.ret== true || msg.ret== 'true' ){
							   window.location.href="../../pdflist/"+type; 
						   }else{
							   alert("save pdf failure");
							   $('#expiry_date_copy').replaceWith('<input id="expiry_date" name="expiryDate" class="form-control"  type="text" value="" readonly>');
						   }
						  
					}
				});
		
			}
	   });
	  
	  $("#cancelBtn").click( function() {
		  var type= $('#pdf_type').val();
		  window.location.href="../../pdflist/"+type; 
	   });
	  
	  $("#backBtn").click( function() {
		  var type= $('#pdf_type').val();
		  window.location.href="../../pdflist/"+type; 
		});
	  
	  $("#editBtn").click( function() {
		    var id= $('#pdf-id').val();
	        window.location.href="../../pdfedit/" +id; 
	  });
	  
	  $(":radio").click( function() {
		  isRadioCheck(); 
	  });
 
})

function validateFile(isEdit){
	  $("#en_pdf_err").html("");
	 var efilename = $("#en_pdf").val();  
	 var reg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))(.pdf|.PDF)$/;
	 var result =true;
	 
	 if(efilename == "" && !isEdit){
		  $("#en_pdf_err").html("This value is required.");
		  result = false;
	 }
	if (efilename != "" &&!efilename.match(reg)) {
		$("#en_pdf_err").html("the file must be pdf.");
		result = false;
	}  
	return result;
	 
 }

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