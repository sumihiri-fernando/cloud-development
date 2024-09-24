package se.sumihiri.aplicationdb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.*;

import java.util.List;

@Entity(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subject;
    private String email;
    @OneToMany(mappedBy = "selected", cascade = CascadeType.ALL)
    private List<Applicant> applicants;

}
