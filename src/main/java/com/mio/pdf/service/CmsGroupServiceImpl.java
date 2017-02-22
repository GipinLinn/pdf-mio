package com.mio.pdf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mio.pdf.domain.CmsGroup;
import com.mio.pdf.domain.CmsGroupRepository;
import com.mio.pdf.domain.CmsStatus;
import com.mio.pdf.domain.CmsStatusRepository;

@Repository
@Transactional
public class CmsGroupServiceImpl implements CmsGroupService {

	@Autowired 
	CmsGroupRepository repository;
	
	@Autowired 
	CmsStatusRepository statusRepository;
	@Override
	public List<CmsGroup> findList(String type) {
		return repository.findList(type);
	}

	@Override
	public List<CmsGroup> findPublishList(String type) {
		return repository.findPublishList(type);
	}

	@Override
	public CmsGroup saveOrUpdate(CmsGroup group) {
		if(group.getGroupId() == null){
			group.setCreateDate(new Date());
			group.setLastModifiedDate(new Date());
			group.setLastCreatedBy(null);
			repository.save(group);
			return group;
		}else{
			//use old date
			CmsGroup ogroup = repository.findOne(group.getGroupId());
			ogroup.setGroupName(group.getGroupName());
			ogroup.setExpiryDate(group.getExpiryDate());
			ogroup.setPublishDate(group.getPublishDate());
			ogroup.setStatus(group.getStatus());
			ogroup.setType(group.getType());
			ogroup.setLastModifiedDate(new Date());
			ogroup.setLastCreatedBy(null);
			repository.save(group);
			return group;
		}
	}

	@Override
	public void updateSeq(CmsGroup group) {
		group.setSequence(group.getGroupId());
		repository.save(group);
	}

	@Override
	public CmsGroup findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public String saveSeq(List<CmsGroup> groups) {
		for (CmsGroup cmsGroup : groups) {
			CmsGroup group = repository.findOne(cmsGroup.getGroupId());
			group.setSequence(cmsGroup.getSequence());
			repository.save(group);
		}
		return null;
	}

	@Override
	public List<CmsStatus> findAllStatus() {
		Iterable<CmsStatus> css= statusRepository.findAll();
		List<CmsStatus> cs = new ArrayList<>();
		for (Iterator iterator = css.iterator(); iterator.hasNext();) {
			CmsStatus type = (CmsStatus) iterator.next();
			cs.add(type);
		}
		return cs;
	}

}
