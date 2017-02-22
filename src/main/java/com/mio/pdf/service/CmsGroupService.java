package com.mio.pdf.service;

import java.util.List;

import com.mio.pdf.domain.CmsGroup;
import com.mio.pdf.domain.CmsStatus;


public interface CmsGroupService {
	
	public List<CmsGroup> findList(String type);
	
	public List<CmsGroup> findPublishList(String type);
	
	public CmsGroup saveOrUpdate(CmsGroup group);
	
	public void updateSeq(CmsGroup group);

	public CmsGroup findById(Long id);
	
	public String saveSeq(List<CmsGroup> groups);
	
	public List<CmsStatus> findAllStatus();
}
