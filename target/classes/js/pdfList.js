
$(document).ready(function() {
	
	
    var $datatable = $('#pdfDatatable');

    $datatable.dataTable({
      'order': [[ 1, 'asc' ]],
      'columnDefs': [
        { orderable: false, targets: [0] },
        { orderable: false, targets: [8] },
        {"targets": [ 2 ], orderable: false,visible:false }
      ]
    });
    $datatable.on('draw.dt', function() {
      $('input').iCheck({
        checkboxClass: 'icheckbox_flat-green'
      });
    });

    $("#groupId").change( function() {
		   var groupId = $(this).val();  
		   $datatable.DataTable().column( 2 ).search( groupId).draw();
	   });
    
    
    $("#addBtn").click( function() {
   	  var url = window.location.href;
	  var type = url.substring(url.lastIndexOf("/"),url.length);
	  window.location.href="../../pdfcreate"+type; 
	});
 });
    