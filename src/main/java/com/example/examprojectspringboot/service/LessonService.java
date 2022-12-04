package com.example.examprojectspringboot.service;

import com.example.examprojectspringboot.model.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getAllLesson();

    List<Lesson> getAllLesson(Long courseId);

    Lesson getLessonById(Long id);

    void saveLesson(Long courseId, Lesson lesson);

    void updateLesson(Long id, Lesson lesson);

    void deleteLesson(Long id);
}
