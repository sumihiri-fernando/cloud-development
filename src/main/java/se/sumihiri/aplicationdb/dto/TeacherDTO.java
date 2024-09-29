package se.sumihiri.aplicationdb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import se.sumihiri.aplicationdb.models.Teacher;



@Data
@NoArgsConstructor
public class TeacherDTO {
    private String id;
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


