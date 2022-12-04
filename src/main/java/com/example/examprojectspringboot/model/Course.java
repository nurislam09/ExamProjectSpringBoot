package com.example.examprojectspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "courses")
public class Course {
    @Id
    @SequenceGenerator(name = "course_seq",sequenceName = "course_seq",allocationSize = 1)
    @GeneratedValue(generator = "course_seq",strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(length = 500)
    private String courseName;

    @Column(length = 500)
    private Integer duration;

    @Column(length = 500)
    private String description;

    @ManyToOne(cascade = {DETACH,REFRESH,MERGE},fetch = EAGER)
    private Company company;

    @ManyToMany(cascade = {DETACH, REFRESH, MERGE, REMOVE}, fetch = LAZY, mappedBy = "courses")
    private List<Group> groups;

    @OneToMany(cascade = {DETACH, MERGE, REFRESH, REMOVE}, fetch = LAZY, mappedBy = "course")
    private List<Instructor> instructors;

    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "course")
    private List<Lesson> lessons;

    public Course(String courseName, Integer duration, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.description = description;
    }

    public void addGroup(Group group) {
        if (groups == null) groups = new ArrayList<>();
        groups.add(group);
    }

    public void addInstructor(Instructor instructor) {
        if (instructors == null) instructors = new ArrayList<>();
        instructors.add(instructor);
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) lessons = new ArrayList<>();
        lessons.add(lesson);
    }
//    public void remove(Group group){
//        this.groups.remove(group);
//        group.getCourses().remove(this);
//    }
}
