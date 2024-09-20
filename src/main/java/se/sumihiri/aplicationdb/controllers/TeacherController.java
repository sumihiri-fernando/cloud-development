package se.sumihiri.aplicationdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import se.sumihiri.aplicationdb.dto.TeacherDTO;
import se.sumihiri.aplicationdb.models.Teacher;
import se.sumihiri.aplicationdb.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Display list of teachers
    @GetMapping
    public String getAllTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teacher";  // This will be the teachers list HTML page
    }

    // Show form to add new teacher
    @GetMapping("/new")
    public String showNewTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-form";  // This will be the form to add/edit a teacher
    }

    // Save a new teacher
    @PostMapping
    public String createTeacher(@ModelAttribute("teacher") TeacherDTO teacher, TeacherDTO teacherDTO) {
        teacherService.createTeacher(teacherDTO);
        return "redirect:/teachers";
    }

    // Show form for updating a teacher
    @GetMapping("/edit/{id}")
    public String getTeacherById(@PathVariable(value = "id") Long id, Model model) {
        TeacherDTO teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher-form";
    }

    // Delete teacher by ID
    @GetMapping("/delete/{id}")
    public String deleteTeacherById(@PathVariable(value = "id") Long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers";
    }
}