package com.in28minutes.fullstack.springboot.maven.crud.springbootfullstackcrudfullstackwithmaven.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CourseHardcodedService {
	private static List<Course> courses = new ArrayList<>();
	private static long idCounter = 0;
	
	static {
		courses.add(new Course(++idCounter, "in28minutes", "learn full stack with spring boot and angular"));
		courses.add(new Course(++idCounter, "in28minutes", "learn full stack with spring boot and react"));
		courses.add(new Course(++idCounter, "in28minutes", "master Microservices with spring boot and spring cloud"));
		courses.add(new Course(++idCounter, "in28minutes", "deploy spring boot microservices to cloud with docker and kubernetes"));
	}
	
	public List<Course> findAll() {
		return courses;
	}
	
	public Course save(Course course) {
		if (course.getId() == -1 || course.getId() == 0) {
				course.setId(++idCounter);
				courses.add(course);
		} else {
				deleteById(course.getId());
				courses.add(course);
		}
		return course;
	}
	
	public Course deleteById(long id) {
		Course course = findById(id);
		
		if (course == null)
				return null;
		
		if (courses.remove(course)) {
				return course;
		}
		
		return null;
	}
	
	public Course findById(long id) {
		for (Course course : courses) {
			if (course.getId() == id) {
					return course;
			}			
		}
		
		return null;
	}
}
