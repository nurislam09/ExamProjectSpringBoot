package com.example.examprojectspringboot.api;

import com.example.examprojectspringboot.model.Company;
import com.example.examprojectspringboot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class CompanyApi {
    private final CompanyService companyService;

    @Autowired
    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

@GetMapping("/getAllCompany")
public String getAllCompany(Model model) {
    model.addAttribute("getAllCompany", companyService.getAllCompany());
    return "/company/allCompanies";
}

    @GetMapping("/getCompanyById/{id}")
    public String getCompanyById(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "redirect:/getAllCompany";
    }

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("newCompany", new Company());
        return "/company/addCompany";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("newCompany") Company company) throws IOException {
        companyService.saveCompany(company);
        return "redirect:/getAllCompany";
    }

    @GetMapping("/updateCompany")
    public String updateCompany(@RequestParam("companyId") Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/company/updateCompany";
    }

    @PostMapping("/saveUpdateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company) throws IOException {
        companyService.updateCompany(company);
        return "redirect:/getAllCompany";
    }

    @GetMapping("/deleteCompany")
    public String deleteCompany(@RequestParam("companyId") Long id) {
        companyService.deleteCompany(companyService.getCompanyById(id));
        return "redirect:/getAllCompany";
    }
}
