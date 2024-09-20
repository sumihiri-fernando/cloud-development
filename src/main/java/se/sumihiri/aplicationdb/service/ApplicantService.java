package se.sumihiri.aplicationdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sumihiri.aplicationdb.models.Applicant;
import se.sumihiri.aplicationdb.repositories.ApplicantRepository;
import se.sumihiri.aplicationdb.dto.ApplicantDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public List<ApplicantDTO> getAllApplicants() {
        List<Applicant> applicants = (List<Applicant>) applicantRepository.findAll();
        List<ApplicantDTO> applicantDTOs = new ArrayList<>();
        for (Applicant applicant : applicants) {
            applicantDTOs.add(convertToDTO(applicant));
        }
        return applicantDTOs;
    }

    public ApplicantDTO getApplicantById(Long id) {
        Applicant applicant = applicantRepository.findById(id).orElse(null);
        return applicant != null ? convertToDTO(applicant) : null;
    }

    public ApplicantDTO createApplicant(Applicant applicantDTO) {
        Applicant applicant = convertToEntity(applicantDTO);
        return convertToDTO(applicantRepository.save(applicant));
    }

    public ApplicantDTO updateApplicant(Long id, ApplicantDTO applicantDTO) {
        Applicant applicant = applicantRepository.findById(id).orElse(null);
        if (applicant != null) {
            applicant.setName(applicantDTO.getName());
            applicant.setBirthYear(applicantDTO.getBirthYear());
            applicant.setEmail(applicantDTO.getEmail());
            return convertToDTO(applicantRepository.save(applicant));
        }
        return null;
    }

    public void deleteApplicantById(Long id) {
        applicantRepository.deleteById(id);
    }

    // Convert Applicant entity to DTO
    private ApplicantDTO convertToDTO(Applicant applicant) {
        return new ApplicantDTO(applicant.getId(), applicant.getName(), applicant.getBirthYear(), applicant.getEmail());
    }

    // Convert DTO to Applicant entity
    private Applicant convertToEntity(Applicant applicantDTO) {
        Applicant applicant = new Applicant();
        applicant.setName(applicantDTO.getName());
        applicant.setBirthYear(applicantDTO.getBirthYear());
        applicant.setEmail(applicantDTO.getEmail());
        return applicant;
    }
}
