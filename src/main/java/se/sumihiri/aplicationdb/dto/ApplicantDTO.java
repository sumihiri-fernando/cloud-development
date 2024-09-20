package se.sumihiri.aplicationdb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDTO {
    private Long id;
    private String name;
    private Integer birthYear;
    private String email;
}

