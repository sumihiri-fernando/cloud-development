package se.sumihiri.aplicationdb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sumihiri.aplicationdb.models.Applicant;
import se.sumihiri.aplicationdb.service.ApplicantService;

@RestController
@RequestMapping("/api/applicants")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;


    @PostMapping
    ResponseEntity<String> createApplicant(@RequestBody Applicant applicant) {
        var created = applicantService.createApplicant(applicant);

        if (created) {
            return new ResponseEntity<>("Applicant has been created successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create applicant", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<Iterable<Applicant>> getAllApplicants() {
        Iterable<Applicant> applicants = applicantService.getAllApplicants();
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicantById(@PathVariable Long id) {
        var applicant = applicantService.getApplicantById(id);
        if (applicant.isPresent()) {
            return new ResponseEntity<>(applicant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateApplicant(@PathVariable Long id, @RequestBody Applicant applicant) {
        var isUpdated = applicantService.updateApplicant(id, applicant);
        if (isUpdated) {
            return new ResponseEntity<>("Applicant has been updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update applicant", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplicant(@PathVariable Long id) {
        var isDeleted = applicantService.deleteApplicant(id);

        if (isDeleted) {
            return new ResponseEntity<>("Applicant has been deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Failed to delete applicant", HttpStatus.NOT_FOUND);
        }
    }
}
