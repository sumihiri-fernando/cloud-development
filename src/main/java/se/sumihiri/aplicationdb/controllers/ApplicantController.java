package se.sumihiri.aplicationdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.sumihiri.aplicationdb.dto.ApplicantDTO;
import se.sumihiri.aplicationdb.models.Applicant;
import se.sumihiri.aplicationdb.service.ApplicantService;
import se.sumihiri.aplicationdb.service.TeacherService;

@Controller
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private TeacherService teacherService;

    // Display list of applicants
    @GetMapping
    public String viewApplicantsPage(Model model) {
        model.addAttribute("applicants", applicantService.getAllApplicants());
        return "applicant";  // This will be the applicants list HTML page
    }

    // Show form to add new applicant
    @GetMapping("/new")
    public String showNewApplicantForm(Model model) {
        model.addAttribute("applicant", new Applicant());
        model.addAttribute("teachers", teacherService.getAllTeachers());  // To select teacher
        return "applicant-form";  // This will be the form to add/edit an applicant
    }

    // Save a new applicant
    @PostMapping
    public String saveApplicant(@ModelAttribute("applicant") Applicant applicant) {
        applicantService.createApplicant(applicant);
        return "redirect:/applicants";
    }

    // Show form for updating an applicant
    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        ApplicantDTO applicant = applicantService.getApplicantById(id);
        model.addAttribute("applicant", applicant);
        model.addAttribute("teachers", teacherService.getAllTeachers());  // To select teacher
        return "applicant-form";
    }

    // Delete applicant by ID
    @GetMapping("/delete/{id}")
    public String deleteApplicant(@PathVariable(value = "id") Long id) {
        applicantService.deleteApplicantById(id);
        return "redirect:/applicants";
    }
}
