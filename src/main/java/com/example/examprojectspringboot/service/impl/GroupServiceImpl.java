package com.example.examprojectspringboot.service.impl;

import com.example.examprojectspringboot.model.Course;
import com.example.examprojectspringboot.model.Group;
import com.example.examprojectspringboot.model.Instructor;
import com.example.examprojectspringboot.model.Student;
import com.example.examprojectspringboot.repository.CourseRepository;
import com.example.examprojectspringboot.repository.GroupRepository;
import com.example.examprojectspringboot.service.GroupService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(CourseRepository courseRepository, GroupRepository groupRepository) {
        this.courseRepository = courseRepository;
        this.groupRepository = groupRepository;
    }
    @Override
    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getAllGroup(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        List<Group> groups = course.getGroups();
        groups.forEach(System.out::println);
        return groups;
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public void saveGroup(Long courseId, Group group) {
        Course course = courseRepository.findById(courseId).get();
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.save(group);
    }

    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = groupRepository.findById(id).get();
        group1.setGroupName(group.getGroupName());
        group1.setDataOfStart(group.getDataOfStart());
        group1.setImage(group.getImage());
        groupRepository.save(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = groupRepository.getById(id);
        List<Student> students = group.getStudents();
        Long count = students.stream().count();
        for (Course course : group.getCourses()) {
            int count1 = course.getCompany().getCountOfStudent();
            count1 -= count;
            course.getCompany().setCountOfStudent(count1);
            for (Instructor instructor : course.getInstructors()) {
                int count2 = instructor.getStudent();
                count2 -= count;
                instructor.setStudent(count2);
            }
        }
        groupRepository.delete(group);
    }

    @Override
    public void assignGroup(Long courseId, Long id) throws IOException {
        Group group = groupRepository.findById(id).get();
        Course course = courseRepository.findById(courseId).get();
        if (course.getGroups() != null) {
            for (Group g : course.getGroups()) {
                if (g.getId() == id) {
                    throw new IOException("This group already exists!");
                }
            }
        }
        group.addCourse(course);
        course.addGroup(group);
        courseRepository.save(course);
        groupRepository.save(group);
    }
}
