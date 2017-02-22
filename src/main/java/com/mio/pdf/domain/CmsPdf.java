package com.mio.pdf.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "cms_pdf")
public class CmsPdf extends AbstractTimestampEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pdf_id", unique = true, nullable = false, columnDefinition = "bigint")
	private Long pdfId;
	
	@Column(name = "pdf_no", nullable = false, length = 500)
	private String pdfNo;
	
	
	@Column(name = "pdf_name", nullable = false, length = 500)
	private String pdfName;
	
	@Column(name = "pdf_dec",length = 2000)
	private String pdfDec;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "pdf_content", nullable = false)
	private byte[] pdfContent; 
	
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)
	private CmsGroup group;
	
	@Column(name = "sequence")
	private Integer sequence;

	public Long getPdfId() {
		return pdfId;
	}

	public void setPdfId(Long pdfId) {
		this.pdfId = pdfId;
	}

	public String getPdfNo() {
		return pdfNo;
	}

	public void setPdfNo(String pdfNo) {
		this.pdfNo = pdfNo;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	public String getPdfDec() {
		return pdfDec;
	}

	public void setPdfDec(String pdfDec) {
		this.pdfDec = pdfDec;
	}



	public byte[] getPdfContent() {
		return pdfContent;
	}

	public void setPdfContent(byte[] pdfContent) {
		this.pdfContent = pdfContent;
	}

	public CmsGroup getGroup() {
		return group;
	}

	public void setGroup(CmsGroup group) {
		this.group = group;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	

	
}
