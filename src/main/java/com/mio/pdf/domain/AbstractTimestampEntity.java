package com.mio.pdf.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@MappedSuperclass
public class AbstractTimestampEntity {



	@Column(name = "create_date", updatable = false,  columnDefinition = "datetime")
	private Date createDate;

	
	@Column(name = "last_modified_date", updatable = true, columnDefinition = "datetime")
	private Date lastModifiedDate;
	
	@Column(name = "last_created_by",  length = 500)
	private String lastCreatedBy;
	

	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@Column(name = "publish_date", updatable = true, columnDefinition = "datetime")
	private Date publishDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@Column(name = "expiry_date",nullable=true,  columnDefinition = "datetime")
	private Date expiryDate;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private CmsStatus status;
	
	@Column(name = "type", nullable = false, length = 50)
	private String type;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public CmsStatus getStatus() {
		return status;
	}

	public void setStatus(CmsStatus status) {
		this.status = status;
	}
	
	

	public String getLastCreatedBy() {
		return lastCreatedBy;
	}

	public void setLastCreatedBy(String lastCreatedBy) {
		this.lastCreatedBy = lastCreatedBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
