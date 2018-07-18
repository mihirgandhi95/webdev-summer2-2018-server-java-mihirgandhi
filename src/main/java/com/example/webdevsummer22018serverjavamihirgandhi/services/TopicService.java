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
import com.example.webdevsummer22018serverjavamihirgandhi.models.Topic;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.LessonRepository;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.ModuleRepository;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.TopicRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	TopicRepository topicRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public Topic createTopic(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Topic newTopic) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			newTopic.setLesson(lesson);
			return topicRepository.save(newTopic);
		}
		return null;
	}
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic")
	public List<Topic> findAllTopicssForLesson(
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getTopics();
		}
		return new ArrayList<Topic>();		
	}
	
	
	@PutMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}")
	public Topic editTopic(@PathVariable ("courseId") int courseId,@PathVariable ("moduleId") int moduleId,@PathVariable ("lessonId") int lessonId,@PathVariable ("topicId") int topicId,@RequestBody Topic topic)
	{	
		topic.setId(topicId);
		topicRepository.save(topic);
		return topic;
	}
	
	
	@DeleteMapping("/api/topic/{topicId}")
	public void deleteTopic(@PathVariable("topicId") int topicId)
	{
		topicRepository.deleteById(topicId);
		
	}
	
	@GetMapping("/api/topic")
	public List<Topic> findAllTopics()
	{
		return (List<Topic>) topicRepository.findAll();
	}
	

	
}
