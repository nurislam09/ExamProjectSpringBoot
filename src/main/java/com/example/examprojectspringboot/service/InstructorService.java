package com.example.examprojectspringboot.service;

import com.example.examprojectspringboot.model.Instructor;
import com.example.examprojectspringboot.model.Lesson;

import java.io.IOException;
import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructor();
    List<Instructor> getAllInstructor(Long courseId);
    Instructor getInstructorById(Long id);
    void saveInstructor(Long courseId, Instructor instructor) throws IOException;
    void updateInstructor(Long id, Instructor instructor) throws IOException;
    void deleteInstructor(Long id);
    void assignInstructor(Long id, Long courseId) throws IOException;

}
