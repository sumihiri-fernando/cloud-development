package se.sumihiri.aplicationdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sumihiri.aplicationdb.models.Applicant;
import se.sumihiri.aplicationdb.models.Teacher;
import se.sumihiri.aplicationdb.repositories.TeacherRepository;
import se.sumihiri.aplicationdb.dto.TeacherDTO;
import se.sumihiri.aplicationdb.dto.ApplicantDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        Iterable<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherDTOs.add(convertToDTO(teacher));
        }
        return teacherDTOs;
    }

    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        return teacher != null ? convertToDTO(teacher) : null;
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        return convertToDTO(teacherRepository.save(teacher));
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            teacher.setName(teacherDTO.getName());
            teacher.setSubject(teacherDTO.getSubject());
            teacher.setEmail(teacherDTO.getEmail());
            return convertToDTO(teacherRepository.save(teacher));
        }
        return null;
    }

    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    // Convert Teacher entity to DTO
    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setSubject(teacher.getSubject());
        teacherDTO.setEmail(teacher.getEmail());

        // Convert applicants without using streams
        List<ApplicantDTO> applicantDTOs = new ArrayList<>();
        for (Applicant applicant : teacher.getApplicants()) {
            applicantDTOs.add(new ApplicantDTO(applicant.getId(), applicant.getName(), applicant.getBirthYear(), applicant.getEmail()));
        }
        teacherDTO.setApplicants(applicantDTOs);

        return teacherDTO;
    }

    // Convert DTO to Teacher entity
    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setSubject(teacherDTO.getSubject());
        teacher.setEmail(teacherDTO.getEmail());
        return teacher;
    }
}
