package it.valueson.TwoDatabaseConnection.controller;

import it.valueson.TwoDatabaseConnection.db1.entities.Student;
import it.valueson.TwoDatabaseConnection.db1.repository.StudentRepository;
import it.valueson.TwoDatabaseConnection.db2.entities.Teacher;
import it.valueson.TwoDatabaseConnection.db2.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan("it.valueson.TwoDatabaseConnection.db2.entities")
public class DbController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(value = "/save/teacher", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher){
        Teacher savedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    @PostMapping("/save/student")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentRepository.save(student));
    }


}
