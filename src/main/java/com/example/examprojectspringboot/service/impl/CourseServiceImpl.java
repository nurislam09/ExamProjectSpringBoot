package com.example.examprojectspringboot.service.impl;

import com.example.examprojectspringboot.model.Company;
import com.example.examprojectspringboot.model.Course;
import com.example.examprojectspringboot.model.Group;
import com.example.examprojectspringboot.model.Student;
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
        validator(course.getCourseName(),  course.getDescription(),course.getDuration());
       company.addCourse(course);
       course.setCompany(company);
       courseRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, Course course) throws IOException {
       Course course1 = courseRepository.findById(id).get();
        validator(course.getCourseName(),  course.getDescription(),course.getDuration());
       course1.setCourseName(course.getCourseName());
       course1.setDuration(course.getDuration());
       course1.setDescription(course.getDescription());
       courseRepository.save(course1);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        int count = 0;
        for (Group group : course.getGroups()) {
            for (Student student : group.getStudents()) {
                count++;
            }
        }
        int count1 = course.getCompany().getCountOfStudent();
        count1 -= count;
        course.getCompany().setCountOfStudent(count1);

        courseRepository.delete(course);

    }

    private void validator(String courseName, String description, int duration) throws IOException {
        if (courseName.length()>3 && description.length()>5 && description.length()<15 && duration>0 && duration<24){
            for (Character i: courseName.toCharArray()) {
                if (!Character.isLetter(i)){
                    throw new IOException("В название курса нельзя вставлять цифры");
                }
            }
        }else {
            throw new IOException("Form error course registration");
        }
    }
}
