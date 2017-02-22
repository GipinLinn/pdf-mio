package com.mio.pdf.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mio.pdf.domain.CmsPdf;

public interface CmsPdfService {

	public List<CmsPdf> findAllByType(String type);
	
	public CmsPdf saveOrUpdate(CmsPdf literature, MultipartFile pdfContent) throws IOException;
	
	public CmsPdf findById(Long id);
}
