package com.example.examprojectspringboot.api;

import com.example.examprojectspringboot.enums.StudyFormat;
import com.example.examprojectspringboot.model.Student;
import com.example.examprojectspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
    public class StudentApi {

        private final StudentService studentService;

        @Autowired
        public StudentApi(StudentService studentService) {
            this.studentService = studentService;
        }

        @GetMapping("/getAllStudent/{groupId}")
        public String getAllStudent(@PathVariable Long groupId, Model model) {
            model.addAttribute("getAllStudent", studentService.getAllStudent());
            model.addAttribute("groupId", groupId);
            return "/student/allStudents";
        }

        @GetMapping("/getAllStudentByGroupId/{groupId}")
        public String getAllStudentByCourseId(@PathVariable Long groupId, Model model) {
            model.addAttribute("getAllStudentByGroupId", studentService.getAllStudent(groupId));
            model.addAttribute("groupId", groupId);
            return "/student/getAllStudentById";
        }

        @GetMapping("/getStudentById/{id}")
        public String getStudentById(@PathVariable Long id, Model model) {
            model.addAttribute("student", studentService.getStudentById(id));
            return "redirect:/getAllStudentByGroupId";
        }

        @GetMapping("/getAllStudentByGroupId/{groupId}/new")
        public String newStudent(@PathVariable Long groupId, Model model) {
            model.addAttribute("newStudent", new Student());
            model.addAttribute("groupId", groupId);
            model.addAttribute("studyFormatOnline", StudyFormat.ONLINE);
            model.addAttribute("studyFormatOffline", StudyFormat.OFFLINE);
            return "/student/addStudent";
        }

        @PostMapping("/{groupId}/saveStudent")
        public String saveStudent(@PathVariable Long groupId, Model model, @ModelAttribute("newStudent") Student student)throws IOException {
            model.addAttribute("student", new Student());
            model.addAttribute("studyFormatOnline", StudyFormat.ONLINE);
            model.addAttribute("studyFormatOffline", StudyFormat.OFFLINE);
            model.addAttribute("groupId", groupId);
            studentService.saveStudent(groupId, student);
            return "redirect:/getAllStudentByGroupId/" + groupId;
        }

        @GetMapping("/updateStudent/{id}")
        public String updateStudent(@PathVariable Long id, Model model) {
            Student student = studentService.getStudentById(id);
            model.addAttribute("updateStudent", student);
            model.addAttribute("groupId", student.getGroup().getId());
            model.addAttribute("studyFormatOnline", StudyFormat.ONLINE);
            model.addAttribute("studyFormatOffline", StudyFormat.OFFLINE);
            return "/student/updateStudent";
        }

        @PostMapping("/{groupId}/{id}/saveUpdateStudent")
        public String saveUpdateStudent(@PathVariable Long groupId,
                                        @PathVariable Long id,
                                        @ModelAttribute("updateStudent") Student student)throws IOException {
            studentService.updateStudent(id, student);
            return "redirect:/getAllStudentByGroupId/" + groupId;
        }

        @GetMapping("/{groupId}/{id}/deleteStudentById")
        public String deleteStudentById(@PathVariable Long groupId, @PathVariable Long id) {
            studentService.deleteStudent(id);
            return "redirect:/getAllStudentByGroupId/" + groupId;
        }

    }
