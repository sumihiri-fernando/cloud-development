package se.sumihiri.aplicationdb.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(name = "birthYear")
    private int birthYear;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)

    private Teacher selected;
}
