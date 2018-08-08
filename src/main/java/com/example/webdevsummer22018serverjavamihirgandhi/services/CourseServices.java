package com.example.webdevsummer22018serverjavamihirgandhi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavamihirgandhi.models.Course;
import com.example.webdevsummer22018serverjavamihirgandhi.models.Module;
import com.example.webdevsummer22018serverjavamihirgandhi.models.User;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.CourseRepository;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseServices {
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll(); 
	}
	
	
	@GetMapping("/api/section/course/{courseId}")
	public Optional<Course> findCourseById(@PathVariable("courseId") int id) {
		return courseRepository.findById(id);
	}

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@PutMapping("/api/course/{courseId}")
	public void editCourse(@PathVariable ("courseId") int courseId, @RequestBody String courseTitle)
	{	
		System.out.println("************************************"+courseTitle);
		Optional<Course> course = courseRepository.findById(courseId); 
		
		if(course.isPresent())
		{
			Course updatedCourse = course.get();
			
			updatedCourse.setTitle(courseTitle);
			courseRepository.save(updatedCourse);
			
		}
	}
	
	
//	
//	@GetMapping("/api/course/{courseId}")
//	public Course findCourseById(@PathVariable ("courseId") int courseId) {
//		Optional<Course> course = courseRepository.findById(courseId);
//		
//		if(course.isPresent())
//		{
//			return course;
//		}
//		else
//		{
//			return new Course()
//		}
//	}
	
	@GetMapping("/api/course/{courseTitle}")
	public List<Course> findCourseByTitle(@PathVariable("courseTitle") String courseTitle) {
		//Map<String, String> res = new HashMap<String, String>();
		String bool;
		
		List<Course> courseList = courseRepository.findCourseByTitle(courseTitle);
		if(courseList.size()==0) {
			bool = "false";
		}
		else
		{
			bool ="true";
		}
		if(bool == "true") {
			return courseList;
		}
		else {
		return new ArrayList<Course>();	
		}
		//res.put("bool",""+bool);
		//return res;
		
	}
	
	
	
	

}
