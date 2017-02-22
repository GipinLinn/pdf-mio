package com.mio.pdf.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mio.pdf.domain.CmsGroup;
import com.mio.pdf.domain.CmsStatus;
import com.mio.pdf.service.CmsGroupService;

@Controller
//@RequestMapping(value="group")
public class GroupController {
	private static final Logger logger = LogManager.getLogger(GroupController.class);
	@Autowired
	private CmsGroupService cmsGroupService;
	

    
    @RequestMapping(value = "/grouplist/{type}", method = RequestMethod.GET)
    public String findAll(Model model, @PathVariable String type) {
    	logger.info("find all group for list type ="+ type);
    	List<CmsGroup> groupList= cmsGroupService.findList(type);
        model.addAttribute("groupList", groupList);
        model.addAttribute("title",  type +" Corner -Manage group");
        return "groupList";
    }
    
    @RequestMapping(value = "/groupolist/{type}", method = RequestMethod.GET)
    public String findAllByOrder(Model model, @PathVariable String type) {
    	logger.info("find all group for order type ="+ type);
    	List<CmsGroup> groupList= cmsGroupService.findList(type);
        model.addAttribute("groupList", groupList);
        return "reOrderGroup";
    }
    
    @RequestMapping(value = "/groupcreate/{type}", method = RequestMethod.GET)
    public String create(Model model, @PathVariable String type) {
    	logger.info("create group type ="+ type);
    	List<CmsStatus> status= cmsGroupService.findAllStatus();
        model.addAttribute("status", status);
        model.addAttribute("type", type);
        model.addAttribute("title", type +" Corner -  Add group");
        return "createGroup";
    }
    
    @RequestMapping(value = "/groupedit/{groupId}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable Long groupId) {
    	logger.info("find group by id id="+ groupId);
    	CmsGroup group = cmsGroupService.findById(groupId);
    	List<CmsStatus> status= cmsGroupService.findAllStatus();
        model.addAttribute("group", group);
        model.addAttribute("status", status);
        model.addAttribute("title",  group.getType() +" Corner -  Edit group");
        return "editGroup";
    }
    
    
   
    @RequestMapping(value = "/groupsave", method = RequestMethod.POST)
	@ResponseBody
    public Map<String,Object> save(final CmsGroup group) {
    	logger.info("save or update group start.....");
    	Map<String,Object> retMap = new HashMap<String, Object>();
    	try {
    		CmsGroup ngroup = null;
    		if(group.getGroupId() == null){
    			 ngroup = cmsGroupService.saveOrUpdate(group);
    			 cmsGroupService.updateSeq(ngroup);
    		}else{
    			 ngroup = cmsGroupService.saveOrUpdate(group);
    		}
    		
    		retMap.put("ret", true);
    		retMap.put("group", ngroup);
		} catch (Exception e) {
			retMap.put("ret", false);
			logger.error("save group error",e);
		}
        return retMap;
    }
    
    @RequestMapping(value = "/groupsaveSeq", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveSeq( String  groups) {
    	Map<String,Object> retMap = new HashMap<String, Object>();
    	try {
    		 ObjectMapper mapper = new ObjectMapper();  
    		 List<CmsGroup>   cmsGroups =  mapper.readValue(groups,  new TypeReference<List<CmsGroup>>(){});
    		cmsGroupService.saveSeq(cmsGroups);
    		retMap.put("ret", true);
		} catch (Exception e) {
			retMap.put("ret", false);
			logger.error("save group error",e);
		}
        return retMap;
    }
    
 
}
