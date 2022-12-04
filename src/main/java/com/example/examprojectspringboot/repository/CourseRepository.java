package com.example.examprojectspringboot.repository;

import com.example.examprojectspringboot.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query(value = "select c from Course c where c.company.id = :companyId")
    List<Course> findAllCourseByCompanyId(Long companyId);
}
