package com.example.webdevsummer22018serverjavamihirgandhi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.example.webdevsummer22018serverjavamihirgandhi.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	
	@Query(value = "select * from widget where topic_id=?1 order by widget_order", nativeQuery = true)
	public List<Widget> findAllWidgetsForTopic(@Param("topicId") int topicId);
	
}
