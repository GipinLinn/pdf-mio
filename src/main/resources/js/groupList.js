
$(document).ready(function() {

	 var url = window.location.href;
	 var type = url.substring(url.lastIndexOf("/"),url.length);
	  $("#addBtn").click( function() {
	        window.location.href="../../groupcreate"+type; 
		   });
	  
	  $("#reorderBtn").click( function() {
	        window.location.href="../../groupolist"+type; 
		   });
 });
    