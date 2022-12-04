package com.example.examprojectspringboot.repository;

import com.example.examprojectspringboot.model.Course;
import com.example.examprojectspringboot.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    @Query(value = "select c from Course c where c.company.id = :companyId")
    List<Course> findAllCourseByCompanyId(Long companyId);
}
