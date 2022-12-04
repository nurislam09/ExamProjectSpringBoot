package com.example.examprojectspringboot.repository;

import com.example.examprojectspringboot.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    @Query( "select i from Instructor i where i.course.id = :courseId")
    List<Instructor> findAllInstructorByCourseId(Long courseId);
}
