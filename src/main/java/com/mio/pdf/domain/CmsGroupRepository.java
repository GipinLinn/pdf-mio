package com.mio.pdf.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CmsGroupRepository  extends CrudRepository<CmsGroup,Long>{

	/**
	 * 通过使用@Query 注解来创建查询，您只需要编写JPQL语句，并通过类似“:type”来映射@Param指定的参数
	 * @param type
	 * @return
	 */
	@Query("from CmsGroup c where c.type=:type order by sequence")
	public List<CmsGroup> findList(@Param("type") String type);
	
	@Query("from CmsGroup c where c.type=:type and c.status.statusName='Publish'")
	public List<CmsGroup> findPublishList(@Param("type") String type);
}
