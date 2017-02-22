package com.mio.pdf.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mio.pdf.domain.CmsPdf;
import com.mio.pdf.domain.CmsPdfRepository;

@Repository
@Transactional
public class CmsPdfServiceImpl implements CmsPdfService {

	@Autowired 
	CmsPdfRepository repository;
	@Override
	public List<CmsPdf> findAllByType(String type) {
		return repository.findByType(type);
	}

	@Override
	public CmsPdf saveOrUpdate(CmsPdf literature, MultipartFile pdfContent) throws IOException {
		if(literature.getPdfId() == null){
			literature.setPdfContent(pdfContent.getBytes());
			literature.setPdfName(pdfContent.getOriginalFilename());
			literature.setCreateDate(new Date());
			literature.setLastCreatedBy(null);
			literature.setLastModifiedDate(new Date());
			repository.save(literature);
			return literature;
		}else{
			CmsPdf ol = repository.findOne(literature.getPdfId() );
			if(pdfContent!=null){
				ol.setPdfContent(pdfContent.getBytes());
				ol.setPdfName(pdfContent.getOriginalFilename());
			}
			ol.setPdfNo(literature.getPdfNo());
			ol.setPdfDec(literature.getPdfDec());
			ol.setPublishDate(literature.getPublishDate());
			ol.setExpiryDate(literature.getExpiryDate());
			ol.setStatus(literature.getStatus());
			
			ol.setLastCreatedBy(null);
			ol.setLastModifiedDate(new Date());
			repository.save(ol);
			return ol;
		}
	
	}

	@Override
	public CmsPdf findById(Long id) {
		return repository.findOne(id);
	}

}
