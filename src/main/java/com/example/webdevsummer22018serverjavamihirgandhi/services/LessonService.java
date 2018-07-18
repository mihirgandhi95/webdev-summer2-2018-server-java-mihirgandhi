package com.example.webdevsummer22018serverjavamihirgandhi.services;

import java.util.ArrayList;
import java.util.List;
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

import com.example.webdevsummer22018serverjavamihirgandhi.models.Lesson;
import com.example.webdevsummer22018serverjavamihirgandhi.models.Module;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.LessonRepository;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(
			@PathVariable("moduleId") int moduleId,
			@RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(
			@PathVariable("moduleId") int moduleId,
			@PathVariable("courseId") int courseId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return new ArrayList<Lesson>();		
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int lessonId)
	{
		lessonRepository.deleteById(lessonId);	
	}
	
	
	@PutMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}")
	public Lesson editLesson(@PathVariable ("courseId") int courseId,@PathVariable ("moduleId") int moduleId,@PathVariable ("lessonId") int lessonId,@RequestBody Lesson lesson)
	{	
		lesson.setId(lessonId);
		lessonRepository.save(lesson);
		return lesson;
	}
	
	
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons()
	{
		return (List<Lesson>) lessonRepository.findAll();
	}
	
}

