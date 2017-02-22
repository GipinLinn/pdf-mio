package com.mio.pdf.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mio.pdf.domain.CmsGroup;
import com.mio.pdf.domain.CmsPdf;
import com.mio.pdf.domain.CmsStatus;
import com.mio.pdf.service.CmsGroupService;
import com.mio.pdf.service.CmsPdfService;

@Controller
//@RequestMapping(value="pdf")
public class PdfController {
	
	 private static final Logger logger = LogManager.getLogger(PdfController.class);
	 
	@Autowired
	private CmsPdfService cmsPdfService;
	
	@Autowired
	private CmsGroupService cmsGroupService;
	
	
    @RequestMapping(value = "/pdflist/{type}", method = RequestMethod.GET)
    public String findAll(Model model, @PathVariable String type) {
    	List<CmsGroup> groupList= cmsGroupService.findPublishList(type);
        model.addAttribute("pdfList", cmsPdfService.findAllByType(type));
        model.addAttribute("groupList", groupList);
        model.addAttribute("title",  type +" Corner ");
        return "pdfList";
    }
    
    @RequestMapping(value = "/pdfcreate/{type}", method = RequestMethod.GET)
    public String create(Model model, @PathVariable String type) {
    	logger.info("create group type ="+ type);
    	List<CmsStatus> status= cmsGroupService.findAllStatus();
    	List<CmsGroup> groupList= cmsGroupService.findPublishList(type);
    	model.addAttribute("groupList", groupList);
        model.addAttribute("status", status);
        model.addAttribute("type", type);
        model.addAttribute("title", type +" Corner -  Add form");
        return "createPdf";
    }
    
    @RequestMapping(value = "/pdfedit/{literatureId}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable Long literatureId) {
    	CmsPdf literature = cmsPdfService.findById(literatureId);
    	List<CmsStatus> status= cmsGroupService.findAllStatus();
    	List<CmsGroup> groupList= cmsGroupService.findPublishList(literature.getType());
      	model.addAttribute("pdf", literature);
        model.addAttribute("status", status);
        model.addAttribute("groupList", groupList);
        model.addAttribute("type", literature.getType());
        model.addAttribute("title",  literature.getType() +" Corner -  Edit form");
        return "editPdf";
    }
    
    
    @RequestMapping(value = "/pdfshow/{literatureId}", method = RequestMethod.GET)
    public String show(Model model,@PathVariable Long literatureId) {
    	CmsPdf literature = cmsPdfService.findById(literatureId);
    	List<CmsStatus> status= cmsGroupService.findAllStatus();
    	List<CmsGroup> groupList= cmsGroupService.findPublishList(literature.getType());
    	model.addAttribute("pdf", literature);
        model.addAttribute("status", status);
        model.addAttribute("groupList", groupList);
        model.addAttribute("type", literature.getType());
        model.addAttribute("title",  literature.getType() +" Corner -  Show form");
        return "showPdf";
    }

    @RequestMapping(value = "/pdfsave",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(CmsPdf literature, MultipartFile pdfFile) {
    	logger.info("save or update Literature start.....");
    	Map<String,Object> retMap = new HashMap<String, Object>();
    	try {
    		CmsPdf nliterature = cmsPdfService.saveOrUpdate(literature, pdfFile);
    		retMap.put("ret", true);
    		retMap.put("group", nliterature);
		} catch (Exception e) {
			retMap.put("ret", false);
			logger.error("save "+literature.getType()+" error",e);
		}
        return retMap;
    }
    
    @RequestMapping(value = "/pdfdownload/{literatureId}", method = RequestMethod.GET)
    public void downloadPdf(HttpServletResponse res,@PathVariable Long literatureId) throws IOException {
    	CmsPdf literature = cmsPdfService.findById(literatureId);
    	 String fileName = literature.getPdfName();
		 OutputStream os = res.getOutputStream();  
		    try {  
		    	byte[] pdfByte =  literature.getPdfContent();
		            
		        res.reset();  
		        res.setHeader("Content-Disposition", "attachment; filename=" +fileName);  
		        res.setContentType("application/octet-stream; charset=utf-8");  
		        os.write(pdfByte);  
		        os.flush();  
		    } finally {  
		        if (os != null) {  
		            os.close();  
		        }  
		    } 
    }
}
