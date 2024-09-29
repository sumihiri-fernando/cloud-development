package se.sumihiri.aplicationdb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.sumihiri.aplicationdb.models.Applicant;
import se.sumihiri.aplicationdb.repositories.ApplicantRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;


    public boolean createApplicant(Applicant applicant) {
        try {
            applicantRepository.save(applicant);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Iterable<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }


    public Optional<Applicant> getApplicantById(String id) {
        return applicantRepository.findById(id);
    }


    public boolean updateApplicant(String id, Applicant updatedApplicant) {
        Optional<Applicant> existingApplicant = applicantRepository.findById(id);
        if (existingApplicant.isPresent()) {
            Applicant applicant = existingApplicant.get();

            applicant.setName(updatedApplicant.getName());
            applicant.setBirthYear(updatedApplicant.getBirthYear());
            applicant.setEmail(updatedApplicant.getEmail());

            applicantRepository.save(applicant);
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteApplicant(String id) {
        try {
            applicantRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

