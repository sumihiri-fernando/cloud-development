package se.sumihiri.aplicationdb.models;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "applicants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // Ensure you have an ID field

    private String name;

    @Column(name = "birthYear")  // Explicitly define the column name
    private int birthYear;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")  // Ensure this matches the database column
    private Teacher selected;
}
