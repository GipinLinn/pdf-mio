
$(document).ready(function() {
	$('#left_col_div').html('  <div class="left_col scroll-view">'
			+'<div class="navbar nav_title" style="border: 0; ">'
			+'   <a href="index.html" class="site_title"><img src="../images/thymeleaf.png" alt="Zurich Logo" ></a> <br/>'
			+'   <span style="font-size: 15px;">mio\'s   CMS demo</span>'
			+'</div>'
			+' <div class="clearfix"></div> <br />'
			+'<div id="sidebar-menu" class="main_menu_side hidden-print main_menu"><div class="menu_section">'
			+'   <ul class="nav side-menu">'
			+'    <li><a><i class="fa fa-home"></i> 类型一 <span class="fa fa-chevron-down"></span></a>'
			+'      <ul class="nav child_menu">'
			+'          <li><a href="../../pdflist/type1"> pdf管理</a></li>'
			+'           <li><a href="../../grouplist/type1"> group管理</a></li>'
			+'       </ul>'
			+'     </li>'
			+'    <li><a><i class="fa fa-home"></i> 类型二<span class="fa fa-chevron-down"></span></a>'
			+'      <ul class="nav child_menu">'
			+'          <li><a href="../../pdflist/type2">  pdf管理</a></li>'
			+'           <li><a href="../../grouplist/type2">group管理</a></li>'
			+'       </ul>'
			+'     </li>'
			+'      <li><a href="../../login.html"><i class="fa fa-edit"></i> Logout <span class="fa fa-chevron-down"></span></a></li>'
			+'  </ul>'
			+'</div></div>'
			+'</div>');
	
	
	$('#top_nav_div').html('<div class="mod mod-Navigation">'
			+'<div class="sidebar-offcanvas navbar"><div class="container">'
			+'<div class="container-inlay">'
			+'   <div class="search-copyright-wrapper">'
			+'     <span class="copy-site-identifier-1 hidden-xs top_title">Hello, mio zeng</span>'
			+'   </div>'
			+'</div>'
			+'</div></div>'
			+'</div>');
 });
    