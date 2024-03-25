package com.radhatechie.controller;

import com.radhatechie.dto.Course;
import com.radhatechie.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody  Course course){
        Course newCourse = courseService.onboardNewCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>>findAllCourses(){
       return new ResponseEntity<>(courseService.viewAllCourses(), HttpStatus.OK);
    }

    @GetMapping("search/path/{courseId}")
    public ResponseEntity<?> findCourse(@PathVariable  Integer courseId){
        return new ResponseEntity<>(courseService.findByCourseId(courseId), HttpStatus.OK);
    }

    @GetMapping("search/request")
    public ResponseEntity<?> findCourseUsingRequestParam(@RequestParam(required = false)  int courseId){
        return new ResponseEntity<>(courseService.findByCourseId(courseId), HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer courseId, @RequestBody Course course){
        Course updatedCourse = courseService.updateCourse(courseId, course);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }



}
