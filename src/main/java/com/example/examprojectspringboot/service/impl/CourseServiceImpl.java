package com.example.examprojectspringboot.service.impl;

import com.example.examprojectspringboot.model.Company;
import com.example.examprojectspringboot.model.Course;
import com.example.examprojectspringboot.repository.CompanyRepository;
import com.example.examprojectspringboot.repository.CourseRepository;
import com.example.examprojectspringboot.service.CourseService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CompanyRepository companyRepository,CourseRepository courseRepository){
        this.companyRepository=companyRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getAllCourse(Long companyId) {
        return courseRepository.findAllCourseByCompanyId(companyId);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void saveCourse(Long companyId, Course course) throws IOException {
       Company company = companyRepository.findById(companyId).get();
       company.addCourse(course);
       course.setCompany(company);
       courseRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, Course course) throws IOException {
       Course course1 = courseRepository.findById(id).get();
       course1.setCourseName(course.getCourseName());
       course1.setDuration(course.getDuration());
       course1.setDescription(course.getDescription());
       courseRepository.save(course1);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        courseRepository.delete(course);

    }
}
