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
import com.example.webdevsummer22018serverjavamihirgandhi.models.Widget;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.TopicRepository;
import com.example.webdevsummer22018serverjavamihirgandhi.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	
//	
//	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
//	public Widget createWidget(
//			@PathVariable("topicId") int topicId,
//			@RequestBody Widget newWidget) {
//		Optional<Topic> data = topicRepository.findById(topicId);
//		if(data.isPresent()) {
//			Topic topic = data.get();
//			newWidget.setTopic(topic);
//			return widgetRepository.save(newWidget);
//		}
//		return null;
//	}
	

	@PostMapping("/api/topic/{topicId}/widget")
	public List <Widget> createWidget(
			@PathVariable("topicId") int topicId,
			@RequestBody List<Widget> widgets) {
		
		for(Widget widget: widgets) {
		Optional<Topic> data = topicRepository.findById(topicId);
		widget.setTopic(data.get());
		widgetRepository.save(widget);
	}
		return widgets;
	}
	
	
	
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget")
	public List<Widget> findAllWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		
		//return widgetRepository.findAllWidgetsForTopic(topicId);
//		Optional<Topic> data = topicRepository.findById(topicId);
//		if(data.isPresent()) {
//			Topic topic = data.get();
//			return topic.getWidgets();
//		}
//		return new ArrayList<Widget>();		
		
		return widgetRepository.findAllWidgetsForTopic(topicId);
	}
	
	
	@GetMapping("/api/topic/{topicId}/widget")
	public List<Widget> findWidgetsForTopic(
			@PathVariable("topicId") int topicId) {
		
		//return widgetRepository.findAllWidgetsForTopic(topicId);
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			return topic.getWidgets();
		}
		return new ArrayList<Widget>();		
	}
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets(){
		return (List<Widget>) widgetRepository.findAll();
	}
	
	
//	@PostMapping("/api/widget/save")
//	public void saveAllWidgets(@RequestBody List <Widget> widgets) {
//		widgetRepository.deleteAll();
//		for(Widget widget:widgets) {
//			widgetRepository.save(widget);
//		}
//	}
//	
	
	
	@PutMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}/topic/{topicId}/widget/{widgetId}")
	public Widget editWidget(@PathVariable ("courseId") int courseId,@PathVariable ("moduleId") int moduleId,@PathVariable ("lessonId") int lessonId,@PathVariable ("topicId") int topicId,@PathVariable ("widgetId") int widgetId,@RequestBody Widget widget)
	{	
		widget.setId(widgetId);
		widgetRepository.save(widget);
		return widget;
	}
	
//	@GetMapping("/api/widget/{widgetId}")
//	public void getWidgetById(@PathVariable ("widgetId") int id) {
//		widgetRepository.findById(id);
//	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable ("widgetId") int id) {
		widgetRepository.deleteById(id);
	}
	
}
