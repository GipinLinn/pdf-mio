package com.mio.pdf.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CmsPdfRepository  extends CrudRepository<CmsPdf,Long>{

	/**
	 * 通过解析方法名创建查询
	 * @param type
	 * @return
	 */
	public List<CmsPdf> findByType(String type);
}
