package se.sumihiri.aplicationdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.sumihiri.aplicationdb.models.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, String> {
}
