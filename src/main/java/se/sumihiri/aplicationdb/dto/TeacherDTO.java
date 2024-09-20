package se.sumihiri.aplicationdb.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String name;
    private String subject;
    private String email;
    private List<ApplicantDTO> applicants;  // Nested DTO for applicants
}


