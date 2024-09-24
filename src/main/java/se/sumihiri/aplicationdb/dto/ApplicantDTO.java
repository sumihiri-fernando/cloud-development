package se.sumihiri.aplicationdb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ApplicantDTO {
    private Long id;
    private String name;
    private int birthYear;
    private String email;



    }




