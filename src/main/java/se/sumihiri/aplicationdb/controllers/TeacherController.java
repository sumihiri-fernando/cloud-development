package se.sumihiri.aplicationdb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sumihiri.aplicationdb.dto.TeacherDTO;
import se.sumihiri.aplicationdb.models.Teacher;
import se.sumihiri.aplicationdb.service.TeacherService;


import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    // Create a new Teacher
    @PostMapping
    public ResponseEntity<String> createTeacher(@RequestBody TeacherDTO requestBody) {
        boolean created = teacherService.createTeacher(requestBody);

        if (created) {
            return new ResponseEntity<>("Teacher created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all Teachers
    @GetMapping
    public ResponseEntity<Iterable<TeacherDTO>> getAllTeachers() {
        Iterable<TeacherDTO> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Get a Teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a Teacher
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable String id, @RequestBody TeacherDTO teacherDTO) {
        boolean isUpdated = teacherService.updateTeacher(id, teacherDTO);
        if (isUpdated) {
            return new ResponseEntity<>("Teacher updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Teacher not found or failed to update", HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Teacher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        boolean isDeleted = teacherService.deleteById(id);

        if (isDeleted) {
            return new ResponseEntity<>("Teacher deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Teacher not found or failed to delete", HttpStatus.NOT_FOUND);
        }
    }
}
