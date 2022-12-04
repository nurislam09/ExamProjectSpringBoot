package com.example.examprojectspringboot.service.impl;

import com.example.examprojectspringboot.model.Course;
import com.example.examprojectspringboot.model.Lesson;
import com.example.examprojectspringboot.repository.CourseRepository;
import com.example.examprojectspringboot.repository.LessonRepository;
import com.example.examprojectspringboot.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(CourseRepository courseRepository,LessonRepository lessonRepository){
        this.courseRepository=courseRepository;
        this.lessonRepository=lessonRepository;
    }
    @Override
    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> getAllLesson(Long courseId) {
        return lessonRepository.findAllLessonByCourseId(courseId);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).get();
    }

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
       Course course = courseRepository.findById(courseId).get();
       course.addLesson(lesson);
       lesson.setCourse(course);
       lessonRepository.save(lesson);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
      Lesson lesson1= lessonRepository.findById(id).get();
      lesson1.setLessonName(lesson.getLessonName());
      lessonRepository.save(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
       Lesson lesson = lessonRepository.findById(id).get();
       lessonRepository.delete(lesson);
    }
}
