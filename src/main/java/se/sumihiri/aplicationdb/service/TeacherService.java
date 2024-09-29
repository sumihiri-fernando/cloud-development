package se.sumihiri.aplicationdb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.sumihiri.aplicationdb.dto.TeacherDTO;
import se.sumihiri.aplicationdb.models.Teacher;
import se.sumihiri.aplicationdb.repositories.TeacherRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public boolean createTeacher(TeacherDTO dto) {
        try {
            teacherRepository.save(dto.toTeacher()); // Assuming TeacherDTO has a toTeacher() method
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Iterable<TeacherDTO> getAllTeachers() {
        var teachers = teacherRepository.findAll();
        var teacherDTOs = new ArrayList<TeacherDTO>();

        for (var teacher : teachers) {
            var teacherDTO = new TeacherDTO(teacher); // Assuming TeacherDTO has a constructor that takes Teacher
            teacherDTOs.add(teacherDTO);
        }
        return teacherDTOs;
    }


    public Optional<Teacher> getTeacherById(String id)
    {
        return teacherRepository.findById(id);
    }


    public boolean updateTeacher(String id, TeacherDTO updatedTeacher) {
        var existingTeacher = teacherRepository.findById(id);

        if (existingTeacher.isPresent()) {
            Teacher teacher = existingTeacher.get();
            teacher.setName(updatedTeacher.getName());
            teacher.setSubject(updatedTeacher.getSubject());
            teacher.setEmail(updatedTeacher.getEmail());
            teacherRepository.save(teacher);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteById(String id) {
        try {
            teacherRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
