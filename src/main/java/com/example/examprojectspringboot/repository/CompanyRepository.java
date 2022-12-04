package com.example.examprojectspringboot.repository;

import com.example.examprojectspringboot.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
