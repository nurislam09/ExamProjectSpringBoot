package com.example.examprojectspringboot.service.impl;

import com.example.examprojectspringboot.model.Company;
import com.example.examprojectspringboot.repository.CompanyRepository;
import com.example.examprojectspringboot.service.CompanyService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public void saveCompany(Company company) throws IOException {
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company) throws IOException {
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Company company) {
         companyRepository.delete(company);
    }
}
