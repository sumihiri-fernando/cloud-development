package se.sumihiri.aplicationdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.sumihiri.aplicationdb.service.ApplicantService;
import se.sumihiri.aplicationdb.service.TeacherService;

@Controller
public class HomeController {
    private final TeacherService teacherService;
    private final ApplicantService applicantService;


    public HomeController(TeacherService teacherService, ApplicantService applicantService) {
        this.teacherService = teacherService;
        this.applicantService = applicantService;
    }


    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/teachers")
    public String getTeachers(Model model) {
        var teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }


    @GetMapping("/applicants")
    public String getApplicants(Model model) {
        var applicants = applicantService.getAllApplicants();
        model.addAttribute("applicants", applicants);
        return "applicants";
    }
}
