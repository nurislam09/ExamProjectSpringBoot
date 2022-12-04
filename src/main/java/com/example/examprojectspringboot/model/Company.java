package com.example.examprojectspringboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "companies")
public class Company {
    @Id
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    @GeneratedValue(generator = "company_seq", strategy = GenerationType.SEQUENCE)
    @Column(length = 500)
    private Long id;

    @Column(length = 500)
    private String companyName;

    @Column(length = 500)
    private String locatedCountry;

    @OneToMany(cascade = {DETACH,MERGE,REFRESH},fetch = LAZY,mappedBy = "company")
    private List<Course> courses;

    @Column
    private int countOfStudent=0;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void addCourse(Course course){
        if (course == null) courses = new ArrayList<>();
        courses.add(course);
    }
    public void plus(){
        countOfStudent++;
    }
    public void minus(){
        countOfStudent--;
    }
}
