package com.in28minutes.fullstack.springboot.maven.crud.springbootfullstackcrudfullstackwithmaven.course;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = {"http://localhost:3001", "https://master--todo-list-javaspring-react.netlify.com", "https://todo-list-javaspring-react.netlify.com"})
@RestController
public class courseResource {
	@Autowired
	private CourseHardcodedService courseManagementService;
	
	@GetMapping ("/instructors/{username}/courses")
	public List <Course> getAllCourses(@PathVariable String username) {
			return courseManagementService.findAll();
	}
	
	@GetMapping("/instructors/{username}/courses/{id}")
	public Course getCourse(@PathVariable String username, @PathVariable long id) {
			return courseManagementService.findById(id);
	}
	
	@DeleteMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable String username, @PathVariable long id){
		
			Course course = courseManagementService.deleteById(id);
			
			if (course != null) {
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/instructors/{username}/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable String username, @RequestBody Course course){
		
			Course createdCourse = courseManagementService.save(course);
			
			//location
			//get current resource url
			/// {id}
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCourse.getId())
							.toUri();
			return ResponseEntity.created(uri).build();
	}

}
