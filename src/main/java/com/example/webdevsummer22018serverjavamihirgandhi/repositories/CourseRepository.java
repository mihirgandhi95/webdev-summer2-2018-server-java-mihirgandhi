package com.example.webdevsummer22018serverjavamihirgandhi.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverjavamihirgandhi.models.Course;


public interface CourseRepository
extends CrudRepository<Course, Integer> {

	@Query ("Select p from Course p where p.title LIKE CONCAT('%',:title,'%')")
	public List<Course> findCourseByTitle(@Param("title") String title);
	
}
