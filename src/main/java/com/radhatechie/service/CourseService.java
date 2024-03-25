package com.radhatechie.service;

import com.radhatechie.dto.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class CourseService {

    private List<Course> courseDatabase  = new ArrayList<>();

    // create a course object in DB
    public Course onboardNewCourse(Course course){
        course.setCourseId(new Random().nextInt(3756));
        courseDatabase.add(course);
        return course;
    }

    // load all courses
    public List<Course> viewAllCourses(){
        return courseDatabase;
    }

    // filter course by course ID
    public Course findByCourseId(int courseId){
        return courseDatabase.stream()
                .filter(course -> course.getCourseId() == courseId)
                .findFirst().orElse(null);
    }

    public void deleteCourse(int courseId){
        Course course = findByCourseId(courseId);
        courseDatabase.remove(course);
    }

    public Course updateCourse(int courseId, Course course){
        Course existingCourse = findByCourseId(courseId);
        return courseDatabase.set(courseDatabase.indexOf(existingCourse), course);
    }
}
