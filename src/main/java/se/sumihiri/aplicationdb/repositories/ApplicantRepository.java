package se.sumihiri.aplicationdb.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.sumihiri.aplicationdb.models.Applicant;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, String> {
}
