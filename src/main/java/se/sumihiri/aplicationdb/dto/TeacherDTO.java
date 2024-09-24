package se.sumihiri.aplicationdb.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.sumihiri.aplicationdb.models.Teacher;

import java.util.List;

@Data
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String name;
    private String subject;
    private String email;


    public  TeacherDTO(Teacher teacher){
        setId(teacher.getId());
        setName(teacher.getName());
        setSubject(teacher.getSubject());
        setEmail(teacher.getSubject());

    }


    public Teacher toTeacher(){
        return new Teacher(id,name,subject,email,null);
    }


}


